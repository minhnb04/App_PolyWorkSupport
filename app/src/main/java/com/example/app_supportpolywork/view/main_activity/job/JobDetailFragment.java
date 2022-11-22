package com.example.app_supportpolywork.view.main_activity.job;

import static com.example.app_supportpolywork.util.AdapterUtil.getJobSalary;
import static com.example.app_supportpolywork.util.AdapterUtil.getNeededPeople;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.databinding.FragmentJobDetailBinding;
import com.example.app_supportpolywork.view.main_activity.MainActivity;
import com.squareup.picasso.Picasso;


public class JobDetailFragment extends BaseFragment {

    private FragmentJobDetailBinding mBinding;
    private Job mCurrentJob;

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
        setupFavoriteBtn();
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
        mBinding.tvNumberOfPeople.setText(getNeededPeople(mCurrentJob.getStartNeededNumberOfPeople(), mCurrentJob.getEndNeededNumberOfPeople()));
        mBinding.tvJobDescription.setText(mCurrentJob.getDescription());
        mBinding.tvBenefits.setText(mCurrentJob.getBenefits());
        mBinding.tvRequirement.setText(mCurrentJob.getRequirement());
        mBinding.tvGender.setText(mCurrentJob.getGender());
        mBinding.tvExperience.setText(mCurrentJob.getExperience());
        mBinding.tvPosition.setText(mCurrentJob.getPosition());
    }

    private void setupFavoriteBtn() {
        mBinding.toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.favorite) {
                favoriteJob();
                return true;
            }
            return false;
        });
    }

    private void favoriteJob() {

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
}