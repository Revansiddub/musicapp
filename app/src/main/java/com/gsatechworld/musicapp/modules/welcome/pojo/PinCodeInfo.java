package com.gsatechworld.musicapp.modules.welcome.pojo;

public class PinCodeInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String userType;
    private String pinCode;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PinCodeInfo(String userType, String pinCode) {
        this.userType = userType;
        this.pinCode = pinCode;
    }
}