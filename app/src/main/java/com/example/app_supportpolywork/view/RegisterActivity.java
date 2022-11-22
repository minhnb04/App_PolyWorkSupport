package com.example.app_supportpolywork.view;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_supportpolywork.databinding.ActivityRegisterBinding;
import com.example.app_supportpolywork.util.CommonUtil;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setupBtnRegister();

    }

    private void setupBtnRegister() {
        mBinding.btnRegister.setOnClickListener(v -> {
            CommonUtil.hideKeyboard(RegisterActivity.this);
            clearRuins();

            String email = CommonUtil.getStringFromEdt(mBinding.edtEmail);
            String password = CommonUtil.getStringFromEdt(mBinding.edtPassword);
            String checkerPassword = CommonUtil.getStringFromEdt(mBinding.edtCheckerPassword);
            String name = CommonUtil.getStringFromEdt(mBinding.edtName);
            String phoneNumber = CommonUtil.getStringFromEdt(mBinding.edtPhoneNumber);

            if (validateEmail(email)
                    && validatePassword(password)
                    && validateCheckerPassword(password, checkerPassword)
                    && validateName(name)
                    && validatePhoneNumber(phoneNumber)
            ) {
                register(email, password, name, phoneNumber);
            }

        });
    }

    private void clearRuins() {
        mBinding.edtEmail.setError(null);
        mBinding.edtPassword.setError(null);
        mBinding.edtCheckerPassword.setError(null);
        mBinding.edtName.setError(null);
        mBinding.edtPhoneNumber.setError(null);
    }

    private void register(String email, String password, String name, String phoneNumber) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng ký ...");
        progressDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
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


    public boolean validatePassword(String password) {
        if (password.isEmpty()) {
            mBinding.edtPassword.setError("Vui lòng nhập mật khẩu.");
            return false;
        }

        if (!CommonUtil.validPassword(password)) {
            mBinding.edtPassword.setError("Vui lòng nhập mật khẩu đúng định dạng.");
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
            mBinding.edtName.setError("Hãy nhập tên của bạn.");
            return false;
        }

        return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            mBinding.edtPhoneNumber.setError("Hãy nhập số điện thoại của bạn.");
            return false;
        }

        if (!CommonUtil.validNumberPhone(phoneNumber)) {
            mBinding.edtPhoneNumber.setError("Vui lòng nhập số điện thoại đúng định dạng.");
            return false;
        }
        return true;
    }

}