package com.example.app_supportpolywork.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app_supportpolywork.Adapter.Adapter_tab_baiviet;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.SeachActivity;
import com.example.app_supportpolywork.model.Tab_BaiViet;

import java.util.ArrayList;
import java.util.List;


public class ViecLam_Fragment extends Fragment implements Adapter_tab_baiviet.ItemClick {
    RecyclerView recyclerView_BaiViet;
    List<Tab_BaiViet> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viec_lam, container, false);
        recyclerView_BaiViet = view.findViewById(R.id.recyclerView_BaiViet);
        recyclerView_BaiViet.setHasFixedSize(true);
        recyclerView_BaiViet.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        Adapter_tab_baiviet adapter_tab_baiviet = new Adapter_tab_baiviet(this, list);
        builData();
        recyclerView_BaiViet.setAdapter(adapter_tab_baiviet);
        return view;
    }

    private void builData() {
        list.add(new Tab_BaiViet("Nhân Viên Kinh Doanh - Tư Vấn Bán Hàng - Sales (Thu Nhập Lên Tới 15 Triệu/Tháng)"));
        list.add(new Tab_BaiViet("Bai Viet 2"));
        list.add(new Tab_BaiViet("Nhân Viên Kinh Doanh - Tư Vấn Bán Hàng - Sales (Thu Nhập Lên Tới 15 Triệu/Tháng)"));
        list.add(new Tab_BaiViet("Bai Viet 4"));
    }

    @Override
    public void onclick(Tab_BaiViet tab_baiViet) {
        Fragment fragment = ChiTietBaiViet_Fragment.newInstance(tab_baiViet.getTenbaiviet());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment,"fragment_chi_tiet_bai_viet_");
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public interface ISendata {
        void sendata(Tab_BaiViet tab_baiViet);

    }
}
