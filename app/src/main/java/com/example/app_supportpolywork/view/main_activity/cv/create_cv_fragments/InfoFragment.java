package com.example.app_supportpolywork.view.main_activity.cv.create_cv_fragments;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;
import static com.example.app_supportpolywork.util.CommonUtil.makeToast;
import static com.example.app_supportpolywork.util.CommonUtil.validEmail;
import static com.example.app_supportpolywork.util.CommonUtil.validNumberPhone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.manager.LocalCVManager;
import com.example.app_supportpolywork.data.model.cv_model.Info;
import com.example.app_supportpolywork.databinding.FragmentInfoBinding;

import java.util.Calendar;

public class InfoFragment extends BaseFragment {

    private FragmentInfoBinding mBinding;
    private Uri mImageUri;

    private String mGender;


    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mImageUri = result.getData().getData();
            mBinding.imvAvatar.setImageURI(mImageUri);
        }
    });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentInfoBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillContent();
        setupToolbar();
        setupImvAvatar();
        setupEdtDate();
        setupEdtGender();
    }

    private void fillContent() {
        Info info = LocalCVManager.getInstance().getInfo();
        if (info != null) {
            mImageUri = info.getImage();
            mBinding.imvAvatar.setImageURI(mImageUri);
            mBinding.edtName.getEditText().setText(info.getName());
            mBinding.edtEmail.getEditText().setText(info.getEmail());
            mBinding.edtAddress.getEditText().setText(info.getAddress());
            mBinding.edtPosition.getEditText().setText(info.getPosition());
            mBinding.edtPhoneNumber.getEditText().setText(info.getPhoneNumber());
            mBinding.edtDate.setText(info.getDate());
            mBinding.edtGender.setText(info.getGender());
        }
    }

    private void setupImvAvatar() {
        mBinding.imvAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            mGetContent.launch(intent);
        });
    }

    private void setupToolbar() {
        mBinding.include.tvTitle.setText("Thông Tin");
        mBinding.include.imvBack.setOnClickListener(v -> mNavController.popBackStack());
        mBinding.include.imvSave.setOnClickListener(v -> {
            saveInfo();
        });
    }

    private void saveInfo() {
        String name = getStringFromEdt(mBinding.edtName);
        String position = getStringFromEdt(mBinding.edtPosition);
        String date = getStringFromEdt(mBinding.edtDate);
        String gender = getStringFromEdt(mBinding.edtGender);
        String phoneNumber = getStringFromEdt(mBinding.edtPhoneNumber);
        String email = getStringFromEdt(mBinding.edtEmail);
        String address = getStringFromEdt(mBinding.edtAddress);

        if (validateImage() &&
                validateName(name) &&
                validatePosition(position) &&
                validateDate(date) &&
                validateGender(gender) &&
                validatePhoneNumber(phoneNumber) &&
                validateEmail(email) &&
                validateAddress(address)) {

            Info info = new Info(mImageUri, name, position, gender, address, email, phoneNumber, date);
            LocalCVManager.getInstance().setInfo(info);
            mNavController.popBackStack();
        }
    }

    private boolean validateImage() {
        if (mImageUri == null) {
            makeToast(requireContext(), "Vui lòng chọn ảnh đại diện");
            return false;
        }
        return true;
    }

    private boolean validateName(String name) {
        if (name.isEmpty()) {
            mBinding.edtName.setError("Vui lòng nhập tên của bạn");
            return false;
        }
        return true;
    }

    private boolean validatePosition(String position) {
        if (position.isEmpty()) {
            mBinding.edtPosition.setError("Vui lòng nhập vị trí muốn ứng tuyển");
            return false;
        }
        return true;
    }

    private boolean validateDate(String date) {
        if (date.isEmpty()) {
            mBinding.edtDate.setError("Vui lòng chọn ngày sinh");
            return false;
        }
        return true;
    }

    private boolean validateGender(String gender) {
        if (gender.isEmpty()) {
            makeToast(requireContext(), "Vui lòng chọn giới tính");
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            makeToast(requireContext(), "Vui lòng nhập điện thoại của bạn");
            return false;
        }

        if (!validNumberPhone(phoneNumber)) {
            makeToast(requireContext(), "Vui lòng nhập số điện thoại đúng định dạng");
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            makeToast(requireContext(), "Vui lòng nhập email của bạn");
            return false;
        }

        if (!validEmail(email)) {
            makeToast(requireContext(), "Vui lòng nhập email đúng định dạng");
            return false;
        }
        return true;
    }

    private boolean validateAddress(String address) {
        if (address.isEmpty()) {
            makeToast(requireContext(), "Vui lòng nhập địa chỉ của bạn");
            return false;
        }
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEdtDate() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        mBinding.edtDate.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showDatePopup(year, month, day);
                return true;
            }
            return false;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEdtGender() {
        mBinding.edtGender.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showGenderPopup();
                return true;
            }
            return false;
        });
    }

    private void showGenderPopup() {
        String[] arr = getResources().getStringArray(R.array.gender);
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setSingleChoiceItems(arr, -1, (dialog, which) -> {
                    mGender = arr[which];
                })
                .setNegativeButton("Hủy", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("Chọn", (dialog, which) -> {
                    mBinding.edtGender.setText(mGender);
                    dialog.dismiss();
                })
                .create();
        alertDialog.show();
    }

    private void showDatePopup(int year, int month, int day) {
        @SuppressLint("SetTextI18n")
        DatePickerDialog.OnDateSetListener listener = (view, year1, month1, dayOfMonth) -> {
            mBinding.edtDate.setText(dayOfMonth + "-" + (month1 + 1) + "-" + year1);
        };
        DatePickerDialog dialog = new DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}