package com.gsatechworld.musicapp.modules.login.pojo;

import com.google.gson.annotations.SerializedName;

public class TrainerLoginInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("firebase_token")
    private String firebase_token;









    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerLoginInfo(String userName, String password,String firebase_token) {
        this.userName = userName;
        this.password = password;
        this.firebase_token=firebase_token;
    }
}