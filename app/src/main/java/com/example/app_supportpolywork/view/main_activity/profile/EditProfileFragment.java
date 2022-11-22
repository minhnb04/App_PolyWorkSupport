package com.example.app_supportpolywork.view.main_activity.profile;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;
import static com.example.app_supportpolywork.util.CommonUtil.hideKeyboard;
import static com.example.app_supportpolywork.util.CommonUtil.makeToast;
import static com.example.app_supportpolywork.util.CommonUtil.validEmail;
import static com.example.app_supportpolywork.util.CommonUtil.validNumberPhone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.databinding.FragmentEditProfileBinding;
import com.example.app_supportpolywork.util.CommonUtil;

public class EditProfileFragment extends BaseFragment {

    private FragmentEditProfileBinding mBinding;
    private String mGender = "Nam";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentEditProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupEditGender();
        setupUserInfo();
        setupBackBtn();
        setupSaveBtn();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEditGender() {
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

    private void setupUserInfo() {
        User user = getUser();
        if (user != null) {
            mBinding.edtName.setText(user.getName());
            mBinding.edtAddress.setText(user.getAddress());
            mBinding.edtEmail.setText(user.getEmail());
            mBinding.edtGender.setText(user.getGender());
            mBinding.edtPhoneNumber.setText(user.getPhoneNumber());
        }
    }

    private User getUser() {
        return new User("0", "lam@gmail.com", "Bùi Thanh Lâm", "0868358175", "Ba Đình, Hà Nội", "Nam");
    }

    private void setupBackBtn() {
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.popBackStack());
    }

    private void setupSaveBtn() {
        mBinding.btnSave.setOnClickListener(v -> {
            hideKeyboard(requireActivity());

            String name = getStringFromEdt(mBinding.edtName);
            String email = getStringFromEdt(mBinding.edtEmail);
            String address = getStringFromEdt(mBinding.edtAddress);
            String phoneNumber = getStringFromEdt(mBinding.edtPhoneNumber);
            String gender = getStringFromEdt(mBinding.edtGender);


            if (validateName(name) &&
                    validateEmail(email) &&
                    validateAddress(address) &&
                    validatePhoneNumber(phoneNumber) &&
                    validateGender(gender)) {

                updateUser(name, email, address, phoneNumber, gender);

            }

        });
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

        if(!validNumberPhone(phoneNumber)) {
            makeToast(requireContext(), "Vui lòng nhập số điện thoại đúng định dạng");
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

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            makeToast(requireContext(), "Vui lòng nhập email của bạn");
            return false;
        }

        if(!validEmail(email)) {
            makeToast(requireContext(), "Vui lòng nhập email đúng định dạng");
            return false;
        }
        return true;
    }

    private boolean validateName(String name) {
        if (name.isEmpty()) {
            makeToast(requireContext(), "Vui lòng nhập tên của bạn");
            return false;
        }
        return true;
    }

    private void updateUser(String name, String email, String address, String phoneNumber, String gender) {
        mProgressDialog.setMessage("Cập nhật thông tin ...");
        mProgressDialog.show();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}