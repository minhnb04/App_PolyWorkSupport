package com.example.app_supportpolywork.view.main_activity.cv;

import static androidx.core.content.FileProvider.getUriForFile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.manager.CVManager;
import com.example.app_supportpolywork.data.model.cv_model.Education;
import com.example.app_supportpolywork.data.model.cv_model.Experience;
import com.example.app_supportpolywork.data.model.cv_model.Info;
import com.example.app_supportpolywork.databinding.FragmentCreateCvBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.util.UploadImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreateCVFragment extends BaseFragment {

    private static final String TAG = "CreateCVFragment";
    private boolean mUpload;

    private FragmentCreateCvBinding mBinding;
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    createPdf(mUpload);
                } else {
                    CommonUtil.makeToast(requireContext(), "Bạn cần cung cấp quyền để thực hiện thao tác này");
                }
            });


    private Bitmap bitmap;
    private Uri contentUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreateCvBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
        fillPDFContent();
        setupPDFContent();

    }

    @SuppressLint("SetTextI18n")
    private void fillPDFContent() {
        Info info = CVManager.getInstance().getInfo();
        if (info != null) {
            mBinding.imvAvatar.setImageURI(info.getImage());
            mBinding.tvName.setText(info.getName());
            mBinding.tvPosition.setText(info.getPosition());
            mBinding.tvGender.setText(info.getGender());
            mBinding.tvAddress.setText(info.getAddress());
            mBinding.tvEmail.setText(info.getEmail());
            mBinding.tvPhoneNumber.setText(info.getPhoneNumber());
            mBinding.tvBirthday.setText(info.getDate());
        }


        String objective = CVManager.getInstance().getObjective();
        if (objective != null) {
            mBinding.tvObjective.setText(objective);
        }

        String skill = CVManager.getInstance().getSkill();
        if (skill != null) {
            mBinding.tvSkill.setText(skill);
        }

        String active = CVManager.getInstance().getActive();
        if (active != null) {
            mBinding.tvActive.setText(active);
        }

        String interesting = CVManager.getInstance().getInteresting();
        if (interesting != null) {
            mBinding.tvIntersting.setText(interesting);
        }

        String certification = CVManager.getInstance().getCertification();
        if (certification != null) {
            mBinding.tvCertification.setText(certification);
        }

        Education education = CVManager.getInstance().getEducation();
        if (education != null) {
            mBinding.tvEducationSchool.setText(education.getUniversityName());
            mBinding.tvEducationYear.setText(education.getStartTime() + " - " + education.getEndTime());
            mBinding.tvMajor.setText("Chuyên ngành: " + education.getMajor());
            mBinding.tvDegree.setText("Bằng cấp: " + education.getDegreeType());
            mBinding.tvTypeDegree.setText("Tốt nghiệp loại: " + education.getDegreeRank());
        }

        List<Experience> experiences = CVManager.getInstance().getExperiences();
        if (experiences != null) {
            if (experiences.size() == 1) {
                Experience experience1 = experiences.get(0);
                if (experience1 != null) {
                    mBinding.tvExperienceCompany.setText(experience1.getCompanyName());
                    mBinding.tvExperienceTime.setText(experience1.getStartTime() + " - " + experience1.getEndTime());
                    mBinding.tvExperiencePosition.setText(experience1.getTitle());
                    mBinding.tvExperienceDescription.setText(experience1.getDescription());
                }

                mBinding.tvExperienceCompany2.setText("");
                mBinding.tvExperienceTime2.setText("");
                mBinding.tvExperiencePosition2.setText("");
                mBinding.tvExperienceDescription2.setText("");
            } else if (experiences.size() == 2) {
                Experience experience1 = experiences.get(0);
                if (experience1 != null) {
                    mBinding.tvExperienceCompany.setText(experience1.getCompanyName());
                    mBinding.tvExperienceTime.setText(experience1.getStartTime() + " - " + experience1.getEndTime());
                    mBinding.tvExperiencePosition.setText(experience1.getTitle());
                    mBinding.tvExperienceDescription.setText(experience1.getDescription());
                }

                Experience experience2 = experiences.get(1);
                if (experience2 != null) {
                    mBinding.tvExperienceCompany2.setText(experience2.getCompanyName());
                    mBinding.tvExperienceTime2.setText(experience2.getStartTime() + " - " + experience2.getEndTime());
                    mBinding.tvExperiencePosition2.setText(experience2.getTitle());
                    mBinding.tvExperienceDescription2.setText(experience2.getDescription());
                }
            }

        }
    }

    private void setupPDFContent() {
        setupInfo();
        setupObjective();
        setupSkill();
        setupInteresting();
        setupExperience();
        setupEducation();
        setupActive();
        setupCertification();
    }

    private void setupCertification() {
        mBinding.tvCertificationTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_certificate);
        });
    }

    private void setupActive() {
        mBinding.tvActiveTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_activeFragment);
        });
    }

    private void setupEducation() {
        mBinding.tvEducationTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_educationFragment);
        });
    }

    private void setupExperience() {
        mBinding.tvExperienceTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_experienceFragment);
        });
    }

    private void setupInteresting() {
        mBinding.tvInterestingTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_interestingFragment);
        });
    }

    private void setupSkill() {
        mBinding.tvSkillTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_skillFragment);
        });
    }

    private void setupObjective() {
        mBinding.tvObjectiveTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_objectiveFragment);
        });
    }

    private void setupInfo() {
        mBinding.info.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_personalInfoFragment);
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void setupToolbar() {
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.popBackStack());
        mBinding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.download:
                    mUpload = false;
                    startCreatePDF();
                    break;
                case R.id.upload:
                    mUpload = true;
                    startCreatePDF();
                    break;
            }
            return true;
        });
    }

    private void startCreatePDF() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                createPdf(mUpload);
            } else {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
    }

    private void createPdf(boolean upload) {
        bitmap = loadBitmapFromView();

        int pageHeight = 1120;
        int pageWidth = 792;

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, pageWidth, pageHeight, true);
        canvas.drawBitmap(bitmap, 0, 0, null);
        pdfDocument.finishPage(page);

        File dir = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File newFile = new File(dir, System.currentTimeMillis() + ".pdf");

        Log.i(TAG, "createPdf: " + newFile.getAbsolutePath());

        try {
            pdfDocument.writeTo(new FileOutputStream(newFile));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            Toast.makeText(requireContext(), "Something ", Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();
        if(upload) {
            contentUri = getUriForFile(requireContext(), "com.example.app_supportpolywork.fileprovider", newFile);
            Log.i(TAG, "createPdf: " + contentUri.toString());
            UploadImageUtil.uploadImage(contentUri, new TaskListener() {
                @Override
                public void onSuccess(Object o) {
                    String url = (String) o;
                    Toast.makeText(requireContext(), "Upload thành công " + url, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(requireContext(), "Đã có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(requireContext(), "Tải xuống máy thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap loadBitmapFromView() {
        bitmap = Bitmap.createBitmap(mBinding.page.getWidth(), mBinding.page.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mBinding.page.draw(canvas);
        return bitmap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


}