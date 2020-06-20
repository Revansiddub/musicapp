package com.gsatechworld.musicapp.modules.details.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OnBoadingTrainer {
    @SerializedName("address")
    private String address;

    @SerializedName("gender")
    private String gender;


    @SerializedName("recurrence_type")
    private String  recurrence_type;

    @SerializedName("slot_details")
    private ArrayList<Slot_details> slot_details;

    @SerializedName("coaching_days")
    public ArrayList<String> coaching_days;

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


    public OnBoadingTrainer(String profile_image,String address,
                            String gender, String recurrence_type,
                            String highest_degree, String address_proof_back,
                            String expertise_document, String pincode_id,
                            String govt_id_front, String address_proof_front,
                            String category_id, ArrayList<String> coaching_types,
                            String sub_category_id, String name, String govt_id_back,
                            String mobile_number, String email, String charge_amount,
                            ArrayList<Slot_details> slot_details, ArrayList<String> coaching_days) {
        this.profile_image=profile_image;
        this.address = address;
        this.gender = gender;
        this.recurrence_type = recurrence_type;
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
        this.slot_details = slot_details;
        this.coaching_days = coaching_days;
    }


    public ArrayList<Slot_details> getSlot_details() {
        return slot_details;
    }

    public void setSlot_details(ArrayList<Slot_details> slot_details) {
        this.slot_details = slot_details;
    }

    public ArrayList<String> getCoaching_days() {
        return coaching_days;
    }

    public void setCoaching_days(ArrayList<String> coaching_days) {
        this.coaching_days = coaching_days;
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
