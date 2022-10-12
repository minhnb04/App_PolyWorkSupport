package com.example.app_supportpolywork.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.model.ChuDe;


import java.util.ArrayList;
import java.util.List;


public class ChuDe_Fragment extends Fragment {
    RecyclerView recyclerView;
    List<ChuDe> chudeArrayList = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cv,container,false);
        recyclerView = view.findViewById(R.id.rclchude);
        chudeArrayList.add(new ChuDe("Moi Nhat "));
        chudeArrayList.add(new ChuDe("Moi Nhat1 "));
        chudeArrayList.add(new ChuDe("Moi Nhat2 "));
        chudeArrayList.add(new ChuDe("Moi Nhat3"));
        chudeArrayList.add(new ChuDe("Moi Nhat4 "));
        chudeArrayList.add(new ChuDe("Moi Nhat5 "));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        Adapter_chude Adapter = new Adapter_chude(chudeArrayList,getActivity().getApplicationContext());
        recyclerView.setAdapter(Adapter);
        return view;
    }

}