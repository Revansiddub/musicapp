package com.gsatechworld.musicapp.modules.select_time_slot.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AvailableTimesSlotResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    @SerializedName("available_slots")
    private ArrayList<AvailablesSlotes> available_slots;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<AvailablesSlotes> getAvailable_slots() {
        return available_slots;
    }



    public class AvailablesSlotes{

        @SerializedName("start_time")
        public String start_time;

        public String getStart_time() {
            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        @SerializedName("end_time")
        public String end_time;




    }

}
