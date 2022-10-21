package com.example.app_supportpolywork;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Animation img,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        imageView =findViewById(R.id.imageViewLG);
        textView=findViewById(R.id.textMainlogo1);
        img= AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        text=AnimationUtils.loadAnimation(this,R.anim.bottom_anim_logo);
        imageView.setAnimation(img);
        textView.setAnimation(text);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}