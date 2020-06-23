package com.gsatechworld.musicapp.modules.student_home.pojo;

import com.google.gson.annotations.SerializedName;

public class UpcomingResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("upcoming_class")
    public String upcoming_class;

    public String getStatus() {
        return status;
    }

    public String getUpcoming_class() {
        return upcoming_class;
    }




}
