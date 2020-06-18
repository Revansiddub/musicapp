package com.gsatechworld.musicapp.modules.welcome.pojo;

import com.google.gson.annotations.SerializedName;

public class PinCodeInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */



    private String userType;
    @SerializedName("pincode")
    private String pincode;



    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PinCodeInfo(String pincode) {
        this.pincode = pincode;
    }

}