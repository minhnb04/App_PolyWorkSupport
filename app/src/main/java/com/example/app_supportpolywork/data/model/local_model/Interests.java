package com.example.app_supportpolywork.data.model.local_model;

public class Interests {
    private String id;
    private String name;
    private String other;

    public Interests() {
    }

    public Interests(String name, String other) {
        this.name = name;
        this.other = other;
    }

    public Interests(String id, String name, String other) {
        this.id = id;
        this.name = name;
        this.other = other;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
