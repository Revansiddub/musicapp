package com.gsatechworld.musicapp.utilities;

import com.google.gson.annotations.SerializedName;

public class CommonResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    @SerializedName("response")
    private String response;
    @SerializedName("message")
    private String message;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CommonResponse(String result, String message) {
        this.response = result;
        this.message = message;
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
}
