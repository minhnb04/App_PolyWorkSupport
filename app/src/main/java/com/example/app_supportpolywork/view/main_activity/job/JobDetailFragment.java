package com.example.app_supportpolywork.view.main_activity.job;

import static com.example.app_supportpolywork.util.AdapterUtil.getJobSalary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.CV;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.data.network.CVManager;
import com.example.app_supportpolywork.data.network.JobManager;
import com.example.app_supportpolywork.databinding.FragmentJobDetailBinding;
import com.example.app_supportpolywork.util.AdapterUtil;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.ShareFileUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.view.main_activity.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.List;


public class JobDetailFragment extends BaseFragment implements CvItemAdapter.OnClickCVItemListener {

    private FragmentJobDetailBinding mBinding;
    private Job mCurrentJob;
    private List<CV> mCvList;
    private BottomSheetDialog mBottomSheetDialog;
    private RecyclerView mRecycleCVList;
    private CvItemAdapter mCvItemAdapter;
    private Button btnApply;
    private CV mSelectedCV;
    private final TaskListener mApplyCvTask = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            mBinding.btnApply.setEnabled(false);
            mBottomSheetDialog.hide();
            CommonUtil.makeToast(requireContext(), "Gửi CV thành công");
        }

        @Override
        public void onError(Exception e) {
            CommonUtil.makeToast(requireContext(), "Gửi CV xảy ra lỗi, vui lòng kiểm tra lại");
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity) requireActivity()).closeBottomNav();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentJobDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupBackBtn();
        setupJobInfo();
        setupApplyJobBtn();
        loadCV();
    }

    private void loadCV() {
        CVManager.getInstance().getCVListOfUser(ShareFileUtil.getUser(requireContext()).getId(), new TaskListener() {
            @Override
            public void onSuccess(Object o) {
                mCvList = (List<CV>) o;
                View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_cv_list, null);
                mBottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
                mBottomSheetDialog.setContentView(view);

                mRecycleCVList = mBottomSheetDialog.findViewById(R.id.rcvCV);
                mCvItemAdapter = new CvItemAdapter(JobDetailFragment.this);
                mCvItemAdapter.submitList(mCvList);
                mRecycleCVList.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
                mRecycleCVList.setAdapter(mCvItemAdapter);

                btnApply = mBottomSheetDialog.findViewById(R.id.btnApply);
                btnApply.setOnClickListener(v -> {
                    if (mSelectedCV == null) {
                        CommonUtil.makeToast(requireContext(), "Vui lòng chọn CV để ứng tuyển");
                        return;
                    }
                    JobManager.getInstance().applyJob(
                            ShareFileUtil.getUser(requireContext()).getId(),
                            mSelectedCV.getId(),
                            mCurrentJob.getId(),
                            mApplyCvTask
                    );
                });
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setupApplyJobBtn() {
        mBinding.btnApply.setOnClickListener(v -> {
            mBottomSheetDialog.show();
        });
    }

    private void setupBackBtn() {
        mBinding.toolbar.setNavigationOnClickListener(v -> {
            mNavController.popBackStack();
        });
    }

    private void setupJobInfo() {
        mCurrentJob = JobDetailFragmentArgs.fromBundle(getArguments()).getJob();

        Picasso.get()
                .load(mCurrentJob.getAvatar())
                .placeholder(R.drawable.img_sample)
                .error(R.drawable.img_sample)
                .into(mBinding.imvJobAvatar);

        mBinding.tvJobTitle.setText(mCurrentJob.getTitle());
        mBinding.tvSalary.setText(getJobSalary(mCurrentJob.getStartSalary(), mCurrentJob.getEndSalary()));
        mBinding.tvWorkForm.setText(mCurrentJob.getWorkForm());
        mBinding.tvWorkPlace.setText(mCurrentJob.getWorkPlace());
        mBinding.tvNumberOfPeople.setText(mCurrentJob.getSlot());
        mBinding.tvJobDescription.setText(mCurrentJob.getDescription());
        mBinding.tvBenefits.setText(mCurrentJob.getBenefits());
        mBinding.tvRequirement.setText(mCurrentJob.getRequirement());
        mBinding.tvExperience.setText(mCurrentJob.getExperience());
        mBinding.tvPosition.setText(mCurrentJob.getPosition());

        int gender = -1;
        try {
            gender = Integer.parseInt(mCurrentJob.getGender());
        } catch (Exception ignored) {
        }
        mBinding.tvGender.setText(AdapterUtil.getGenderFromCode(gender));
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) requireActivity()).openBottomNav();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onClickItem(CV cv) {
        mSelectedCV = cv;
    }


    @Override
    public void onWatch(CV cv) {

    }
}