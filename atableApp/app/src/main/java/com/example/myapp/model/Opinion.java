package com.example.myapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Opinion implements Serializable {

    @SerializedName("grade")
    @Expose
    private Float grade;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("id")
    @Expose
    private String id;

    public Opinion(Float grade, String description) {
        this.grade = grade;
        this.description = description;
    }

    public Float getGrade() { return grade; }

    public void setName(Float grade) {
        this.grade = grade;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }
}
