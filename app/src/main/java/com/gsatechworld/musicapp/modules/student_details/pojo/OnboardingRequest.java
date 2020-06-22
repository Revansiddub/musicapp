package com.gsatechworld.musicapp.modules.student_details.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.modules.home.approval.pojo.Slot_Details;

import java.util.ArrayList;

public class OnboardingRequest {
    @SerializedName("pincode_id")
    public String pincode_id;

    @SerializedName("category_id")
    public String category_id;

    @SerializedName("sub_category_id")
    public String sub_category_id;

    @SerializedName("slot_details")
    public ArrayList<Slot_Details> slot_details;

    @SerializedName("name")
    public String name;

    @SerializedName("age")
    public String age;

    @SerializedName("standard")
    public String standard;


    @SerializedName("school_name")
    public String school_name;

    @SerializedName("address")
    public String address;

    @SerializedName("mobile_number")
    public String mobile_number;

    @SerializedName("trainer_id")
    public String trainer_id;

    public OnboardingRequest(String pincode_id, String category_id, String sub_category_id, ArrayList<Slot_Details> slot_details, String name, String age, String standard, String school_name, String address, String mobile_number, String trainer_id, String profile_image) {
        this.pincode_id = pincode_id;
        this.category_id = category_id;
        this.sub_category_id = sub_category_id;
        this.slot_details = slot_details;
        this.name = name;
        this.age = age;
        this.standard = standard;
        this.school_name = school_name;
        this.address = address;
        this.mobile_number = mobile_number;
        this.trainer_id = trainer_id;
        this.profile_image = profile_image;
    }

    @SerializedName("profile_image")
    public String profile_image;

    public String getSub_category_id() {
        return sub_category_id;
    }

    public String getProfile_image() {
        return profile_image;
    }



    public String getPincode_id() {
        return pincode_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public ArrayList<Slot_Details> getSlot_details() {
        return slot_details;
    }

    public String getStandard() {
        return standard;
    }

    public String getAddress() {
        return address;
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

    public String getTrainer_id() {
        return trainer_id;
    }










}
