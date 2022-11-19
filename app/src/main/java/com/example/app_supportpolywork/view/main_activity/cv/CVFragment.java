package com.example.app_supportpolywork.view.main_activity.cv;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.databinding.FragmentCvBinding;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.view.activity.AddCVActivity;


public class CVFragment extends Fragment {
    
    private FragmentCvBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentCvBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
        setupRcvCV();
    }

    private void setupToolbar() {
        mBinding.toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.add_cv) {
                startActivity(new Intent(requireContext(), AddCVActivity.class));
            }
            return true;
        });
    }

    private void setupRcvCV() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}