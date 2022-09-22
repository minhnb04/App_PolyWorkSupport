package com.example.app_supportpolywork.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app_supportpolywork.Adapter.Adapter_tab_baiviet;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.SeachActivity;
import com.example.app_supportpolywork.model.Tab_BaiViet;

import java.util.ArrayList;
import java.util.List;


public class ViecLam_Fragment extends Fragment {
    RecyclerView recyclerView_BaiViet;
    List<Tab_BaiViet> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kham_pha,container,false);
        recyclerView_BaiViet=view.findViewById(R.id.recyclerView_BaiViet);
        list.add(new Tab_BaiViet("Bai Viet 1"));
        list.add(new Tab_BaiViet("Bai Viet 2"));
        list.add(new Tab_BaiViet("Bai Viet 3"));
        list.add(new Tab_BaiViet("Bai Viet 4"));
        recyclerView_BaiViet.setHasFixedSize(true);
        recyclerView_BaiViet.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        Adapter_tab_baiviet adapter_tab_baiviet = new Adapter_tab_baiviet(getActivity().getApplicationContext(),list);
        recyclerView_BaiViet.setAdapter(adapter_tab_baiviet);
        return view;
    }
}