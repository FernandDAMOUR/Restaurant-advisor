package com.example.myapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("localization")
    @Expose
    private String localization;

    @SerializedName("phone_number")
    @Expose
    private String phone;

    @SerializedName("website")
    @Expose
    private String website ;

    @SerializedName("hours")
    @Expose
    private String hours;


    public Restaurant(String name , String description  , String localization, String phone , String website , String hours  ){
        this.name= name;
        this.description = description;
        this.localization = localization;
        this.phone = phone;
        this.website = website;
        this.hours = hours;
    }


    public String getName(){

        return name;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getId() {

        return id;
    }

    public void setId( String id) {

        this.id = id ;
    }

    public String getDescription(){

        return description;
    }

    public void setDescription(String description){
        this.description = description;

    }

    public  String getGrade(){

        return grade;
    }




    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }


}
