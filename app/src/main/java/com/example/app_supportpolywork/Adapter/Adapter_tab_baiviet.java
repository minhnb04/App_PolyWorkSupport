package com.example.app_supportpolywork.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.R;

import com.example.app_supportpolywork.model.Tab_BaiViet;

import java.util.List;

public class Adapter_tab_baiviet extends RecyclerView.Adapter<Adapter_tab_baiviet.ViewHolder_tap_baiviet> {
    Context context;
    List<Tab_BaiViet> list;

    public Adapter_tab_baiviet(Context context, List<Tab_BaiViet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder_tap_baiviet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context , R.layout.item_tab_bai_viet, null);
        return new ViewHolder_tap_baiviet(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_tap_baiviet holder, int position) {
//        Tab_BaiViet tab_baiViet =list.get(position);
//        holder.txtTenBaiViet.setText(tab_baiViet.getTenbaiviet());
//        holder.linearLayout_tabbaiviet.setOnClickListener(v -> {
//            Toast.makeText(context,"clickTab_bai_viet"+position,Toast.LENGTH_SHORT).show();
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder_tap_baiviet extends RecyclerView.ViewHolder{
        ImageView imgTabBaiViet;
        TextView txtTenBaiViet,txtSoChuong,txtTenTacGia;
        LinearLayout linearLayout_tabbaiviet;
        public ViewHolder_tap_baiviet(@NonNull View itemView) {
            super(itemView);
//            imgTabBaiViet=itemView.findViewById(R.id.imgBaiViet);
//            txtSoChuong=itemView.findViewById(R.id.itemChuong);
//            txtTenTacGia=itemView.findViewById(R.id.itemTacGia);
//            txtTenBaiViet=itemView.findViewById(R.id.itemTenNoiDung);
            linearLayout_tabbaiviet=itemView.findViewById(R.id.Linear_tabbaiviet);
        }
    }
}
