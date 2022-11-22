package com.example.app_supportpolywork.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_supportpolywork.databinding.ActivityLoginBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.view.main_activity.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setupBtnLogin();
        setupTvRegister();
    }

    private void setupBtnLogin() {
        mBinding.btnLogin.setOnClickListener(v -> {
            String email = CommonUtil.getStringFromEdt(mBinding.edtEmail);
            String password = CommonUtil.getStringFromEdt(mBinding.edtPassword);
            login(email, password);

        });
    }

    private void setupTvRegister() {
        mBinding.tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
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

    private void login(String email, String password) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}