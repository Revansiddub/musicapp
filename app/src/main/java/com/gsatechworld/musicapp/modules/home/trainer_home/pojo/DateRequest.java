package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import com.google.gson.annotations.SerializedName;

public class DateRequest {
    @SerializedName("trainer_id")
    public String trainer_id;

    @SerializedName("month")
    public String month;

    @SerializedName("year")
    public String year;

    public DateRequest(String trainer_id, String month, String year) {
        this.trainer_id = trainer_id;
        this.month = month;
        this.year = year;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }





}
