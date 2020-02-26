package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import java.util.List;

public class TrainerResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<Trainer> trainerList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerResponse(String response, String message, List<Trainer> trainerList) {
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