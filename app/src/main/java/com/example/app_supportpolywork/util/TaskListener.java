package com.example.app_supportpolywork.util;

public interface TaskListener {
    <T> void onSuccess(T data);
    void onFailure(Exception exception);
}
