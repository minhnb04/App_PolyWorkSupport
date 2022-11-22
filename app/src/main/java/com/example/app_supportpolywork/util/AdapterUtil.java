package com.example.app_supportpolywork.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdapterUtil {
    public static String getJobSalary(long startSalary, long endSalary) {
        if (endSalary == -1) {
            return "Thỏa thuận";
        }
        return (startSalary / 1_000_000) + " - " + (endSalary / 1_000_000) + " triệu";
    }

    public static String getJobExpiry(String expiry) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
        try {
            Date date = simpleDateFormat.parse(expiry);
            if (date != null) {
                long time = date.getTime() - System.currentTimeMillis();
                int numberOfDate = (int) (time / (1000 * 60 * 60 * 24));
                if (numberOfDate >= 0) {
                    return "Còn " + (numberOfDate + 1) + " ngày để ứng tuyển";
                } else {
                    return "Hết hạn ứng tuyển";
                }
            } else {
                return "Hết hạn ứng tuyển";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "Hết hạn ứng tuyển";
        }
    }


    public static String getNeededPeople(int startNeededNumberOfPeople, int endNeededNumberOfPeople) {
        return startNeededNumberOfPeople + " - " + endNeededNumberOfPeople + " người";
    }
}
