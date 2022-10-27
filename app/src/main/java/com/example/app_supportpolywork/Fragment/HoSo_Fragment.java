package com.example.app_supportpolywork.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.CV_Detail;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.Thong_Tin_Ca_Nhan_Activity;


public class HoSo_Fragment extends Fragment {
    LinearLayout lThongTin,lcv;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ho_so, container, false);
           lThongTin = view.findViewById(R.id.linearThongtin);
            lcv = view.findViewById(R.id.linearCV);
            lThongTin.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), Thong_Tin_Ca_Nhan_Activity.class);
                startActivity(intent);
            });

            lcv.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), CV_Detail.class);
                startActivity(intent);
            });
        return view;
    }
}