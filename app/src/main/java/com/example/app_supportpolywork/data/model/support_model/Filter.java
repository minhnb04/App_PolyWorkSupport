package com.example.app_supportpolywork.data.model.support_model;

import java.io.Serializable;

public class Filter implements Serializable {
    private Position position;
    private Technology technology;


    public Filter() {
    }

    public Filter(Position position, Technology technology) {
        this.position = position;
        this.technology = technology;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
