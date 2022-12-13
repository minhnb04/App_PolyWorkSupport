package com.example.app_supportpolywork.view.main_activity.cv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.CV;
import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.data.network.CVManager;
import com.example.app_supportpolywork.databinding.FragmentCvBinding;
import com.example.app_supportpolywork.util.ShareFileUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.view.main_activity.MainActivity;

import java.util.List;


public class CvFragment extends BaseFragment implements CvItemAdapter.OnClickCVItemListener {

    private FragmentCvBinding mBinding;

    private Animation mRotateOpenAnim;
    private Animation mRotateCloseAnim;
    private Animation mFromBottomAnim;
    private Animation mToBottomAnim;

    private boolean mClicked;

    private final TaskListener mGetCVListTask = new TaskListener() {
        @Override
        public void onSuccess(Object o) {
            List<CV> cvList = (List<CV>) o;
            mCvItemAdapter.submitList(cvList);
        }

        @Override
        public void onError(Exception e) {
//            makeToast(requireContext(), e.getMessage());
        }
    };

    private CvItemAdapter mCvItemAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentCvBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAnimations();
        setupAddCvBtn();
        setupNewCvBtn();
        setupUploadCvBtn();
        setupCvs();
    }

    private void initAnimations() {
        mRotateOpenAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim);
        mRotateCloseAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim);
        mFromBottomAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim);
        mToBottomAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim);
    }

    private void setupAddCvBtn() {
        mClicked = false;
        mBinding.btnAddCv.setOnClickListener(v -> {
            mClicked = !mClicked;
            setVisibility();
            setAnimation();
        });
    }

    private void setAnimation() {
        if (mClicked) {
            mBinding.btnNewCv.setAnimation(mFromBottomAnim);
            mBinding.btnUploadCv.setAnimation(mFromBottomAnim);
            mBinding.btnAddCv.setAnimation(mRotateOpenAnim);
        } else {
            mBinding.btnNewCv.setAnimation(mToBottomAnim);
            mBinding.btnUploadCv.setAnimation(mToBottomAnim);
            mBinding.btnAddCv.setAnimation(mRotateCloseAnim);
        }
    }

    private void setVisibility() {
        if (mClicked) {
            mBinding.btnUploadCv.setVisibility(View.GONE);
            mBinding.btnNewCv.setVisibility(View.GONE);
        } else {
            mBinding.btnUploadCv.setVisibility(View.VISIBLE);
            mBinding.btnNewCv.setVisibility(View.VISIBLE);
        }
    }

    private void setupUploadCvBtn() {
        mBinding.btnUploadCv.setOnClickListener(v -> {
            mNavController.navigate(R.id.action_cvFragment_to_uploadCvFragment);
        });
    }

    private void setupNewCvBtn() {
        mBinding.btnNewCv.setOnClickListener(v -> mNavController.navigate(R.id.action_cvFragment_to_cvTemplateFragment));
    }

    private void setupCvs() {
        mCvItemAdapter = new CvItemAdapter(this);
        mBinding.rcvCV.setAdapter(mCvItemAdapter);
        User user = ShareFileUtil.getUser(requireContext());
        CVManager.getInstance().getCVListOfUser(user.getId(), mGetCVListTask);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) requireActivity()).openBottomNav();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onClickItem(CV cv) {
        mNavController.navigate(
                CvFragmentDirections.actionCvFragmentToCvItemFragment(cv)
        );
    }
}