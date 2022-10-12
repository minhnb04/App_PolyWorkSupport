package com.example.app_supportpolywork.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.R;


public class ChiTietBaiViet_Fragment extends Fragment {
    private static final String ARG_PARAM1="mP1";
    private String mP1;
    public ChiTietBaiViet_Fragment(){

    }

    public static ChiTietBaiViet_Fragment newInstance(String mP1) {
        ChiTietBaiViet_Fragment fragment = new ChiTietBaiViet_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,mP1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mP1 =getArguments().getString(ARG_PARAM1);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_bai_viet_, container, false);
        TextView textView = view.findViewById(R.id.itemTenTieuDe2);
            textView.setText(ARG_PARAM1);
        return view;
    }
}