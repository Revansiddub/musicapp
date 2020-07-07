package com.gsatechworld.musicapp.modules.otp.pojo;

import com.google.gson.annotations.SerializedName;

public class TrainerOTPVerification {

    @SerializedName("mobile_number")
    public String mobile_number;


    @SerializedName("otp")
    public String otp;

    @SerializedName("firebase_token")
    public String firebase_token;

    public TrainerOTPVerification(String mobile_number, String otp,String firebase_token) {
        this.mobile_number = mobile_number;
        this.otp = otp;
        this.firebase_token=firebase_token;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getOtp() {
        return otp;
    }


}
