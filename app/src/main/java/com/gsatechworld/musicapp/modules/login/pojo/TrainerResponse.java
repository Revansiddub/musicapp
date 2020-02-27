package com.gsatechworld.musicapp.modules.login.pojo;

public class TrainerResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private String trainerID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerResponse(String response, String message, String trainerID) {
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

    public String getTrainerID() {
        return trainerID;
    }
}