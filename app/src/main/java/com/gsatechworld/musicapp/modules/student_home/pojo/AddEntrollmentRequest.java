package com.gsatechworld.musicapp.modules.student_home.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.modules.student_details.pojo.OnboardingRequest;

import java.util.ArrayList;

public class AddEntrollmentRequest {
    @SerializedName("student_id")
    public String student_id;
    @SerializedName("trainer_id")
    public String trainer_id;
    @SerializedName("category_id")
    public String category_id;
    @SerializedName("sub_category_id")
    public String sub_category_id;
    @SerializedName("slot_details")
    public ArrayList<Time_slot_Details> slot_details;

    public AddEntrollmentRequest(String student_id, String trainer_id, String category_id, String sub_category_id, ArrayList<Time_slot_Details> time_slot_details) {
        this.student_id = student_id;
        this.trainer_id = trainer_id;
        this.category_id = category_id;
        this.sub_category_id = sub_category_id;
        this.slot_details = time_slot_details;
    }


    public String getStudent_id() {
        return student_id;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getSub_category_id() {
        return sub_category_id;
    }

    public ArrayList<Time_slot_Details> getSlot_details() {
        return slot_details;
    }




    public static class Time_slot_Details {
        @SerializedName("start_time")
        private String start_time;

        @SerializedName("end_time")
        private String end_time;

        public Time_slot_Details(String start_time, String end_time) {
            this.start_time = start_time;
            this.end_time = end_time;
        }



        public String getStart_time() {
            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }



    }
}
