package com.example.app_supportpolywork.data.model;

public enum Technology {
    JAVA("Lập trình Java"),
    ANDROID("Lập trình Android"),
    ANDROID_KOTLIN("Android-kotlin"),
    IOS("Lập trình IOS"),
    PHP("Lập trình Web PHP"),
    NODE_JS("Lập trình back-end Nodejs"),
    OTHER("Lập trình");

    public String value;
    Technology(String s) {
        value = s;
    }
}
