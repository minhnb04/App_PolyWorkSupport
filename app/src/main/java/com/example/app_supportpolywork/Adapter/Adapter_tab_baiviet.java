package com.example.app_supportpolywork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.ChiTietBaiVietActivity;
import com.example.app_supportpolywork.R;

import com.example.app_supportpolywork.model.Tab_BaiViet;

import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adapter_tab_baiviet extends RecyclerView.Adapter<Adapter_tab_baiviet.ViewHolder_tap_baiviet> implements Filterable {

        Context context;
    List<Tab_BaiViet> list;
    List<Tab_BaiViet> listold;
//    private ItemClick itemClick;

    public Adapter_tab_baiviet( Context context, List<Tab_BaiViet> list) {
        this.context = context;
//        this.itemClick=itemClick;
        this.listold=list;
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
//            itemClick.onclick(list.get(position));
            Intent intent = new Intent(context, ChiTietBaiVietActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("tap", (Serializable) tab_baiViet);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()) {
                    list = listold;
                } else {
                    List<Tab_BaiViet> ls = new ArrayList<>();
                    for (Tab_BaiViet tab_baiViet : listold) {
                        if (tab_baiViet.getTenbaiviet().toLowerCase().contains(search.toLowerCase())) {
                            ls.add(tab_baiViet);

                        }
                        list = ls;

                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    list= (List<Tab_BaiViet>) results.values;
                    notifyDataSetChanged();
            }
        };
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

}
