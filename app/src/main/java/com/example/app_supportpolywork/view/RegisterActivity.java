package com.example.app_supportpolywork.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_supportpolywork.data.network.UserManager;
import com.example.app_supportpolywork.databinding.ActivityRegisterBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.TaskListener;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setupBtnRegister();
        setupLoginNow();
    }

    private void setupLoginNow() {
        mBinding.tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }

    private void setupBtnRegister() {
        mBinding.btnRegister.setOnClickListener(v -> {
            CommonUtil.hideKeyboard(RegisterActivity.this);
            clearRuins();

            String userName = CommonUtil.getStringFromEdt(mBinding.edtUserName);
            String password = CommonUtil.getStringFromEdt(mBinding.edtPassword);
            String checkerPassword = CommonUtil.getStringFromEdt(mBinding.edtCheckerPassword);
            String fullName = CommonUtil.getStringFromEdt(mBinding.edtFullName);
            String email = CommonUtil.getStringFromEdt(mBinding.edtEmail);


            if (validateUserName(userName)
                    && validatePassword(password)
                    && validateCheckerPassword(password, checkerPassword)
                    && validateName(fullName)
                    && validateEmail(email)
            ) {
                register(userName, password, fullName, email);
            }

        });
    }

    private void clearRuins() {
        mBinding.edtEmail.setError(null);
        mBinding.edtPassword.setError(null);
        mBinding.edtCheckerPassword.setError(null);
        mBinding.edtFullName.setError(null);
        mBinding.edtUserName.setError(null);
        mBinding.tvError.setVisibility(View.GONE);
        mBinding.tvError.setText("");
    }

    private void register(String userName, String password, String fullName, String email) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng ký ...");
        progressDialog.show();
        UserManager.getInstance().register(userName, password, fullName, email, new TaskListener() {
            @Override
            public void onSuccess(Object o) {
                progressDialog.dismiss();
                CommonUtil.makeToast(RegisterActivity.this, "Đăng kí thành công");
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finishAffinity();
            }

            @Override
            public void onError(Exception e) {
                progressDialog.dismiss();
                mBinding.tvError.setVisibility(View.VISIBLE);
                mBinding.tvError.setText(e.getMessage());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    private boolean validateUserName(String userName) {
        if (userName.isEmpty()) {
            mBinding.edtUserName.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }

        if (userName.length() <= 6) {
            mBinding.edtUserName.setError("Tên đăng nhập cần lớn hơn 6 kí tự");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if (password.isEmpty()) {
            mBinding.edtPassword.setError("Vui lòng nhập mật khẩu.");
            return false;
        }

        if (!CommonUtil.validPassword(password)) {
            mBinding.edtPassword.setError("Vui lòng nhập mật khẩu cần có 8 kí tự trở lên (có số, chữ in hoa, kí tự đặc biệt)");
            return false;
        }
        return true;
    }

    public boolean validateCheckerPassword(String password, String checkerPassword) {

        if (checkerPassword.isEmpty()) {
            mBinding.edtCheckerPassword.setError("Vui lòng nhập mật khẩu xác nhận.");
            return false;
        }

        if (!checkerPassword.equals(password)) {
            mBinding.edtCheckerPassword.setError("Mật khẩu xác nhận không trùng khớp.");
            return false;
        }
        return true;
    }

    public boolean validateName(String name) {
        if (name.isEmpty()) {
            mBinding.edtFullName.setError("Hãy nhập họ và tên của bạn.");
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email) {
        if (email.isEmpty()) {
            mBinding.edtEmail.setError("Vui lòng nhập email của bạn.");
            return false;
        }

        if (!CommonUtil.validEmail(email)) {
            mBinding.edtEmail.setError("Vui lòng nhập email đúng định dạng.");
            return false;
        }
        return true;
    }


}