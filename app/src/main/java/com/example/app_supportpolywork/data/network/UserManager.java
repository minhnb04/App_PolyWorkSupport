package com.example.app_supportpolywork.data.network;


import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.example.app_supportpolywork.data.model.User;
import com.example.app_supportpolywork.util.TaskListener;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class UserManager {
    private static UserManager instance;

    private UserManager() {
    }

    public static UserManager getInstance() {
        synchronized (UserManager.class) {
            if (instance == null) {
                instance = new UserManager();
            }
        }
        return instance;
    }

    public void register(String userName, String password, String fullName, String email, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message == null) return;
                listener.onSuccess(message);
            }
        };
        Call<ResponseBody> call = Network.mService.register(userName, password, fullName, email);
        call.enqueue(myCallback);
    }

    public void login(String userName, String password, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                JSONObject data = getDataJSONObject(response);
                if (data == null) return;
                try {
                    JSONObject userInfoJSON = data.getJSONObject("UserInfo");
                    User user = getUserFromJSONObject(userInfoJSON);
                    String token = response.headers().get(Network.AUTHORIZATION_KEY);
                    listener.onSuccess(new Pair<>(user, token));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Call<ResponseBody> call = Network.mService.login(userName, password);
        call.enqueue(myCallback);
    }

    public void changeProfile(String fullName, String email, String address, String gender, String userId, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message != null) {
                    listener.onSuccess(message);
                }
            }
        };
        Call<ResponseBody> call = Network.mService.changeInfo(
                fullName,
                email,
                address,
                getCodeFromGender(gender),
                userId
        );
        call.enqueue(myCallback);
    }

    private User getUserFromJSONObject(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.setId(jsonObject.getString(Network.ID_KEY));
        user.setUserName(jsonObject.getString("user_name"));
        user.setEmail(jsonObject.getString("email"));
        user.setFullName(jsonObject.getString("full_name"));
        return user;
    }

    private String getGenderFromCode(int gender) {
        switch (gender) {
            case 0:
                return "Nữ";
            case 1:
                return "Nam";
            default:
                return "Khác";
        }
    }

    private int getCodeFromGender(String gender) {
        switch (gender) {
            case "Nữ":
                return 0;
            case "Nam":
                return 1;
            default:
                return -1;
        }
    }
}
