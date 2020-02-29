package com.gsatechworld.musicapp.modules.select_time_slot.pojo;

import java.util.List;

public class TimeSlotResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<TimeSlot> timeSlotList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TimeSlotResponse(String response, String message, List<TimeSlot> timeSlotList) {
        this.response = response;
        this.message = message;
        this.timeSlotList = timeSlotList;
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

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }
}