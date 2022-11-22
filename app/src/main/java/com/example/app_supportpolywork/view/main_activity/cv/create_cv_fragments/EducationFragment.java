package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.data.manager.CVManager;
import com.example.app_supportpolywork.data.model.cv_model.Education;
import com.example.app_supportpolywork.databinding.FragmentEducationBinding;

public class EducationFragment extends BaseFragment {

    private FragmentEducationBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentEducationBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
    }

    private void fillContent() {
        Education education = CVManager.getInstance().getEducation();
        if (education != null) {
            mBinding.edtUnisersityName.getEditText().setText(education.getUniversityName());
            mBinding.edtDegreeRank.getEditText().setText(education.getDegreeRank());
            mBinding.edtDegreeType.getEditText().setText(education.getDegreeType());
            mBinding.edtMajor.getEditText().setText(education.getMajor());
            mBinding.edtEndTime.getEditText().setText(education.getEndTime());
            mBinding.edtStartTime.getEditText().setText(education.getStartTime());
        }
    }

    private void setupToolbar() {
        mBinding.toolbar.tvTitle.setText("Học vấn");
        mBinding.toolbar.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.toolbar.imvSave.setOnClickListener(v -> saveEducation());
    }

    private void saveEducation() {
        String universityName = getStringFromEdt(mBinding.edtUnisersityName);
        String major = getStringFromEdt(mBinding.edtMajor);
        String degreeType = getStringFromEdt(mBinding.edtDegreeType);
        String degreeRank = getStringFromEdt(mBinding.edtDegreeRank);
        String startTime = getStringFromEdt(mBinding.edtStartTime);
        String endTime = getStringFromEdt(mBinding.edtEndTime);

        if (validateUniversityName(universityName) &&
                validateMajor(major) &&
                validateDegreeType(degreeType) &&
                validateDegreeRank(degreeRank) &&
                validateStartTime(startTime) &&
                validateEndTime(endTime)) {

            Education education = new Education(universityName, major, degreeRank, degreeType, startTime, endTime);
            CVManager.getInstance().setEducation(education);
            mNavController.popBackStack();
        }
    }

    private boolean validateUniversityName(String universityName) {
        if (universityName.isEmpty()) {
            mBinding.edtUnisersityName.setError("Vui lòng nhập tên trường học");
            return false;
        }
        return true;
    }

    private boolean validateMajor(String major) {
        if (major.isEmpty()) {
            mBinding.edtMajor.setError("Vui lòng nhập tên chuyên ngành học");
            return false;
        }
        return true;
    }

    private boolean validateDegreeType(String degreeType) {
        if (degreeType.isEmpty()) {
            mBinding.edtDegreeType.setError("Vui lòng nhập tên loại bằng");
            return false;
        }
        return true;
    }

    private boolean validateDegreeRank(String degreeRank) {
        if (degreeRank.isEmpty()) {
            mBinding.edtDegreeRank.setError("Vui lòng nhập tốt nghiệp loại nào");
            return false;
        }
        return true;
    }

    private boolean validateStartTime(String startTime) {
        if (startTime.isEmpty()) {
            mBinding.edtStartTime.setError("Vui lòng nhập thời gian bắt đầu học");
            return false;
        }
        return true;
    }

    private boolean validateEndTime(String endTime) {
        if (endTime.isEmpty()) {
            mBinding.edtEndTime.setError("Vui lòng nhập thời gian kết thúc học");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}