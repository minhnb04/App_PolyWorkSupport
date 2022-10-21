package com.example.app_supportpolywork.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.Add_new_cv;
import com.example.app_supportpolywork.R;


public class CV_Fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cv,container,false);
        Button button =  view.findViewById(R.id.btnTaoCV);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Add_new_cv.class);
            startActivity(intent);
        });
        return view;
    }

}