package com.gsatechworld.musicapp.modules.otp.student.pojo;

import com.google.gson.annotations.SerializedName;

public class StudentOTPResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("student_id")
    private String student_id;
    @SerializedName("pincode_id")
    private String pincode_id;
    @SerializedName("pincode")
    private String pincode;

    public String getPincode_id() {
        return pincode_id;
    }

    public String getPincode() {
        return pincode;
    }



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
