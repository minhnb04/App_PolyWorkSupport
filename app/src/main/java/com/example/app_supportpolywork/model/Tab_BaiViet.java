package com.example.app_supportpolywork.model;

import java.io.Serializable;

public class Tab_BaiViet implements Serializable {
    String tenbaiviet;

    public Tab_BaiViet(String tenbaiviet) {
        this.tenbaiviet = tenbaiviet;
    }

    public String getTenbaiviet() {
        return tenbaiviet;
    }

    public void setTenbaiviet(String tenbaiviet) {
        this.tenbaiviet = tenbaiviet;
    }
}
