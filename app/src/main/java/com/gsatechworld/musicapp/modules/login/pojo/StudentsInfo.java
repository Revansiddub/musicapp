package com.gsatechworld.musicapp.modules.login.pojo;

import com.google.gson.annotations.SerializedName;

public class StudentsInfo {
    public String getMobile_number() {
        return mobile_number;
    }

    @SerializedName("mobile_number")
    public String mobile_number;
}
