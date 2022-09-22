package com.example.app_supportpolywork.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.model.Tab_BaiViet;

import java.util.ArrayList;
import java.util.List;


public class ThemBaiViet_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_bai_viet,container,false);
        return view;
    }

}