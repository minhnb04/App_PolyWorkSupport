package com.example.app_supportpolywork.data.model;

import java.io.Serializable;
import java.util.Objects;

public class CV implements Serializable {
    private String id;
    private String title;
    private String image;

    public CV() {
    }

    public CV(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public CV(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CV cv = (CV) o;
        return Objects.equals(getTitle(), cv.getTitle()) && Objects.equals(getImage(), cv.getImage());
    }
}