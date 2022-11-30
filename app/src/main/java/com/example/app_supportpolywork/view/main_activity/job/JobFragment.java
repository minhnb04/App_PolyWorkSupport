package com.example.app_supportpolywork.view.main_activity.job;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.example.app_supportpolywork.BaseFragment;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.dummy.JobDummy;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.data.network.JobManager;
import com.example.app_supportpolywork.databinding.FragmentJobBinding;
import com.example.app_supportpolywork.util.CommonUtil;
import com.example.app_supportpolywork.util.TaskListener;
import com.example.app_supportpolywork.view.main_activity.MainActivity;

import java.util.List;


public class JobFragment extends BaseFragment implements JobAdapter.JobAdapterListener {

    private FragmentJobBinding mBinding;
    private JobAdapter mJobAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentJobBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).openBottomNav();
        setupJobs();
        setupSearchJobs();
    }

    private void setupSearchJobs() {
        mBinding.edtSearchText.setOnClickListener(v ->
                mNavController.navigate(R.id.action_jobFragment_to_jobSearchingFragment));
    }

    private void setupJobs() {
        mJobAdapter = new JobAdapter(this);
        mBinding.rcvJobs.setAdapter(mJobAdapter);
        JobManager.getInstance().getJob(new TaskListener() {
            @Override
            public void onSuccess(Object o) {
                mJobAdapter.submitList((List<Job>) o);
            }

            @Override
            public void onError(Exception e) {
                CommonUtil.makeToast(requireContext(), e.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onClickJobItem(Job job) {
        JobFragmentDirections.ActionJobFragmentToJobDetailFragment action =
                JobFragmentDirections.actionJobFragmentToJobDetailFragment(job);
        mNavController.navigate((NavDirections) action);
    }
}