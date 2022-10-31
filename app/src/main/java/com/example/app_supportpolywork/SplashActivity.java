package com.example.app_supportpolywork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SplashScreen;
import android.window.SplashScreenView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Animation img,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
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
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}