package com.example.app_supportpolywork.view.main_activity.profile;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;
import static com.example.app_supportpolywork.util.CommonUtil.hideKeyboard;
import static com.example.app_supportpolywork.util.CommonUtil.makeToast;
import static com.example.app_supportpolywork.util.CommonUtil.validEmail;
import static com.example.app_supportpolywork.util.CommonUtil.validNumberPhone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.data.network.UserManager;
import com.example.app_supportpolywork.databinding.FragmentEditProfileBinding;
import com.example.app_supportpolywork.util.AdapterUtil;
import com.example.app_supportpolywork.util.ShareFileUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.util.UploadImageUtil;
import com.squareup.picasso.Picasso;

public class EditProfileFragment extends BaseFragment {

    private FragmentEditProfileBinding mBinding;
    private String mGender;

    private Uri mImageUri;
    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mImageUri = result.getData().getData();
            mBinding.imvAvatar.setImageURI(mImageUri);
        } else {
            mImageUri = null;
            mBinding.imvAvatar.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.img_sample, null));
        }
    });

    private final TaskListener mFindUserInfoTask = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            mProgressDialog.dismiss();
            mImageUri = null;
            User user = (User) o;
            if (user != null) {
                Picasso.get().load(user.getImage()).placeholder(R.drawable.img_sample).error(R.drawable.img_sample).into(mBinding.imvAvatar);
                mBinding.edtName.setText(user.getFullName());
                mBinding.edtEmail.setText(user.getEmail());

                if (user.getGender() != -2) {
                    mBinding.edtGender.setText(AdapterUtil.getGenderFromCode(user.getGender()));
                }

                if (user.getPhoneNumber() != null) {
                    mBinding.edtPhoneNumber.setText(user.getPhoneNumber());
                }

                if (user.getAddress() != null) {
                    mBinding.edtAddress.setText(user.getAddress());
                }
                ShareFileUtil.saveUser(requireContext(), user);
            }
        }

        @Override
        public void onError(Exception e) {
            makeToast(requireContext(), e.getMessage());
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentEditProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadInfo();
        setupEditGender();
        setupImvAvatar();
        setupBackBtn();
        setupSaveBtn();
    }

    private void loadInfo() {
        UserManager.getInstance().getInfo(ShareFileUtil.getUser(requireContext()).getId(), mFindUserInfoTask);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupEditGender() {
        mBinding.edtGender.setOnClickListener(v -> {
            showGenderPopup();
        });
    }

    private void showGenderPopup() {
        String[] arr = getResources().getStringArray(R.array.gender);
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setSingleChoiceItems(arr, 0, (dialog, which) -> {
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

    private void setupBackBtn() {
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.popBackStack());
    }

    private void setupImvAvatar() {
        mBinding.imvAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            mGetContent.launch(intent);
        });
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

        if (!validNumberPhone(phoneNumber)) {
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

        if (!validEmail(email)) {
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

    private void updateUser(String fullName, String email, String address, String phoneNumber, String gender) {
        mProgressDialog.setMessage("Cập nhật thông tin ...");
        mProgressDialog.show();

        User user = ShareFileUtil.getUser(requireContext());
        user.setAddress(address);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(AdapterUtil.getCodeFromGender(gender));
        user.setEmail(email);

        if (mImageUri != null) {
            UploadImageUtil.uploadImage(mImageUri, new TaskListener() {
                @Override
                public void onSuccess(Object o) {
                    String imageLink = (String) o;
                    user.setImage(imageLink);
                    UserManager.getInstance().changeProfile(user, new TaskListener() {
                        @Override
                        public void onSuccess(Object o) {
                            UserManager.getInstance().getInfo(user.getId(), mFindUserInfoTask);
                        }

                        @Override
                        public void onError(Exception e) {
                            mProgressDialog.dismiss();
                            makeToast(requireContext(), e.getMessage());
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    mProgressDialog.dismiss();
                    makeToast(requireContext(), e.getMessage());
                }
            });
        } else {
            UserManager.getInstance().changeProfile(user, new TaskListener() {
                @Override
                public void onSuccess(Object o) {
                    UserManager.getInstance().getInfo(user.getId(), mFindUserInfoTask);
                }

                @Override
                public void onError(Exception e) {
                    mProgressDialog.dismiss();
                    makeToast(requireContext(), e.getMessage());
                }
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}