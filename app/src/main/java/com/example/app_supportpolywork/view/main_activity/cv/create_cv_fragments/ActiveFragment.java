package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.data.manager.LocalCVManager;
import com.example.app_supportpolywork.databinding.FragmentActiveBinding;
import com.example.app_supportpolywork.util.CommonUtil;


public class ActiveFragment extends BaseFragment {
    private FragmentActiveBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentActiveBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
    }

    private void fillContent() {
        String active = LocalCVManager.getInstance().getActive();
        if (active != null) {
            mBinding.edtActive.getEditText().setText(active);
        }
    }

    private void setupToolbar() {
        mBinding.toolbar.tvTitle.setText("Hoạt động");
        mBinding.toolbar.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.toolbar.imvSave.setOnClickListener(v -> saveCertificate());
    }

    private void saveCertificate() {
        CommonUtil.hideKeyboard(requireActivity());
        mBinding.edtActive.setError(null);
        String active = CommonUtil.getStringFromEdt(mBinding.edtActive);
        if (active.isEmpty()) {
            mBinding.edtActive.setError("Vui lòng nhập các hoạt động của bạn");
            return;
        }
        LocalCVManager.getInstance().setActive(active);
        mNavController.popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}