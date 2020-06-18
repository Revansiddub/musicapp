package com.gsatechworld.musicapp.modules.details.pojo;

import com.google.gson.annotations.SerializedName;

public class Recurrence_types {

    @SerializedName("slot_details")
    private Slot_details[] slot_details;


    public static void setRecurrence_type(String recurrence_type) {
        Recurrence_types.recurrence_type = recurrence_type;
    }

    public void setSlot_details(Slot_details[] slot_details) {
        this.slot_details = slot_details;
    }

    @SerializedName("recurrence_type")
    public static String recurrence_type;

    public void setCoaching_days(String[] coaching_days) {
        this.coaching_days = coaching_days;
    }

    @SerializedName("coaching_days")
    public String[] coaching_days;

    public Slot_details[] getSlot_details() {
        return slot_details;
    }

    public String getRecurrence_type() {
        return recurrence_type;
    }

    public String[] getCoaching_days() {
        return coaching_days;
    }


}
