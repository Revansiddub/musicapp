package com.gsatechworld.musicapp.modules.home.trainer_home;

import com.google.gson.annotations.SerializedName;

public class AttendanceRequest {
    @SerializedName("trainer_id")
    public String trainer_id;
    @SerializedName("entrollment_id")
    public String entrollment_id;

    @SerializedName("date")
    public String date;

    @SerializedName("start_time")
    public String start_time;

    public String getTrainer_id() {
        return trainer_id;
    }

    public String getEntrollment_id() {
        return entrollment_id;
    }

    public String getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    @SerializedName("end_time")
    public String end_time;

    public AttendanceRequest(String trainer_id, String entrollment_id, String date, String start_time, String end_time) {
        this.trainer_id = trainer_id;
        this.entrollment_id = entrollment_id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }












}
