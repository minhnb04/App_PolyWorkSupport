package com.example.app_supportpolywork.view.main_activity.cv;

import static com.example.app_supportpolywork.util.CommonUtil.getStringFromEdt;
import static com.example.app_supportpolywork.util.CommonUtil.hideKeyboard;
import static com.example.app_supportpolywork.util.CommonUtil.makeToast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.data.network.CVManager;
import com.example.app_supportpolywork.databinding.FragmentUploadCvBinding;
import com.example.app_supportpolywork.util.ShareFileUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.util.UploadImageUtil;
import com.example.app_supportpolywork.view.main_activity.MainActivity;


public class UploadCvFragment extends BaseFragment {

    private FragmentUploadCvBinding mBinding;
    private Uri mPDFUri;
    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            mPDFUri = result.getData().getData();
            mBinding.fileName.setText(getFileName(mPDFUri));
        }
    });

    private final TaskListener mOnUploadCV = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            mProgressDialog.dismiss();
            makeToast(requireContext(), "Tải CV lên thành công");
            mNavController.popBackStack();
        }

        @Override
        public void onError(Exception e) {
            mProgressDialog.dismiss();
            makeToast(requireContext(), e.getMessage());
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentUploadCvBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).closeBottomNav();
        setupToolbar();
        receiveData();
        setupBtnUpload();
        setupUploadFile();
    }

    private void setupToolbar() {
        mBinding.toolbar.setNavigationOnClickListener(v -> {
            mNavController.popBackStack();
        });
    }

    private void receiveData() {
        Uri uri = UploadCvFragmentArgs.fromBundle(requireArguments()).getUri();
        if (uri != null) {
            mPDFUri = uri;
            mBinding.fileName.setText(getFileName(mPDFUri));
        }
    }

    private void setupUploadFile() {
        mBinding.upload.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            mGetContent.launch(intent);
        });
    }

    private void setupBtnUpload() {
        mBinding.btnUploadCv.setOnClickListener(v -> {
            hideKeyboard(requireActivity());
            mBinding.edtFileName.setError(null);
            String title = getStringFromEdt(mBinding.edtFileName);
            if (title.isEmpty()) {
                mBinding.edtFileName.setError("Vui lòng nhập tên CV của bạn");
                return;
            }

            User user = ShareFileUtil.getUser(requireContext());
            if (user == null) {
                makeToast(requireContext(), "Vui lòng đăng nhập để tải CV của bạn");
                return;
            }

            mProgressDialog.setMessage("Đang tải CV lên ...");
            mProgressDialog.show();

            UploadImageUtil.uploadImage(mPDFUri, new TaskListener() {
                @Override
                public void onSuccess(Object o) {
                    String link = (String) o;
                    CVManager.getInstance().postCV(title, link, user.getId(), mOnUploadCV);
                }

                @Override
                public void onError(Exception e) {
                    mProgressDialog.dismiss();
                    makeToast(requireContext(), e.getMessage());
                }
            });
        });
    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = requireActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
        ((MainActivity) requireActivity()).openBottomNav();
    }
}