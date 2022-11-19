package com.example.app_supportpolywork.view.fragment.cv_set_fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.databinding.FragmentIntroBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class IntroFragment extends CVSetBaseFragment {

    private static final String TAG = "IntroFragment";
    private FragmentIntroBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentIntroBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnStart.setOnClickListener(v -> {
            navigate(PersonalInfoFragment.class);
        });
    }


    private static final int PICK_PDF_FILE = 2;

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, PICK_PDF_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        if (requestCode == PICK_PDF_FILE
                && resultCode == Activity.RESULT_OK) {
            Uri uri;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i(TAG, "onActivityResult: " + uri);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                StorageReference cvRef = storageRef.child("cv/" + System.currentTimeMillis() + ".pdf");
                cvRef.putFile(uri).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        CommonUtil.makeToast(requireContext(), "Success");
                    } else {
                        CommonUtil.makeToast(requireContext(), "Fail");
                    }
                });
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}