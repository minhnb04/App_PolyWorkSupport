package com.example.app_supportpolywork.data.network;

import androidx.annotation.NonNull;

import com.example.app_supportpolywork.App;
import com.example.app_supportpolywork.data.model.CV;
import com.example.app_supportpolywork.util.TaskListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
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

    public void postCV(String title, String image, String userId, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message != null) listener.onSuccess(message);
            }
        };
        Call<ResponseBody> call = Network.mService.postCV(title, image, userId);
        call.enqueue(myCallback);
    }

    public void getCVListOfUser(String userId, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                JSONArray data = getDataJSONArray(response);
                if (data == null) return;
                try {
                    listener.onSuccess(getCVListFromJsonObject(data));
                } catch (JSONException e) {
                    listener.onError(new Exception(App.ERROR_MESSAGE));
                }
            }
        };
        Call<ResponseBody> cvListOfUser = Network.mService.getCVListOfUser(userId);
        cvListOfUser.enqueue(myCallback);
    }

    public void updateCV(CV cv, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message == null) return;
                listener.onSuccess(message);
            }
        };
        Call<ResponseBody> updateCV = Network.mService.updateCV(cv.getTitle(), cv.getId());
        updateCV.enqueue(myCallback);
    }

    public void deleteCV(CV mCV, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message == null) return;
                listener.onSuccess(message);
            }
        };
        Call<ResponseBody> deleteCV = Network.mService.deleteCV(mCV.getId());
        deleteCV.enqueue(myCallback);
    }

    private List<CV> getCVListFromJsonObject(JSONArray jsonArray) throws JSONException {
        List<CV> result = new ArrayList<>();
        int size = jsonArray.length();
        for (int i = 0; i < size; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            CV cv = new CV();
            cv.setId(obj.getString(Network.ID_KEY));
            cv.setTitle(obj.getString("document_name"));
            cv.setImage(obj.getString("document_link"));
            result.add(cv);
        }
        return result;
    }

    public void getCV(String cvId, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                JSONObject data = getDataJSONObject(response);
                if (data == null) return;
                try {
                    listener.onSuccess(getCVFromJsonObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Call<ResponseBody> deleteCV = Network.mService.getCV(cvId);
        deleteCV.enqueue(myCallback);
    }

    private Object getCVFromJsonObject(JSONObject obj) throws JSONException {
        CV cv = new CV();
        cv.setId(obj.getString(Network.ID_KEY));
        cv.setTitle(obj.getString("document_name"));
        cv.setImage(obj.getString("document_link"));
        return cv;
    }


}
