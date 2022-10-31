package com.example.app_supportpolywork.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_supportpolywork.Fragment.HoSo_Fragment;
import com.example.app_supportpolywork.Fragment.CV_Fragment;
import com.example.app_supportpolywork.Fragment.Home_Fragment;
import com.example.app_supportpolywork.Fragment.ViecLam_Fragment;


public class Adapter_Main extends FragmentStatePagerAdapter {

    public Adapter_Main(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home_Fragment();
            case 1:
                return new ViecLam_Fragment();
            case 2:
                return new CV_Fragment();
            case 3:
                return new HoSo_Fragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
