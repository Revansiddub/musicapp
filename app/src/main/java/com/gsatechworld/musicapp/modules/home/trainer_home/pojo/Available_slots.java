package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.util.ArrayList;

public class Available_slots {

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


}
