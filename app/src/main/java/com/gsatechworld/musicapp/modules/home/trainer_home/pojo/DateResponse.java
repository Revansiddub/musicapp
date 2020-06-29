package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DateResponse {
    @SerializedName("status")
    private String response;


    @SerializedName("dates")
    private ArrayList<String> dates;

    public String getResponse() {
        return response;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

}
