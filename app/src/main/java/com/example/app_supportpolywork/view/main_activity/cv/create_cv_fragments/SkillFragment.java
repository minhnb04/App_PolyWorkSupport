package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.data.manager.CVManager;
import com.example.app_supportpolywork.databinding.FragmentSkillBinding;
import com.example.app_supportpolywork.util.CommonUtil;

public class SkillFragment extends BaseFragment {
    private FragmentSkillBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSkillBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
    }

    private void fillContent() {
        String skill = CVManager.getInstance().getSkill();
        if (skill != null) {
            mBinding.edtSkill.getEditText().setText(skill);
        }
    }

    private void setupToolbar() {
        mBinding.include.tvTitle.setText("Kĩ Năng");
        mBinding.include.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.include.imvSave.setOnClickListener(v -> saveCertificate());
    }

    private void saveCertificate() {
        CommonUtil.hideKeyboard(requireActivity());
        mBinding.edtSkill.setError(null);
        String skill = CommonUtil.getStringFromEdt(mBinding.edtSkill);
        if (skill.isEmpty()) {
            mBinding.edtSkill.setError("Vui lòng nhập các kĩ năng của bạn");
            return;
        }
        CVManager.getInstance().setSkill(skill);
        mNavController.popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}