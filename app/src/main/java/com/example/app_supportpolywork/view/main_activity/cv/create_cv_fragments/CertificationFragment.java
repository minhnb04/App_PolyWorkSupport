package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.manager.CVManager;
import com.example.app_supportpolywork.databinding.FragmentCertificationBinding;
import com.example.app_supportpolywork.util.CommonUtil;


public class CertificationFragment extends BaseFragment {
    private FragmentCertificationBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCertificationBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
    }

    private void fillContent() {
        String certification = CVManager.getInstance().getCertification();
        if(certification != null) {
            mBinding.edtCertification.getEditText().setText(certification);
        }
    }

    private void setupToolbar() {
        mBinding.include.tvTitle.setText("Chứng chỉ");
        mBinding.include.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.include.imvSave.setOnClickListener(v -> saveCertificate());
    }

    private void saveCertificate() {
        CommonUtil.hideKeyboard(requireActivity());
        mBinding.edtCertification.setError(null);
        String certification = CommonUtil.getStringFromEdt(mBinding.edtCertification);
        if(certification.isEmpty()) {
            mBinding.edtCertification.setError("Vui lòng nhập các chứng chỉ");
            return;
        }
        CVManager.getInstance().setCertification(certification);
        mNavController.popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}