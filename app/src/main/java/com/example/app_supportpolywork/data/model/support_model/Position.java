package com.example.app_supportpolywork.data.model.support_model;

import androidx.annotation.NonNull;

public enum Position implements FilterField{
    ALL("Tất cả"), INTERN("Thực tập sinh"), FRESHER("Fresher"), JUNIOR("Junior"), DEV("Developer"), SENIOR("Senior"), MANAGER("Manager"), OTHER("Other");
    public String value;

    Position(String s) {
        value = s;
    }

    @Override
    public String getValue() {
        return value;
    }
}
