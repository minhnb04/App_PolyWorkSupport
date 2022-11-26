package com.example.app_supportpolywork.view.main_activity.cv;

import static androidx.core.content.FileProvider.getUriForFile;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.app_supportpolywork.App;
import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.manager.LocalCVManager;
import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.data.model.cv_model.Education;
import com.example.app_supportpolywork.data.model.cv_model.Experience;
import com.example.app_supportpolywork.data.model.cv_model.Info;
import com.example.app_supportpolywork.databinding.FragmentCreateCvRootBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.ShareFileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CreateCVFragment extends BaseFragment {

    private static final String TAG = "CreateCVFragment";
    private boolean mUpload;

    private FragmentCreateCvRootBinding mBinding;
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    createPdf(mUpload);
                } else {
                    CommonUtil.makeToast(requireContext(), "Bạn cần cung cấp quyền để thực hiện thao tác này");
                }
            });


    private Bitmap bitmap;

    // View of ViewStub (CV View)
    private ConstraintLayout page;
    private TextView tvExperienceTime;
    private TextView tvExperienceTime2;
    private View info;
    private ImageView imvAvatar;
    private TextView tvName;
    private TextView tvPosition;
    private TextView tvGender;
    private TextView tvAddress;
    private TextView tvBirthday;
    private TextView tvEmail;
    private TextView tvPhoneNumber;
    private TextView tvObjectiveTitle;
    private TextView tvObjective;
    private TextView tvEducationTitle;
    private TextView tvEducationYear;
    private TextView tvEducationSchool;
    private TextView tvExperienceCompany;
    private TextView tvExperienceCompany2;
    private TextView tvCertification;
    private TextView tvMajor;
    private TextView tvExperiencePosition;
    private TextView tvExperiencePosition2;
    private TextView tvExperienceDescription;
    private TextView tvSkill;
    private TextView tvActive;
    private TextView tvIntersting;
    private TextView tvExperienceDescription2;
    private TextView tvDegree;
    private TextView tvTypeDegree;
    private TextView tvExperienceTitle;
    private TextView tvSkillTitle;
    private TextView tvActiveTitle;
    private TextView tvInterestingTitle;
    private TextView tvCertificationTitle;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreateCvRootBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        initCvView();
        fillPDFContent();
        setupPDFContent();
    }


    private void initCvView() {
        mBinding.viewStub.setLayoutResource(
                CreateCVFragmentArgs.fromBundle(requireArguments()).getCvTemplate()
        );
        View inflater = mBinding.viewStub.inflate();
        page = inflater.findViewById(R.id.page);
        tvExperienceTime = inflater.findViewById(R.id.tvExperienceTime);
        tvExperienceTime2 = inflater.findViewById(R.id.tvExperienceTime2);
        info = inflater.findViewById(R.id.info);
        imvAvatar = inflater.findViewById(R.id.imvAvatar);
        tvName = inflater.findViewById(R.id.tvName);
        tvPosition = inflater.findViewById(R.id.tvPosition);
        tvGender = inflater.findViewById(R.id.tvGender);
        tvAddress = inflater.findViewById(R.id.tvAddress);
        tvBirthday = inflater.findViewById(R.id.tvBirthday);
        tvEmail = inflater.findViewById(R.id.tvEmail);
        tvPhoneNumber = inflater.findViewById(R.id.tvPhoneNumber);
        tvObjectiveTitle = inflater.findViewById(R.id.tvObjectiveTitle);
        tvObjective = inflater.findViewById(R.id.tvObjective);
        tvEducationTitle = inflater.findViewById(R.id.tvEducationTitle);
        tvEducationYear = inflater.findViewById(R.id.tvEducationYear);
        tvEducationSchool = inflater.findViewById(R.id.tvEducationSchool);
        tvExperienceCompany = inflater.findViewById(R.id.tvExperienceCompany);
        tvExperienceCompany2 = inflater.findViewById(R.id.tvExperienceCompany2);
        tvCertification = inflater.findViewById(R.id.tvCertification);
        tvMajor = inflater.findViewById(R.id.tvMajor);
        tvExperiencePosition = inflater.findViewById(R.id.tvExperiencePosition);
        tvExperiencePosition2 = inflater.findViewById(R.id.tvExperiencePosition2);
        tvExperienceDescription = inflater.findViewById(R.id.tvExperienceDescription);
        tvSkill = inflater.findViewById(R.id.tvSkill);
        tvActive = inflater.findViewById(R.id.tvActive);
        tvIntersting = inflater.findViewById(R.id.tvIntersting);
        tvExperienceDescription2 = inflater.findViewById(R.id.tvExperienceDescription2);
        tvDegree = inflater.findViewById(R.id.tvDegree);
        tvTypeDegree = inflater.findViewById(R.id.tvTypeDegree);
        tvExperienceTitle = inflater.findViewById(R.id.tvExperienceTitle);
        tvSkillTitle = inflater.findViewById(R.id.tvSkillTitle);
        tvActiveTitle = inflater.findViewById(R.id.tvActiveTitle);
        tvInterestingTitle = inflater.findViewById(R.id.tvInterestingTitle);
        tvCertificationTitle = inflater.findViewById(R.id.tvCertificationTitle);
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    private void fillPDFContent() {
        Info info = LocalCVManager.getInstance().getInfo();
        if (info != null) {
            imvAvatar.setImageURI(info.getImage());
            tvName.setText(info.getName());
            tvPosition.setText(info.getPosition());
            tvGender.setText(info.getGender());
            tvAddress.setText(info.getAddress());
            tvEmail.setText(info.getEmail());
            tvPhoneNumber.setText(info.getPhoneNumber());
            tvBirthday.setText(info.getDate());
        }


        String objective = LocalCVManager.getInstance().getObjective();
        if (objective != null) {
            tvObjective.setText(objective);
        }

        String skill = LocalCVManager.getInstance().getSkill();
        if (skill != null) {
            tvSkill.setText(skill);
        }

        String active = LocalCVManager.getInstance().getActive();
        if (active != null) {
            tvActive.setText(active);
        }

        String interesting = LocalCVManager.getInstance().getInteresting();
        if (interesting != null) {
            tvIntersting.setText(interesting);
        }

        String certification = LocalCVManager.getInstance().getCertification();
        if (certification != null) {
            tvCertification.setText(certification);
        }

        Education education = LocalCVManager.getInstance().getEducation();
        if (education != null) {
            tvEducationSchool.setText(education.getUniversityName());
            tvEducationYear.setText(education.getStartTime() + " - " + education.getEndTime());
            tvMajor.setText("Chuyên ngành: " + education.getMajor());
            tvDegree.setText("Bằng cấp: " + education.getDegreeType());
            tvTypeDegree.setText("Tốt nghiệp loại: " + education.getDegreeRank());
        }

        List<Experience> experiences = LocalCVManager.getInstance().getExperiences();
        if (experiences != null) {
            if (experiences.size() == 1) {
                Experience experience1 = experiences.get(0);
                if (experience1 != null) {
                    tvExperienceCompany.setText(experience1.getCompanyName());
                    tvExperienceTime.setText(experience1.getStartTime() + " - " + experience1.getEndTime());
                    tvExperiencePosition.setText(experience1.getTitle());
                    tvExperienceDescription.setText(experience1.getDescription());
                }

                tvExperienceCompany2.setText("");
                tvExperienceTime2.setText("");
                tvExperiencePosition2.setText("");
                tvExperienceDescription2.setText("");
            } else if (experiences.size() == 2) {
                Experience experience1 = experiences.get(0);
                if (experience1 != null) {
                    tvExperienceCompany.setText(experience1.getCompanyName());
                    tvExperienceTime.setText(experience1.getStartTime() + " - " + experience1.getEndTime());
                    tvExperiencePosition.setText(experience1.getTitle());
                    tvExperienceDescription.setText(experience1.getDescription());
                }

                Experience experience2 = experiences.get(1);
                if (experience2 != null) {
                    tvExperienceCompany2.setText(experience2.getCompanyName());
                    tvExperienceTime2.setText(experience2.getStartTime() + " - " + experience2.getEndTime());
                    tvExperiencePosition2.setText(experience2.getTitle());
                    tvExperienceDescription2.setText(experience2.getDescription());
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
        tvCertificationTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_certificate);
        });
    }

    private void setupActive() {
        tvActiveTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_activeFragment);
        });
    }

    private void setupEducation() {
        tvEducationTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_educationFragment);
        });
    }

    private void setupExperience() {
        tvExperienceTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_experienceFragment);
        });
    }

    private void setupInteresting() {
        tvInterestingTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_interestingFragment);
        });
    }

    private void setupSkill() {
        tvSkillTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_skillFragment);
        });
    }

    private void setupObjective() {
        tvObjectiveTitle.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_createCVFragment_to_objectiveFragment);
        });
    }

    private void setupInfo() {
        info.setOnClickListener(v -> {
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
        String fileName = System.currentTimeMillis() + ".pdf";
        User user = ShareFileUtil.getUser(requireContext());
        if (user != null) {
            fileName = user.getUserName() + "_" + System.currentTimeMillis() + ".pdf";
        }
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
        File newFile = new File(dir, fileName);

        Log.i(TAG, "createPdf: " + newFile.getAbsolutePath());
        boolean isSuccess = true;

        try {
            pdfDocument.writeTo(new FileOutputStream(newFile));
        } catch (IOException ioException) {
            isSuccess = false;
            Toast.makeText(requireContext(), App.ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();

        if (isSuccess) {
            if (upload) {
                Uri contentUri = getUriForFile(requireContext(), "com.example.app_supportpolywork.fileprovider", newFile);
                mNavController.navigate(
                        CreateCVFragmentDirections.actionCreateCVFragmentToUploadCvFragment().setUri(contentUri)
                );
            } else {
                Toast.makeText(requireContext(), "Tải xuống máy thành công", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private Bitmap loadBitmapFromView() {
        bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        page.draw(canvas);
        return bitmap;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }


}