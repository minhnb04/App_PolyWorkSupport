package com.example.app_supportpolywork.data.network;


import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.example.app_supportpolywork.App;
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

    public void changeProfile(User user, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message != null) {
                    listener.onSuccess(message);
                }
            }
        };
        Call<ResponseBody> call = Network.mService.changeInfo(user, user.getId());
        call.enqueue(myCallback);
    }

    private User getUserFromJSONObject(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.setId(jsonObject.getString(Network.ID_KEY));
        user.setUserName(jsonObject.getString("user_name"));
        user.setEmail(jsonObject.getString("email"));
        user.setFullName(jsonObject.getString("full_name"));

        if (jsonObject.has("image")) {
            user.setImage(jsonObject.getString("image"));
        }
        if (jsonObject.has("phone")) {
            user.setPhoneNumber(jsonObject.getString("phone"));

        }
        if (jsonObject.has("address")) {
            user.setAddress(jsonObject.getString("address"));
        }

        if (jsonObject.has("gender")) {
            user.setGender(jsonObject.getInt("gender"));
        }
        return user;
    }

    public void getInfo(String id, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                JSONObject data = getDataJSONObject(response);
                if (data == null) return;
                try {
                    User user = getUserFromJSONObject(data);
                    listener.onSuccess(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError(new Exception(App.ERROR_MESSAGE));
                }
            }
        };
        Call<ResponseBody> call = Network.mService.infoUser(id);
        call.enqueue(myCallback);
    }


    public void changePassword(String oldPassword, String newPassword, String userName, String userId, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                JSONObject data = getDataJSONObject(response);
                if (data == null) return;
                listener.onSuccess(data);
            }
        };
        Call<ResponseBody> call = Network.mService.changePassword(userName, oldPassword, newPassword, userId);
        call.enqueue(myCallback);
    }
}
