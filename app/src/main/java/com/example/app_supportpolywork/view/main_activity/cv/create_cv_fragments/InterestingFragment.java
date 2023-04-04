package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.data.manager.LocalCVManager;
import com.example.app_supportpolywork.databinding.FragmentInterestingBinding;
import com.example.app_supportpolywork.util.CommonUtil;


public class InterestingFragment extends BaseFragment {

    private FragmentInterestingBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentInterestingBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
    }

    private void fillContent() {
        String interesting = LocalCVManager.getInstance().getInteresting();
        if (interesting != null) {
            mBinding.edtInteresting.getEditText().setText(interesting);
        }
    }

    private void setupToolbar() {
        mBinding.toolbar.tvTitle.setText("Sở thích");
        mBinding.toolbar.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.toolbar.imvSave.setOnClickListener(v -> saveCertificate());
    }

    private void saveCertificate() {
        CommonUtil.hideKeyboard(requireActivity());
        mBinding.edtInteresting.setError(null);
        String interesting = CommonUtil.getStringFromEdt(mBinding.edtInteresting);
        if (interesting.isEmpty()) {
            mBinding.edtInteresting.setError("Vui lòng nhập các sở thích");
            return;
        }
        LocalCVManager.getInstance().setInteresting(interesting);
        mNavController.popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}