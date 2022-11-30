package com.example.app_supportpolywork.data.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;
import java.util.Objects;

public class Job  implements Serializable {
    private String id;
    private String avatar;
    private String title;
    private long startSalary;
    private long endSalary;
    private String workForm;
    private String workPlace;
    private String slot;
    private String gender;
    private String experience;
    private String description;
    private String benefits;
    private String requirement;
    private String technology;
    private String position;
    private String expiryApply;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStartSalary() {
        return startSalary;
    }

    public void setStartSalary(long startSalary) {
        this.startSalary = startSalary;
    }

    public long getEndSalary() {
        return endSalary;
    }

    public void setEndSalary(long endSalary) {
        this.endSalary = endSalary;
    }


    public String getWorkForm() {
        return workForm;
    }

    public void setWorkForm(String workForm) {
        this.workForm = workForm;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExpiryApply() {
        return expiryApply;
    }

    public void setExpiryApply(String expiryApply) {
        this.expiryApply = expiryApply;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return getStartSalary() == job.getStartSalary() && getEndSalary() == job.getEndSalary() && Objects.equals(getExpiryApply(), job.getExpiryApply()) && Objects.equals(getAvatar(), job.getAvatar()) && Objects.equals(getTitle(), job.getTitle())  && Objects.equals(getWorkForm(), job.getWorkForm()) && Objects.equals(getWorkPlace(), job.getWorkPlace()) && Objects.equals(getGender(), job.getGender()) && Objects.equals(getExperience(), job.getExperience()) && Objects.equals(getDescription(), job.getDescription()) && Objects.equals(getBenefits(), job.getBenefits()) && Objects.equals(getRequirement(), job.getRequirement()) && Objects.equals(getTechnology(), job.getTechnology()) && Objects.equals(getPosition(), job.getPosition());
    }

    public enum WorkForm {
        FULL_TIME("Full-time"), PART_TIME("Part-time"), REMOTE("Remote");

        public String value;

        WorkForm(String s) {
            value = s;
        }
    }

    public static DiffUtil.ItemCallback<Job> sDiffCallback = new DiffUtil.ItemCallback<Job>() {
        @Override
        public boolean areItemsTheSame(@NonNull Job oldItem, @NonNull Job newItem) {
            return Objects.equals(oldItem.id, newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Job oldItem, @NonNull Job newItem) {
            return oldItem.equals(newItem);
        }
    };
}
