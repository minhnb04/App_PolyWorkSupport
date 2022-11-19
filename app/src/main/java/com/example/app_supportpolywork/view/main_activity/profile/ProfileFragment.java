package com.example.app_supportpolywork.view.main_activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.view.activity.ProfileActivity;


public class ProfileFragment extends Fragment {
    LinearLayout lThongTin,lcv;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
           lThongTin = view.findViewById(R.id.linearThongtin);
            lcv = view.findViewById(R.id.linearCV);
            lThongTin.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            });

            lcv.setOnClickListener(v -> {

            });
        return view;
    }
}