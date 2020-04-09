package com.gsatechworld.musicapp.modules.login.pojo;

import com.google.gson.annotations.SerializedName;

public class TrainerResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    private String response;

    @SerializedName("message")
    private String message;



    @SerializedName("trainer_id")
    private int trainerID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerResponse(String response, String message, int trainerID) {
        this.response = response;
        this.message = message;
        this.trainerID = trainerID;
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

    public int getTrainerID() {
        return trainerID;
    }
}