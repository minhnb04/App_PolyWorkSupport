package com.example.app_supportpolywork.view.main_activity.profile;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;
import static com.example.app_supportpolywork.util.CommonUtil.hideKeyboard;
import static com.example.app_supportpolywork.util.CommonUtil.validPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.databinding.FragmentChangePasswordBinding;


public class ChangePasswordFragment extends BaseFragment {

    private FragmentChangePasswordBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupBackBtn();
        setupChangePasswordBtn();
    }

    private void setupBackBtn() {
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.popBackStack());
    }

    private void setupChangePasswordBtn() {
        mBinding.btnChangePassword.setOnClickListener(v -> {
            hideKeyboard(requireActivity());
            clearRuins();
            String oldPassword = getStringFromEdt(mBinding.edtOldPassword);
            String newPassword = getStringFromEdt(mBinding.edtNewPassword);
            String checkerPassword = getStringFromEdt(mBinding.edtCheckerPassword);

            if (validateOldPassword(oldPassword) &&
                    validateNewPassword(newPassword, oldPassword) &&
                    validateCheckerPassword(checkerPassword, newPassword)) {
                changePassword(oldPassword, newPassword);
            }

        });
    }

    private void clearRuins() {
        mBinding.edtCheckerPassword.setError(null);
        mBinding.edtOldPassword.setError(null);
        mBinding.edtNewPassword.setError(null);
    }

    private void changePassword(String oldPassword, String newPassword) {
        mProgressDialog.setMessage("Đang đổi mật khẩu ...");
        mProgressDialog.show();


    }

    private boolean validateCheckerPassword(String checkerPassword, String newPassword) {
        if (checkerPassword.isEmpty()) {
            mBinding.edtCheckerPassword.setError("Vui lòng nhập mật khẩu");
            return false;
        }

        if (!checkerPassword.equals(newPassword)) {
            mBinding.edtCheckerPassword.setError("Mật khẩu không trùng khớp");
            return false;
        }

        return true;
    }

    private boolean validateNewPassword(String newPassword, String oldPassword) {
        if (newPassword.isEmpty()) {
            mBinding.edtNewPassword.setError("Vui lòng nhập mật khẩu");
            return false;
        }

        if (!validPassword(newPassword)) {
            mBinding.edtNewPassword.setError("Vui lòng nhập mật khẩu đúng định dạng");
            return false;
        }

        if (newPassword.equals(oldPassword)) {
            mBinding.edtNewPassword.setError("Mật khẩu cũ và mật khẩu mới đang trùng nhau");
            return false;
        }
        return true;
    }

    private boolean validateOldPassword(String oldPassword) {
        if (oldPassword.isEmpty()) {
            mBinding.edtOldPassword.setError("Vui lòng nhập mật khẩu");
            return false;
        }

        if (!validPassword(oldPassword)) {
            mBinding.edtOldPassword.setError("Vui lòng nhập mật khẩu đúng định dạng");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}