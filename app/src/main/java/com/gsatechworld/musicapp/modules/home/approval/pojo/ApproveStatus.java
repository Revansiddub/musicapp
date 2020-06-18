package com.gsatechworld.musicapp.modules.home.approval.pojo;

import com.google.gson.annotations.SerializedName;

public class ApproveStatus {

    @SerializedName("student_id")
    public String student_id;

    @SerializedName("is_approved")
    public String is_approved;


    @SerializedName("enrollment_id")
    public String enrollment_id;



    public ApproveStatus(String enrollment_id,String student_id, String is_approved) {
        this.student_id = student_id;
        this.enrollment_id=enrollment_id;
        this.is_approved = is_approved;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public String getEnrollment_id() {
        return enrollment_id;
    }




}
