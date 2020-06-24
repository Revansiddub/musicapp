package com.gsatechworld.musicapp.modules.otp.student;

import com.google.gson.annotations.SerializedName;

public class StudentOTPRequest {
    @SerializedName("mobile_number")
    public String mobile_number;
    @SerializedName("otp")
    public String otp;
    @SerializedName("firebase_token")
    public String firebase_token;

    public StudentOTPRequest(String mobile_number, String otp, String firebase_token) {
        this.mobile_number = mobile_number;
        this.otp = otp;
        this.firebase_token = firebase_token;
    }
}
