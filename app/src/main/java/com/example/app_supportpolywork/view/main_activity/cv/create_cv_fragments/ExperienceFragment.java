package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;
import static com.example.app_supportpolywork.util.CommonUtil.makeToast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.manager.CVManager;
import com.example.app_supportpolywork.data.model.cv_model.Experience;
import com.example.app_supportpolywork.databinding.FragmentExperienceBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class ExperienceFragment extends BaseFragment {
    private FragmentExperienceBinding mBinding;
    private int mCountField = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentExperienceBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
        setupBtnAddExperienceField();
    }

    private void fillContent() {
        List<Experience> experiences = CVManager.getInstance().getExperiences();
        if(experiences != null) {
            for (int i = 0; i < experiences.size(); i++) {
                View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_experience, mBinding.layout, false);

                TextInputLayout companyNameTextInputLayout = view.findViewById(R.id.edtCompanyName);
                TextInputLayout positionTextInputLayout = view.findViewById(R.id.edtPosition);
                TextInputLayout startTimeTextInputLayout = view.findViewById(R.id.edtStartTime);
                TextInputLayout endTimeTextInputLayout = view.findViewById(R.id.edtEndTime);
                TextInputLayout descriptionTextInputLayout = view.findViewById(R.id.edtDescription);

                companyNameTextInputLayout.getEditText().setText(experiences.get(i).getCompanyName());
                positionTextInputLayout.getEditText().setText(experiences.get(i).getTitle());
                startTimeTextInputLayout.getEditText().setText(experiences.get(i).getStartTime());
                endTimeTextInputLayout.getEditText().setText(experiences.get(i).getEndTime());
                descriptionTextInputLayout.getEditText().setText(experiences.get(i).getDescription());

                mBinding.layout.addView(view);
                mCountField++;
                setForField();
            }
        } else {
            setExperienceField();
        }
    }

    private List<Experience> getContentExperienceField() {
        List<Experience> experiences = new ArrayList<>();
        ViewGroup parent = mBinding.layout;
        for (int i = 0; i < parent.getChildCount(); i++) {
            TextInputLayout companyNameTextInputLayout = parent.getChildAt(i).findViewById(R.id.edtCompanyName);
            TextInputLayout positionTextInputLayout = parent.getChildAt(i).findViewById(R.id.edtPosition);
            TextInputLayout startTimeTextInputLayout = parent.getChildAt(i).findViewById(R.id.edtStartTime);
            TextInputLayout endTimeTextInputLayout = parent.getChildAt(i).findViewById(R.id.edtEndTime);
            TextInputLayout descriptionTextInputLayout = parent.getChildAt(i).findViewById(R.id.edtDescription);

            String companyName = getStringFromEdt(companyNameTextInputLayout);
            String position = getStringFromEdt(positionTextInputLayout);
            String startTime = getStringFromEdt(startTimeTextInputLayout);
            String endTime = getStringFromEdt(endTimeTextInputLayout);
            String description = getStringFromEdt(descriptionTextInputLayout);

            if (validateCompanyName(companyName, companyNameTextInputLayout) &&
                    validatePosition(position, positionTextInputLayout) &&
                    validateStartTime(startTime, startTimeTextInputLayout) &&
                    validateEndTime(endTime, endTimeTextInputLayout) &&
                    validateDescription(description, descriptionTextInputLayout)) {

                Experience experience = new Experience(companyName, position, description, startTime, endTime);
                experiences.add(experience);
            } else {
                makeToast(requireContext(), "Xảy ra lỗi, vui lòng kiểm tra lại thông tin đang nhập");
                return null;
            }
        }
        return experiences;
    }

    private boolean validateCompanyName(String companyName, TextInputLayout companyNameTextInputLayout) {
        if (companyName.isEmpty()) {
            companyNameTextInputLayout.setError("Vui lòng nhập tên công ty hoặc tên dự án");
            return false;
        }
        return true;
    }

    private boolean validatePosition(String position, TextInputLayout positionTextInputLayout) {
        if (position.isEmpty()) {
            positionTextInputLayout.setError("Vui lòng nhập vị trí đảm nhiệm");
            return false;
        }
        return true;
    }

    private boolean validateStartTime(String startTime, TextInputLayout startTimeTextInputLayout) {
        if (startTime.isEmpty()) {
            startTimeTextInputLayout.setError("Vui lòng nhập thời gian bắt đầu");
            return false;
        }
        return true;
    }

    private boolean validateEndTime(String endTime, TextInputLayout endTimeTextInputLayout) {
        if (endTime.isEmpty()) {
            endTimeTextInputLayout.setError("Vui lòng nhập thời gian kết thúc");
            return false;
        }
        return true;
    }


    private boolean validateDescription(String description, TextInputLayout descriptionTextInputLayout) {
        if (description.isEmpty()) {
            descriptionTextInputLayout.setError("Vui lòng nhập mô tả");
            return false;
        }
        return true;
    }

    private void setExperienceField() {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_experience, mBinding.layout, false);
        mBinding.layout.addView(view);
        mCountField++;
        setForField();
    }

    private void setupBtnAddExperienceField() {
        mBinding.btnAdd.setOnClickListener(v -> {
            if(mCountField < 2) {
                View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_experience, mBinding.layout, false);
                mBinding.layout.addView(view);
                mCountField++;
                setForField();
            } else {
                makeToast(requireContext(), "Hiện chúng tôi chỉ hỗ trợ hai phần kinh nghiệm làm việc trên CV này");
            }
        });
    }

    private void setForField() {
        ViewGroup parent = mBinding.layout;
        for (int i = 0; i < parent.getChildCount(); i++) {
            setTitleForField();
            TextView tvTitle = parent.getChildAt(i).findViewById(R.id.tvTitle);
            tvTitle.setText("Kinh nghiệm " + (i + 1));

            ImageView imvClear = parent.getChildAt(i).findViewById(R.id.imvClear);
            int finalI = i;
            imvClear.setOnClickListener(v -> {
                parent.removeViewAt(finalI);
                setTitleForField();
            });
        }
    }

    private void setTitleForField() {
        ViewGroup parent = mBinding.layout;
        for (int i = 0; i < parent.getChildCount(); i++) {
            TextView tvTitle = parent.getChildAt(i).findViewById(R.id.tvTitle);
            tvTitle.setText("Kinh nghiệm " + (i + 1));
        }
    }

    private void setupToolbar() {
        mBinding.toolbar.tvTitle.setText("Kinh nghiệm");
        mBinding.toolbar.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.toolbar.imvSave.setOnClickListener(v -> saveExperience());
    }

    private void saveExperience() {
        List<Experience> experiences = getContentExperienceField();
        if (experiences != null) {
            CVManager.getInstance().setExperiences(experiences);
            mNavController.popBackStack();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}