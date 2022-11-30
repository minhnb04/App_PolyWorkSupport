package com.example.app_supportpolywork.util;

import android.annotation.SuppressLint;

import com.example.app_supportpolywork.data.model.User;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
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

    public static String getGenderFromCode2(int gender) {
        switch (gender) {
            case 0:
                return "Nữ";
            case 1:
                return "Nam";
            default:
                return "Không yêu cầu";
        }
    }

    public static String getGenderFromCode(int gender) {
        switch (gender) {
            case 0:
                return "Nữ";
            case 1:
                return "Nam";
            case -2:
                return "";
            default:
                return "Khác";
        }
    }

    public static int getCodeFromGender(String gender) {
        switch (gender) {
            case "Nữ":
                return 0;
            case "Nam":
                return 1;
            case "Khác":
                return -1;
            default:
                return -2;
        }
    }
}
