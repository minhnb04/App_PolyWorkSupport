package com.example.app_supportpolywork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.example.app_supportpolywork.Adapter.Adapter_Main;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    //    Trangchu_Fragment trangchu_fragment = new Trangchu_Fragment();
//    ChuDe_Fragment chuDe_fragment =new ChuDe_Fragment();
//    YeuThich_Fragment yeuThich_fragment = new YeuThich_Fragment();
//    CaNhan_Fragment caNhan_fragment = new CaNhan_Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNaVi);
        viewPager= findViewById(R.id.viewPa);
//        frameLayout= findViewById(R.id.frame);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bot_home:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,trangchu_fragment).commit();
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bot_viec_lam:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,yeuThich_fragment).commit();
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bot_CV:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,chuDe_fragment).commit();
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.bot_ho_so:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,caNhan_fragment).commit();
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        setViewPager();
    }
    public void setViewPager(){
        Adapter_Main viewPagerAdapter =new Adapter_Main(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bot_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bot_viec_lam).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bot_CV).setChecked(true);
                        break;

                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.bot_ho_so).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
