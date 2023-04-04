package com.example.app_supportpolywork.data.model.cv_model;

public class Education {
    private String universityName;
    private String major;
    private String degreeRank;
    private String degreeType;
    private String startTime;
    private String endTime;

    public Education(String universityName, String major, String degreeRank, String degreeType, String startTime, String endTime) {
        this.universityName = universityName;
        this.major = major;
        this.degreeRank = degreeRank;
        this.degreeType = degreeType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
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

    public String getDegreeRank() {
        return degreeRank;
    }

    public void setDegreeRank(String degreeRank) {
        this.degreeRank = degreeRank;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
