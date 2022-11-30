package com.example.app_supportpolywork.view.main_activity.cv;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.CV;
import com.example.app_supportpolywork.data.network.CVManager;
import com.example.app_supportpolywork.databinding.FragmentCvItemBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.view.main_activity.MainActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class CvItemFragment extends BaseFragment {

    private FragmentCvItemBinding mBinding;
    private CV mCV;

    private AlertDialog updateCVDialog;
    private final TaskListener mTaskUpdateCV = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            CVManager.getInstance().getCV(mCV.getId(), new TaskListener() {
                @Override
                public void onSuccess(Object o) {
                    updateCVDialog.dismiss();
                    mCV = (CV) o;
                    mBinding.tvCvTitle.setText(mCV.getTitle());
                }

                @Override
                public void onError(Exception e) {
                    CommonUtil.makeToast(requireContext(), e.getMessage());
                }
            });
        }

        @Override
        public void onError(Exception e) {
            CommonUtil.makeToast(requireContext(), e.getMessage());
        }
    };

    private AlertDialog deleteCVDialog;
    private final TaskListener mDeleteCVTask = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            deleteCVDialog.dismiss();
            mNavController.popBackStack();
        }

        @Override
        public void onError(Exception e) {
            CommonUtil.makeToast(requireContext(), e.getMessage());
        }
    };


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCvItemBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupCvPdf();
        setupEditBtn();
        setupDeleteBtn();
    }

    private void setupToolbar() {
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.popBackStack());
    }

    private void setupEditBtn() {
        mBinding.imvEdit.setOnClickListener(v -> {
            updateCVDialog = new AlertDialog.Builder(requireContext())
                    .setView(R.layout.layout_edit_cv_item)
                    .setCancelable(false)
                    .create();

            updateCVDialog.show();

            TextInputLayout edtCVTitle = updateCVDialog.findViewById(R.id.edtCvTitle);
            Button btnOK = updateCVDialog.findViewById(R.id.btnOK);
            Button btnCancel = updateCVDialog.findViewById(R.id.btnCancel);

            btnCancel.setOnClickListener(v1 -> updateCVDialog.dismiss());
            btnOK.setOnClickListener(btnO -> {
                String title = CommonUtil.getStringFromEdt(edtCVTitle);
                if (title.isEmpty()) {
                    CommonUtil.makeToast(requireContext(), "Vui lòng nhập tên cv");
                    return;
                }
                mCV.setTitle(title);
                CVManager.getInstance().updateCV(mCV, mTaskUpdateCV);
            });

        });
    }

    private void setupDeleteBtn() {
        mBinding.imvDelete.setOnClickListener(v -> {
            deleteCVDialog = new AlertDialog.Builder(requireContext())
                    .setTitle("Xóa CV")
                    .setMessage("Bạn có chắc chắn muốn xóa CV này?")
                    .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        CVManager.getInstance().deleteCV(mCV, mDeleteCVTask);
                    })
                    .setCancelable(false)
                    .create();
            deleteCVDialog.show();
        });
    }

    private void setupCvPdf() {
        mCV = CvItemFragmentArgs.fromBundle(requireArguments()).getCV();
        loadImageFromNetwork(mBinding.PDFView, mCV.getImage());
        mBinding.tvCvTitle.setText(mCV.getTitle());
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) requireActivity()).closeBottomNav();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    private void loadImageFromNetwork(PDFView pdfView, String _url) {
        mBinding.progressCircular.setVisibility(View.VISIBLE);
        new Thread(() -> {
            InputStream inputStream = null;

            try {
                inputStream = new URL(_url).openConnection().getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    mBinding.progressCircular.post(() -> mBinding.progressCircular.setVisibility(View.GONE));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            try {
                InputStream finalInputStream = inputStream;
                pdfView.post(() -> {
                    pdfView.fromStream(finalInputStream).load();
                    mBinding.progressCircular.setVisibility(View.GONE);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}