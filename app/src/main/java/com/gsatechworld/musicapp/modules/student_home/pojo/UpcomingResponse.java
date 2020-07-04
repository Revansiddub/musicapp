package com.gsatechworld.musicapp.modules.student_home.pojo;

import com.google.gson.annotations.SerializedName;

public class UpcomingResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("date")
    public String date;

    @SerializedName("start_time")
    public String start_time;

    @SerializedName("end_time")
    public String end_time;


    public String getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }


    public String getStatus() {
        return status;
    }






}
