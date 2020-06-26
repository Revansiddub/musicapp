package com.gsatechworld.musicapp.modules.home.trainer_home;

import com.google.gson.annotations.SerializedName;

public class AttendanceRequest {
    @SerializedName("student_id")
    public String student_id;
    @SerializedName("enrollment_id")
    public String enrollment_id;

    @SerializedName("date")
    public String date;

    @SerializedName("start_time")
    public String start_time;

    @SerializedName("end_time")
    public String end_time;

    public String getStudent_id() {
        return student_id;
    }

    public String getEnrollment_id() {
        return enrollment_id;
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



    public AttendanceRequest(String student_id, String enrollment_id, String date, String start_time, String end_time) {
        this.student_id = student_id;
        this.enrollment_id = enrollment_id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }












}
