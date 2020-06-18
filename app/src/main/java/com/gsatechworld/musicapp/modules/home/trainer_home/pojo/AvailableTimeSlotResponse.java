package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AvailableTimeSlotResponse {


    @SerializedName("message")
    private String message;


    @SerializedName("status")
    private String status;

    @SerializedName("available_slots")
    private ArrayList<Available_slots> available_slots;


    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
    public ArrayList<Available_slots> getAvailable_slots() {
        return available_slots;
    }



}
