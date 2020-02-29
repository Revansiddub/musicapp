package com.gsatechworld.musicapp.modules.select_time_slot.pojo;

import java.io.Serializable;

public class TimeSlot implements Serializable {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String startTime;
    private String endTime;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TimeSlot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}