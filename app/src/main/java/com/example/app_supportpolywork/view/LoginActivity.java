package com.example.app_supportpolywork.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.example.app_supportpolywork.data.network.UserManager;
import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.databinding.ActivityLoginBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.ShareFileUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.view.main_activity.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;

    private final TaskListener mOnResultResponse = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            Pair<User, String> userAndToken = (Pair<User, String>) o;
            ShareFileUtil.saveUser(LoginActivity.this, userAndToken.first);
            ShareFileUtil.saveToken(LoginActivity.this, userAndToken.second);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        @Override
        public void onError(Exception e) {
            mBinding.tvError.setVisibility(View.VISIBLE);
            mBinding.tvError.setText("Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setupAutoLogin();
        setupBtnLogin();
        setupTvRegister();
    }

    private void setupAutoLogin() {
        String token = ShareFileUtil.getToken(this);
        if(!token.isEmpty()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void setupBtnLogin() {
        mBinding.btnLogin.setOnClickListener(v -> {
            CommonUtil.hideKeyboard(LoginActivity.this);
            clearRuins();
            String userName = CommonUtil.getStringFromEdt(mBinding.edtUserName);
            String password = CommonUtil.getStringFromEdt(mBinding.edtPassword);
            if (validateUserName(userName) && validatePassword(password)) {
                UserManager.getInstance().login(userName, password, mOnResultResponse);
            }
        });
    }

    private void clearRuins() {
        mBinding.tvError.setVisibility(View.GONE);
        mBinding.tvError.setText("");
        mBinding.edtUserName.setError(null);
        mBinding.edtPassword.setError(null);
    }

    private void setupTvRegister() {
        mBinding.tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
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
            mBinding.edtPassword.setError("Vui lòng nhập mật khẩu đúng định dạng.");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}