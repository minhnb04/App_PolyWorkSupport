package com.example.app_supportpolywork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_supportpolywork.model.ThongTinCaNhan_Add_CV_test;

import java.util.Locale;

public class CV_Detail extends AppCompatActivity {
    ImageView imageView;
    TextView tvTen,tvNghe,tvgoitinh,tvdiachi,tvemail,tvsdt,tvnsinh;
    ThongTinCaNhan_Add_CV_test tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_detial);
        imageView = findViewById(R.id.img_detail_cv);
        tvdiachi = findViewById(R.id.tv_address_cv);
        tvTen = findViewById(R.id.tv_name_cv);
        tvNghe = findViewById(R.id.tv_posi_cv);
        tvgoitinh = findViewById(R.id.tv_gender_cv);
        tvemail = findViewById(R.id.tv_email_cv);
        tvsdt = findViewById(R.id.tv_number_cv);
        tvnsinh = findViewById(R.id.tv_namsinh_cv);
        try {
            Bundle bundle = getIntent().getExtras();
            tt =(ThongTinCaNhan_Add_CV_test) bundle.get("data");
            tvTen.setText(tt.getTen());
            tvNghe.setText(tt.getNghe());
            tvdiachi.setText(tt.getDiaChi());
            tvemail.setText(tt.getEmail());
            tvgoitinh.setText(tt.getGoiTinh());
            String sdt= String.valueOf(tt.getSdt());
            tvsdt.setText(sdt);
            tvnsinh.setText(tt.getNamSinh());
        }catch (Exception e){

        }

    }

}