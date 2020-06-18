package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo;

import com.google.gson.annotations.SerializedName;

public class Student_list {
    @SerializedName("amount")
    private String amount;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    public String getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }




}
