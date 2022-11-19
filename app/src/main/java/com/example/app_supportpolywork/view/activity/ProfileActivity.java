package com.example.app_supportpolywork.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;


import com.example.app_supportpolywork.R;

public class ProfileActivity extends AppCompatActivity {
    ImageView imageView ;
    int SELECT_IMAGE2=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView =findViewById(R.id.imgInfor);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"tile"),SELECT_IMAGE2);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            Uri uri=data.getData();
            imageView.setImageURI(uri);
        }
    }
}