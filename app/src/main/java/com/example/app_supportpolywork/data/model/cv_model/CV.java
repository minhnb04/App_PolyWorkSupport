package com.example.app_supportpolywork.data.model.cv_model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class CV {
    private int id;
    private int template;
    private int avatar;
    private String name;
    private CVType cvType;

    public CV(int id, int template, int avatar, String name, CVType cvType) {
        this.id = id;
        this.template = template;
        this.avatar = avatar;
        this.name = name;
        this.cvType = cvType;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public CVType getCvType() {
        return cvType;
    }

    public void setCvType(CVType cvType) {
        this.cvType = cvType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CV cv = (CV) o;
        return getTemplate() == cv.getTemplate() && Objects.equals(getName(), cv.getName());
    }

    public static DiffUtil.ItemCallback<CV> sDiffCallback = new DiffUtil.ItemCallback<CV>() {
        @Override
        public boolean areItemsTheSame(@NonNull CV oldItem, @NonNull CV newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CV oldItem, @NonNull CV newItem) {
            return oldItem.equals(newItem);
        }
    };

    public enum CVType {
        FREE("Miễn phí"), PREMIUM("Cao cấp");

        public String value;

        CVType(String s) {
            value = s;
        }
    }
}
