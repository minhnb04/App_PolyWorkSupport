package com.example.app_supportpolywork;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;


public class SeachActivity extends AppCompatActivity {
    ImageButton imgbtseach;
    TextInputEditText comic;
    RecyclerView rclTimKiem;
    String timKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        imgbtseach=findViewById(R.id.imgseachSP);
        comic=findViewById(R.id.edtseachSP);
        rclTimKiem=findViewById(R.id.rclTimKiemSP);

    }
    }
