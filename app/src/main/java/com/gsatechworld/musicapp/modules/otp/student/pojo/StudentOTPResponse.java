package com.gsatechworld.musicapp.modules.otp.student.pojo;

import com.google.gson.annotations.SerializedName;

public class StudentOTPResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("student_id")
    private String student_id;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getStudent_id() {
        return student_id;
    }
}
