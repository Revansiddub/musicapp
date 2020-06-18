package com.gsatechworld.musicapp.modules.details.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OnBoadingTrainer {
    @SerializedName("address")
    private String address;

    @SerializedName("gender")
    private String gender;


    @SerializedName("recurrence_types")
    private Recurrence_types recurrence_types;

    @SerializedName("highest_degree")
    private String highest_degree;

    @SerializedName("address_proof_back")
    private String address_proof_back;

    @SerializedName("expertise_document")
    private String expertise_document;

    @SerializedName("pincode_id")
    private String pincode_id;


    @SerializedName("profile_image")
    private String profile_image;

    @SerializedName("govt_id_front")
    private String govt_id_front;

    @SerializedName("address_proof_front")
    private String address_proof_front;

    @SerializedName("category_id")
    private String category_id;

    @SerializedName("coaching_types")
    private ArrayList<String> coaching_types;

    @SerializedName("sub_category_id")
    private String sub_category_id;

    @SerializedName("name")
    private String name;

    @SerializedName("govt_id_back")
    private String govt_id_back;

    @SerializedName("mobile_number")
    private String mobile_number;

    @SerializedName("email")
    private String email;

    @SerializedName("charge_amount")
    private String charge_amount;


    public OnBoadingTrainer(String profile_image,String address, String gender, Recurrence_types recurrence_types, String highest_degree, String address_proof_back, String expertise_document, String pincode_id, String govt_id_front, String address_proof_front, String category_id, ArrayList<String> coaching_types, String sub_category_id, String name, String govt_id_back, String mobile_number, String email, String charge_amount) {
        this.profile_image=profile_image;
        this.address = address;
        this.gender = gender;
        this.recurrence_types = recurrence_types;
        this.highest_degree = highest_degree;
        this.address_proof_back = address_proof_back;
        this.expertise_document = expertise_document;
        this.pincode_id = pincode_id;
        this.govt_id_front = govt_id_front;
        this.address_proof_front = address_proof_front;
        this.category_id = category_id;
        this.coaching_types = coaching_types;
        this.sub_category_id = sub_category_id;
        this.name = name;
        this.govt_id_back = govt_id_back;
        this.mobile_number = mobile_number;
        this.email = email;
        this.charge_amount = charge_amount;
    }


    public Recurrence_types getRecurrence_types() {
        return recurrence_types;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getProfile_image() {
        return profile_image;
    }


    public String getHighest_degree() {
        return highest_degree;
    }

    public String getAddress_proof_back() {
        return address_proof_back;
    }

    public String getExpertise_document() {
        return expertise_document;
    }

    public String getPincode_id() {
        return pincode_id;
    }

    public String getGovt_id_front() {
        return govt_id_front;
    }

    public String getAddress_proof_front() {
        return address_proof_front;
    }

    public String getCategory_id() {
        return category_id;
    }

    public ArrayList<String> getCoaching_types() {
        return coaching_types;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public String getName() {
        return name;
    }

    public String getGovt_id_back() {
        return govt_id_back;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public String getCharge_amount() {
        return charge_amount;
    }
}
