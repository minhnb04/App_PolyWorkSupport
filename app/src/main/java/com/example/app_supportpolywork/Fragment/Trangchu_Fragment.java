package com.example.app_supportpolywork.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app_supportpolywork.Adapter.Adapter_tab_baiviet;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.SeachActivity;
import com.example.app_supportpolywork.model.Tab_BaiViet;


import java.util.ArrayList;
import java.util.List;


public class Trangchu_Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu,container,false);

        return view;
    }
    }
