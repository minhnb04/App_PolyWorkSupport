package com.example.app_supportpolywork.view.fragment.cv_set_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.app_supportpolywork.databinding.FragmentPersonalInfoBinding;

public class PersonalInfoFragment extends CVSetBaseFragment {

    private FragmentPersonalInfoBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentPersonalInfoBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupEdtDate();
        setupEdtGender();
        setupBtnNext();
        setupBtnPrevious();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setupEdtDate() {
        mBinding.edtDate.setOnTouchListener((v, event) -> {
            showDatePopup();
            return true;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEdtGender() {
        mBinding.edtDate.setOnTouchListener((v, event) -> {
            showGenderPopup();
            return true;
        });
    }

    private void setupBtnNext() {
        mBinding.include.imvNext.setOnClickListener(v -> {
            if(validateInputData()) {
                navigate(ObjectiveAndPositionFragment.class);
            }
        });
    }

    private void setupBtnPrevious() {
        mBinding.include.imvPrevious.setOnClickListener(v -> {
           popBackStack();
        });
    }

    private void showDatePopup() {

    }

    private void showGenderPopup() {

    }

    private boolean validateInputData() {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}