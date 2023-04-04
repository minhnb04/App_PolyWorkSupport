package com.example.app_supportpolywork.view.main_activity.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.databinding.FragmentHomeBinding;
import com.example.app_supportpolywork.util.CommonUtil;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding mBinding;
    private final ActivityResultLauncher<String> mRequestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    callPhone();
                } else {
                    CommonUtil.makeToast(requireContext(), "Bạn cần cho phép app quyền liên hệ trước khi thực hiện thao tác này!");
                }
            });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupBtnContact();
        setupBtnSupportCenter();
    }

    private void setupBtnContact() {
        mBinding.btnContact.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                mRequestPermissionLauncher.launch(
                        Manifest.permission.CALL_PHONE);
            }

        });
    }

    private void setupBtnSupportCenter() {
        mBinding.btnSupportCenter.setOnClickListener(v -> {

        });
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1900 3030"));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
