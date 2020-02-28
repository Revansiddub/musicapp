package com.gsatechworld.musicapp.modules.home.earnings.pojo;

import java.util.List;

public class EarningResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<Earning> earningList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public EarningResponse(String response, String message, List<Earning> earningList) {
        this.response = response;
        this.message = message;
        this.earningList = earningList;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public List<Earning> getEarningList() {
        return earningList;
    }
}
