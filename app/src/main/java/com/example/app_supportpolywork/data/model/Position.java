package com.example.app_supportpolywork.data.model;

public enum Position {
    INTERN("Thực tập sinh"), FRESHER("Fresher"), JUNIOR ("junior") ,DEV ("Developer"), SENIOR("Senior"), MANAGER("Manager"), OTHER("other");
    public String value;
    Position(String s) {
        value = s;
    }
}
