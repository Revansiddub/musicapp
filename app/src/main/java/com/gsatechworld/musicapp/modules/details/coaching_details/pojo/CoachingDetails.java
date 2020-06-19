package com.gsatechworld.musicapp.modules.details.coaching_details.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.modules.details.pojo.Slot_details;

import java.util.ArrayList;

public class CoachingDetails {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private boolean isHome;
    @SerializedName("coaching_types")
    private String coachingType;
    private boolean isInstitute;
    @SerializedName("address")
    private String address;
    private String charge;
    private boolean isDaily;
    private boolean isBiweekly;
    private boolean isWeekly;

    private ArrayList<Slot_details> slot_details;

    public ArrayList<Slot_details> getSlot_details() {
        return slot_details;
    }

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CoachingDetails(boolean isHome, boolean isInstitute, String address, String charge,
                           boolean isDaily, boolean isBiweekly, boolean isWeekly, ArrayList<Slot_details> slot_details) {
        this.isHome = isHome;
        this.isInstitute = isInstitute;
        this.address = address;
        this.charge = charge;
        this.isDaily = isDaily;
        this.isBiweekly = isBiweekly;
        this.isWeekly = isWeekly;
        this.slot_details = slot_details;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public boolean isHome() {
        return isHome;
    }

    public boolean isInstitute() {
        return isInstitute;
    }

    public String getAddress() {
        return address;
    }

    public String getCharge() {
        return charge;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public boolean isBiweekly() {
        return isBiweekly;
    }

    public boolean isWeekly() {
        return isWeekly;
    }
}