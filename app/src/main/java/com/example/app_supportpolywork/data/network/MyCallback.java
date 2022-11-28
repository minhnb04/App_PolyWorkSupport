package com.example.app_supportpolywork.data.network;

import androidx.annotation.NonNull;

import com.example.app_supportpolywork.App;
import com.example.app_supportpolywork.util.TaskListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCallback<T> implements Callback<T> {

    private final TaskListener listener;

    public MyCallback(TaskListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        listener.onError(new Exception(t.getMessage()));
    }

    public JSONObject getDataJSONObject(@NonNull Response<ResponseBody> response) {
        if (response.body() == null) {
            listener.onError(new Exception(response.message()));
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            int statusCode = jsonObject.getInt(Network.STATUS_KEY);
            if (statusCode == 200) {
                return jsonObject.getJSONObject(Network.DATA_KEY);
            } else {
                listener.onError(new Exception(jsonObject.getString(Network.MESSAGE_KEY)));
                return null;
            }
        } catch (IOException | JSONException e) {
            listener.onError(new Exception(App.ERROR_MESSAGE));
            return null;
        }
    }

    public JSONArray getDataJSONArray(@NonNull Response<ResponseBody> response) {
        if (response.body() == null) {
            listener.onError(new Exception(response.message()));
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            int statusCode = jsonObject.getInt(Network.STATUS_KEY);
            if (statusCode == 200) {
                return jsonObject.getJSONArray(Network.DATA_KEY);
            } else {
                listener.onError(new Exception(jsonObject.getString(Network.MESSAGE_KEY)));
                return null;
            }
        } catch (IOException | JSONException e) {
            listener.onError(new Exception(App.ERROR_MESSAGE));
            return null;
        }
    }

    public String getMessage(@NonNull Response<ResponseBody> response) {
        if (response.body() == null) {
            listener.onError(new Exception(response.message()));
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(response.body().string());
            int statusCode = jsonObject.getInt(Network.STATUS_KEY);
            if (statusCode == 200) {
                return jsonObject.getString(Network.MESSAGE_KEY);
            } else {
                listener.onError(new Exception(jsonObject.getString(Network.MESSAGE_KEY)));
                return null;
            }
        } catch (IOException | JSONException e) {
            listener.onError(new Exception(App.ERROR_MESSAGE));
            return null;
        }
    }
}
