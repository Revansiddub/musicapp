package com.gsatechworld.musicapp.modules.details.coaching_details.pojo;

public class CoachingDetails {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private boolean isHome;
    private boolean isInstitute;
    private String address;
    private String charge;
    private boolean isDaily;
    private boolean isBiweekly;
    private boolean isWeekly;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CoachingDetails(boolean isHome, boolean isInstitute, String address, String charge,
                           boolean isDaily, boolean isBiweekly, boolean isWeekly) {
        this.isHome = isHome;
        this.isInstitute = isInstitute;
        this.address = address;
        this.charge = charge;
        this.isDaily = isDaily;
        this.isBiweekly = isBiweekly;
        this.isWeekly = isWeekly;
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