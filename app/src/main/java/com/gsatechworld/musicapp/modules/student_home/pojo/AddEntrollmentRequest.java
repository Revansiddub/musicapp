package com.gsatechworld.musicapp.modules.student_home.pojo;

import com.google.gson.annotations.SerializedName;

public class AddEntrollmentRequest {
    @SerializedName("student_id")
    public String student_id;
    @SerializedName("trainer_id")
    public String trainer_id;
    @SerializedName("category_id")
    public String category_id;
    @SerializedName("sub_category_id")
    public String sub_category_id;

    public AddEntrollmentRequest(String student_id, String trainer_id, String category_id, String sub_category_id, String time_slot_id) {
        this.student_id = student_id;
        this.trainer_id = trainer_id;
        this.category_id = category_id;
        this.sub_category_id = sub_category_id;
        this.time_slot_id = time_slot_id;
    }

    @SerializedName("time_slot_id")
    public String time_slot_id;

    public String getStudent_id() {
        return student_id;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public String getTime_slot_id() {
        return time_slot_id;
    }



}
