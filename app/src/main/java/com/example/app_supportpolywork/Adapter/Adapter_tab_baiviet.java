package com.example.app_supportpolywork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

    //    Context context;
    List<Tab_BaiViet> list;
    private ItemClick itemClick;

    public Adapter_tab_baiviet( ItemClick itemClick, List<Tab_BaiViet> list) {
//        this.context = context;
        this.itemClick=itemClick;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder_tap_baiviet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_tab_bai_viet, parent,false);
        return new ViewHolder_tap_baiviet(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_tap_baiviet holder, int position) {
        Tab_BaiViet tab_baiViet = list.get(position);
        holder.itemTenTieuDe.setText(list.get(position).getTenbaiviet());
        holder.linearLayout_tabbaiviet.setOnClickListener(v -> {
            itemClick.onclick(list.get(position));

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder_tap_baiviet extends RecyclerView.ViewHolder{
        ImageView imgTabBaiViet;
        TextView txtSoluong,txtLuong,itemTenTieuDe,txtCapBac,txtHinhThuc;
        LinearLayout linearLayout_tabbaiviet;
        public ViewHolder_tap_baiviet(@NonNull View itemView) {
            super(itemView);
            imgTabBaiViet=itemView.findViewById(R.id.imgBaiViet);
            itemTenTieuDe=itemView.findViewById(R.id.itemTenTieuDe);
            txtSoluong=itemView.findViewById(R.id.itemSoLuong);
            txtLuong=itemView.findViewById(R.id.itemMucLuong);
            txtCapBac=itemView.findViewById(R.id.itemCapBac);
            txtHinhThuc=itemView.findViewById(R.id.itemHinhThuc);
            linearLayout_tabbaiviet=itemView.findViewById(R.id.Linear_tabbaiviet);

        }
    }
    public interface ItemClick{
        public void onclick(Tab_BaiViet tab_baiViet);

    }
}
