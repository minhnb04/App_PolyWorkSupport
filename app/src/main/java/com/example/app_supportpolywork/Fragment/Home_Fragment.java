package com.example.app_supportpolywork.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_supportpolywork.MapsActivity;
import com.example.app_supportpolywork.R;


public class Home_Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu,container,false);
        TextView textView;
        textView = view.findViewById(R.id.txtmaps);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplication().getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        });
        return view;
    }
//    void ViewFlip()
//    {
//        ArrayList<String> mang = new ArrayList<>();
//        mang.add("https://truyentranhtronbo.com/wp-content/uploads/2022/05/banner-truyen-tranh-tron-bo-bayvienngocrong.jpg");
//        mang.add("https://truyenbanquyen.com/wp-content/uploads/2018/01/BANNER-WEB-180102.jpg");
//        mang.add("https://congdongshop.com/wp-content/uploads/2022/05/banner-cong-dong-shop-truyen-tranh-full-bo.jpg");
//        for(int i=0;i<mang.size();i++)
//        {
//            ImageView imageView = new ImageView(getActivity().getApplicationContext());
//            Picasso.get().load(mang.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipper.addView(imageView);
//        }
//        viewFlipper.setFlipInterval(4000);
//        viewFlipper.setAutoStart(true);
//
//        Animation animation_in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_in);
//        Animation animation_out = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_out);
//
//        viewFlipper.setInAnimation(animation_in);
//        viewFlipper.setOutAnimation(animation_out);
//    }
}
