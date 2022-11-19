package com.example.app_supportpolywork.data.model.local_model;

public class Introduce {
    private String id;
    private String applicationPosition;
    private String objective;

    public Introduce() {
    }

    public Introduce(String applicationPosition, String objective) {
        this.applicationPosition = applicationPosition;
        this.objective = objective;
    }

    public Introduce(String id, String applicationPosition, String objective) {
        this.id = id;
        this.applicationPosition = applicationPosition;
        this.objective = objective;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationPosition() {
        return applicationPosition;
    }

    public void setApplicationPosition(String applicationPosition) {
        this.applicationPosition = applicationPosition;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
