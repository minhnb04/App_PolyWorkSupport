package com.example.app_supportpolywork.data.model.local_model;

public class Education {
    private String id;
    private String universityName;
    private String major;
    private String rank;
    private long startTime;
    private long endTime;

    public Education() {
    }

    public Education(String universityName, String major, String rank) {
        this.universityName = universityName;
        this.major = major;
        this.rank = rank;
    }

    public Education(String id, String universityName, String major, String rank) {
        this.id = id;
        this.universityName = universityName;
        this.major = major;
        this.rank = rank;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
