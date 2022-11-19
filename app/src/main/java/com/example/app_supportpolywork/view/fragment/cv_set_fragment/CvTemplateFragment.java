package com.example.app_supportpolywork.view.fragment.cv_set_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.databinding.FragmentCvTemplateBinding;


public class CvTemplateFragment extends Fragment {

    private FragmentCvTemplateBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCvTemplateBinding.inflate(inflater);
        return mBinding.getRoot();
    }


}