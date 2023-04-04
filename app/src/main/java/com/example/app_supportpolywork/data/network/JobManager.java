package com.example.app_supportpolywork.data.network;

import androidx.annotation.NonNull;

import com.example.app_supportpolywork.App;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.util.TaskListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class JobManager {
    private static JobManager instance;

    private JobManager() {
    }

    public static JobManager getInstance() {
        synchronized (JobManager.class) {
            if (instance == null) {
                instance = new JobManager();
            }
        }
        return instance;
    }

    public void getJob(TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                JSONArray data = getDataJSONArray(response);
                if (data == null) return;
                try {
                    List<Job> jobs = getJobListFromJsonObject(data);
                    listener.onSuccess(jobs);
                } catch (JSONException e) {
                    listener.onError(new Exception(App.ERROR_MESSAGE));
                }
            }
        };
        Call<ResponseBody> call = Network.mService.getJob();
        call.enqueue(myCallback);
    }

    private List<Job> getJobListFromJsonObject(JSONArray data) throws JSONException {
        List<Job> result = new ArrayList<>();
        int size = data.length();
        for (int i = 0; i < size; i++) {
            JSONObject obj = data.getJSONObject(i);
            Job job = new Job();
            job.setId(obj.getString(Network.ID_KEY));

            if (obj.has("slot")) {
                job.setSlot(obj.getString("slot"));
            }

            if (obj.has("image")) {
                job.setAvatar(obj.getString("image"));
            }

            if (obj.has("job_name")) {
                job.setTitle(obj.getString("job_name"));
            }

            if (obj.has("description")) {
                job.setDescription(obj.getString("description"));
            }

            if (obj.has("salary_min")) {
                job.setStartSalary(obj.getInt("salary_min"));
            }

            if (obj.has("salary_max")) {
                job.setEndSalary(obj.getInt("salary_max"));
            }

            if (obj.has("end_date")) {
                job.setExpiryApply(obj.getString("end_date"));
            }

            if (obj.has("candidate")) {
                job.setPosition(obj.getString("candidate"));
            }

            if (obj.has("technology")) {
                job.setTechnology(obj.getString("technology"));
            }

            if (obj.has("work_form")) {
                job.setWorkForm(obj.getString("work_form"));
            }

            if (obj.has("work_place")) {
                job.setWorkPlace(obj.getString("work_place"));
            }

            if (obj.has("experience")) {
                job.setExperience(obj.getString("experience"));
            }

            if (obj.has("benefits")) {
                job.setBenefits(obj.getString("benefits"));
            }

            if (obj.has("requirement")) {
                job.setRequirement(obj.getString("requirement"));
            }

            if (obj.has("gender")) {
                job.setGender(obj.getString("gender"));
            }

            result.add(job);
        }
        return result;
    }

    public void applyJob(String userId, String cvId, String jobId, TaskListener listener) {
        MyCallback<ResponseBody> myCallback = new MyCallback<ResponseBody>(listener) {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String message = getMessage(response);
                if (message == null) return;
                listener.onSuccess(message);
            }
        };
        Call<ResponseBody> deleteCV = Network.mService.applyJob(userId, cvId, jobId);
        deleteCV.enqueue(myCallback);
    }
}
