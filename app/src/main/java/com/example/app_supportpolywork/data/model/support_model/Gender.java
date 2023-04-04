package com.example.app_supportpolywork.data.model.support_model;

public enum Gender {
    MALE("Nam"), FEMALE("Nữ"), NONE("Không yêu cầu");
    public String value;

    Gender(String s) {
        value = s;
    }
}
