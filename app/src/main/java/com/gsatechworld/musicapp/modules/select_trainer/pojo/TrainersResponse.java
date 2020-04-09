package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrainersResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    private String response;
    @SerializedName("message")
    private String message;
    @SerializedName("trainers_list")
    private List<Trainer> trainerList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainersResponse(String response, String message, List<Trainer> trainerList) {
        this.response = response;
        this.message = message;
        this.trainerList = trainerList;
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

    public List<Trainer> getTrainerList() {
        return trainerList;
    }
}