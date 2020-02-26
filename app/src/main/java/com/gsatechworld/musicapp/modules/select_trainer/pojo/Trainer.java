package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import java.util.List;

public class Trainer {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String trainerID;
    private String trainerName;
    private List<String> coachingType;
    private String charges;
    private String address;
    private List<String> recurrenceType;
    private List<String> recurrenceDays;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Trainer(String trainerID, String trainerName, List<String> coachingType, String charges,
                   String address, List<String> recurrenceType, List<String> recurrenceDays) {
        this.trainerID = trainerID;
        this.trainerName = trainerName;
        this.coachingType = coachingType;
        this.charges = charges;
        this.address = address;
        this.recurrenceType = recurrenceType;
        this.recurrenceDays = recurrenceDays;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getTrainerID() {
        return trainerID;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public List<String> getCoachingType() {
        return coachingType;
    }

    public String getCharges() {
        return charges;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getRecurrenceType() {
        return recurrenceType;
    }

    public List<String> getRecurrenceDays() {
        return recurrenceDays;
    }
}