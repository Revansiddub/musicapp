package com.gsatechworld.musicapp.modules.details.pojo;

import com.google.gson.annotations.SerializedName;

public class Slot_details {
    @SerializedName("start_time")
    private String start_time;

    @SerializedName("end_time")
    private String end_time;

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public Slot_details(String start_time, String end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
