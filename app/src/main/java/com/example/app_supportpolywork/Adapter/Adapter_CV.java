package com.example.app_supportpolywork.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_supportpolywork.R;

import com.example.app_supportpolywork.model.Test_1;

import java.util.List;

public class Adapter_CV extends RecyclerView.Adapter<Adapter_CV.ViewHolder_Chude> {
    List<Test_1> listchude ;
    Context context ;

    public Adapter_CV(List<Test_1> listchude, Context context) {
        this.listchude = listchude;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder_Chude onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context , R.layout.item_chude , null);
        return new ViewHolder_Chude(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Chude holder, @SuppressLint("RecyclerView") int position) {
//        ChuDe chuDe =listchude.get(position);
//        holder.chude.setText(chuDe.getTenChuDe());
//        holder.carview.setOnClickListener(v -> {
//            Toast.makeText(context,"clickChude"+position,Toast.LENGTH_SHORT).show();
//        });
    }

    @Override
    public int getItemCount() {
        return listchude.size() ;
    }

    class ViewHolder_Chude extends RecyclerView.ViewHolder {
        //        View carview;
//        TextView chude  ;
        public ViewHolder_Chude(@NonNull View itemView) {
            super(itemView);
//            carview=itemView.findViewById(R.id.carview1);
//            chude = itemView.findViewById(R.id.chude1) ;
        }
    }
}


