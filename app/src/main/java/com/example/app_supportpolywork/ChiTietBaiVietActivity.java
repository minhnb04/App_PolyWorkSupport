package com.example.app_supportpolywork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app_supportpolywork.model.Tab_BaiViet;

public class ChiTietBaiVietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_viet);
        TextView textView = findViewById(R.id.itemTenTieuDe2);
        Bundle bundle = getIntent().getExtras();
        Tab_BaiViet tab_baiViet =(Tab_BaiViet) bundle.get("tap");
        textView.setText(tab_baiViet.getTenbaiviet());
    }
}