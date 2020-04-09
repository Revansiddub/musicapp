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







    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerLoginInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}