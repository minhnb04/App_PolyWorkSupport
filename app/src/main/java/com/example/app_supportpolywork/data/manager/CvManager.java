package com.example.app_supportpolywork.data.manager;

import com.example.app_supportpolywork.data.model.cv_model.Education;
import com.example.app_supportpolywork.data.model.cv_model.Experience;
import com.example.app_supportpolywork.data.model.cv_model.Info;

import java.util.List;

public class CVManager {
    private static CVManager instance;

    private CVManager() {

    }

    public static CVManager getInstance() {
        synchronized (CVManager.class) {
            if (instance == null) {
                instance = new CVManager();
                return instance;
            }
        }
        return instance;
    }

    private Education mEducation;
    private List<Experience> mExperiences;
    private Info mInfo;
    private String objective;
    private String active;
    private String skill;
    private String interesting;
    private String certification;


    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getInteresting() {
        return interesting;
    }

    public void setInteresting(String interesting) {
        this.interesting = interesting;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Info getInfo() {
        return mInfo;
    }

    public void setInfo(Info info) {
        this.mInfo = info;
    }

    public Education getEducation() {
        return mEducation;
    }

    public void setEducation(Education education) {
        mEducation = education;
    }

    public List<Experience> getExperiences() {
        return mExperiences;
    }

    public void setExperiences(List<Experience> experiences) {
        mExperiences = experiences;
    }
}
