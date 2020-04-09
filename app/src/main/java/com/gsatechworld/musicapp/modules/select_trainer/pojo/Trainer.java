package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trainer {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("trainer_id")
    private String trainerID;
    @SerializedName("trainer_name")
    private String trainerName;
    @SerializedName("coaching_types")
    private List<String> coachingType;
    @SerializedName("charge_amount")
    private String charges;
    @SerializedName("address")
    private String address;


    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Trainer(String trainerID, String trainerName, List<String> coachingType, String charges,
                   String address) {
        this.trainerID = trainerID;
        this.trainerName = trainerName;
        this.coachingType = coachingType;
        this.charges = charges;
        this.address = address;

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


}