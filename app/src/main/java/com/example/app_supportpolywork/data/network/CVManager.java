package com.example.app_supportpolywork.data.manager;

import androidx.annotation.NonNull;

import com.example.app_supportpolywork.data.model.CV;
import com.example.app_supportpolywork.data.network.Network;
import com.example.app_supportpolywork.util.TaskListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CVManager {
    private static CVManager instance;

    private CVManager() {
    }

    public static CVManager getInstance() {
        synchronized (CVManager.class) {
            if (instance == null) {
                instance = new CVManager();
            }
        }
        return instance;
    }

    public void postCV(CV cv, TaskListener listener) {
        Call<ResponseBody> call = Network.mService.postCV(cv);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int statusCode = jsonObject.getInt("StatusCode");
                        String message = jsonObject.getString("Message");
                        if (statusCode == 200) {
                            listener.onSuccess(message);
                        } else {
                            listener.onError(new Exception(message));
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    listener.onError(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                listener.onError(new Exception(t.getMessage()));
            }
        });
    }

    public void getCVListOfUser(String userId, TaskListener listener) {
        Call<ResponseBody> cvListOfUser = Network.mService.getCVListOfUser(userId);
        cvListOfUser.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.body() == null) {
                    listener.onError(new Exception(response.message()));
                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int statusCode = jsonObject.getInt("")
                } catch (JSONException | IOException e) {
                    listener.onError(new Exception("Đã xảy ra lỗi hệ thống"));
                }


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

            }
        });
    }


}
