package com.example.app_supportpolywork.view.main_activity.cv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.manager.CVTemplateManager;
import com.example.app_supportpolywork.data.model.cv_model.CV;
import com.example.app_supportpolywork.databinding.FragmentCvTemplateBinding;
import com.example.app_supportpolywork.view.main_activity.MainActivity;


public class CvTemplateFragment extends BaseFragment implements CvTemplateAdapter.CVTemplateListener {

    private FragmentCvTemplateBinding mBinding;
    private CvTemplateAdapter mCvTemplateAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCvTemplateBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) requireActivity()).closeBottomNav();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupCVTemplates();
    }

    private void setupCVTemplates() {
        mCvTemplateAdapter = new CvTemplateAdapter(this);
        mBinding.rcvCVTemplate.setAdapter(mCvTemplateAdapter);
        mCvTemplateAdapter.submitList(CVTemplateManager.getCVTemplate());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) requireActivity()).openBottomNav();
    }

    @Override
    public void onClickCvTemplateItem(CV cv) {
        mNavController.navigate(R.id.action_cvTemplateFragment_to_createCVFragment);
    }
}