package com.gsatechworld.musicapp.modules.home.earnings.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class EarningResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    @SerializedName("status")
    private String response;

    @SerializedName("result")
    private EarningResult result;


    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public EarningResponse(String response,EarningResult earningList) {
        this.response = response;
        this.result = earningList;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public EarningResult getResult()
    {
        return result;
    }

    public String getResponse() {
        return response;
    }




}
