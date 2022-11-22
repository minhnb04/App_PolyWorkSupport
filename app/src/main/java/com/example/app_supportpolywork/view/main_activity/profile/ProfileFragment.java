package com.example.app_supportpolywork.view.main_activity.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.databinding.FragmentProfileBinding;


public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupEditInfoBtn();
        setupChangePasswordBtn();
        setupLogoutBtn();
    }

    private void setupLogoutBtn() {
        mBinding.btnLogout.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                    .setTitle("Log out")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất tài khoản?")
                    .setNegativeButton("Hủy", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setPositiveButton("Đăng xuất", (dialog, which) -> {
                        logout(dialog);
                    })
                    .create();
            alertDialog.show();
        });
    }

    private void logout(DialogInterface dialog) {

    }

    private void setupChangePasswordBtn() {
        mBinding.btnChangePassword.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_profileFragment_to_changePasswordFragment);
        });
    }

    private void setupEditInfoBtn() {
        mBinding.btnEditInfo.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_profileFragment_to_editProfileFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}