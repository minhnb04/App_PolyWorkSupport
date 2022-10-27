package com.example.app_supportpolywork.Fragment;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app_supportpolywork.Adapter.Adapter_tab_baiviet;
import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.model.Tab_BaiViet;

import java.util.ArrayList;
import java.util.List;


public class ViecLam_Fragment extends Fragment   {
    Adapter_tab_baiviet adapter_tab_baiviet;
    RecyclerView recyclerView_BaiViet;
    SearchView searchView;

    List<Tab_BaiViet> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viec_lam,container,false);
        recyclerView_BaiViet=view.findViewById(R.id.recyclerView_BaiViet);
        searchView= view.findViewById(R.id.searchView);
        searchView.findFocus();
        builData();
        recyclerView_BaiViet.setHasFixedSize(true);
        recyclerView_BaiViet.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        Adapter_tab_baiviet adapter_tab_baiviet = new Adapter_tab_baiviet(getContext(),list);
        recyclerView_BaiViet.setAdapter(adapter_tab_baiviet);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_tab_baiviet.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_tab_baiviet.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }


    private void builData(){
        list.add(new Tab_BaiViet("Nhân Viên Kinh Doanh - Tư Vấn Bán Hàng - Sales (Thu Nhập Lên Tới 15 Triệu/Tháng)"));
        list.add(new Tab_BaiViet("Nhân Viên Tư Vấn Tuyển Sinh Tại Hà Nội (10 - 20 Triệu)"));
        list.add(new Tab_BaiViet("Kế Toán Sản Xuất Thu Nhập Tới 15M - Làm Việc Tại Khu Công Nghiệp Cầu Gáo - Đan Phượng - Hà Nội"));
        list.add(new Tab_BaiViet("Trợ Lý Văn Phòng ( Admin ) - Thu Nhập Cao - Không Kinh Nghiệm Được Đào Tạo"));
    }


//    @Override
//    public void onclick(Tab_BaiViet tab_baiViet) {
//
////        Fragment fragment = ChiTietBaiViet_Fragment.newInstance(tab_baiViet.getTenbaiviet());
////        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.frame,fragment,"fragment_chi_tiet_bai_viet_");
////        transaction.addToBackStack(null);
////        transaction.commit();
//    }
//    public interface ISendata {
//        void sendata(Tab_BaiViet tab_baiViet);
//
//    }
}