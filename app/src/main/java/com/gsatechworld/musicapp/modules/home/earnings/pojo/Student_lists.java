package com.gsatechworld.musicapp.modules.home.earnings.pojo;

import com.google.gson.annotations.SerializedName;

public class Student_lists {
    @SerializedName("date")
    private String date;

    @SerializedName("student_name")
    private String student_name;

    @SerializedName("amount")
    private String amount;

    public String getDate() {
        return date;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getAmount() {
        return amount;
    }

    public String getStudent_id() {
        return student_id;
    }

    @SerializedName("student_id")
    private String student_id;

}
