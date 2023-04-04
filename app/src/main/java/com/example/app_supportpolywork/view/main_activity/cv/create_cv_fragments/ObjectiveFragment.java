package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.data.manager.LocalCVManager;
import com.example.app_supportpolywork.databinding.FragmentObjectiveBinding;
import com.example.app_supportpolywork.util.CommonUtil;


public class ObjectiveFragment extends BaseFragment {

    private FragmentObjectiveBinding mBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentObjectiveBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();

    }

    private void fillContent() {
        String objective = LocalCVManager.getInstance().getObjective();
        if(objective != null) {
            mBinding.edtObjective.getEditText().setText(objective);
        }
    }

    private void setupToolbar() {
        mBinding.include.tvTitle.setText("Mục tiêu công việc");
        mBinding.include.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.include.imvSave.setOnClickListener(v -> saveObjective());
    }

    private void saveObjective() {
        CommonUtil.hideKeyboard(requireActivity());
        mBinding.edtObjective.setError(null);
        String objective = CommonUtil.getStringFromEdt(mBinding.edtObjective);
        if(objective.isEmpty()) {
            mBinding.edtObjective.setError("Vui lòng chọn mục tiêu công việc");
            return;
        }
        LocalCVManager.getInstance().setObjective(objective);
        mNavController.popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}