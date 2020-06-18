package com.gsatechworld.musicapp.modules.home.trainer_home.pojo;

import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.util.ArrayList;

public class Available_slots {


    private ArrayList<TimeSlot> slot_details;

    private String recurrence_type;


    public String getRecurrence_type() {
        return recurrence_type;
    }

    public String[] getCoaching_days() {
        return coaching_days;
    }

    public ArrayList<TimeSlot> getSlot_details() {
        return slot_details;
    }

    private String[] coaching_days;
}
