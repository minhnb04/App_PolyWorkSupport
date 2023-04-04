package com.example.app_supportpolywork.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        loadAnimationForViews();
        navigateToLoginActivity();

    }

    private void loadAnimationForViews() {
        Animation imvAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_imv_logo);
        Animation tvAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_tv_logo);
        mBinding.imvLogo.setAnimation(imvAnimation);
        mBinding.tvLogo.setAnimation(tvAnimation);
    }

    private void navigateToLoginActivity() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}