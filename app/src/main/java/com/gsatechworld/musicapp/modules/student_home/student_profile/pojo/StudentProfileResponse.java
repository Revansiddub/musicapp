package com.gsatechworld.musicapp.modules.student_home.student_profile.pojo;

import com.google.gson.annotations.SerializedName;

public class StudentProfileResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile_number")
    private String mobile_number;

    @SerializedName("age")
    private String age;

    @SerializedName("standard")
    private String standard;

    @SerializedName("school_name")
    private String school_name;

    @SerializedName("profile_image")
    private String profile_image;



    public String getStandard() {
        return standard;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getName() {
        return name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }




}
