package com.example.app_supportpolywork.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.app_supportpolywork.data.model.User;
import com.google.gson.Gson;

public class ShareFileUtil {

    private static final String USER_FILE_KEY = "user_file";
    private static final String USER_KEY = "user";
    private static final String TOKEN_KEY = "token";

    public static void saveUser(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_KEY, new Gson().toJson(user));
        editor.apply();
    }

    public static User getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
        String userString = sharedPreferences.getString(USER_KEY, "");
        return new Gson().fromJson(userString, User.class);
    }

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TOKEN_KEY, "");
    }

    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
