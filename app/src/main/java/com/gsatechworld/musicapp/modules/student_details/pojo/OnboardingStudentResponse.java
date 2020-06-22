package com.gsatechworld.musicapp.modules.student_details.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OnboardingStudentResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("trainers_list")
    private Trainers_lists[] trainers_list;

    @SerializedName("status")
    private String status;

    public String getMessage() {
        return message;
    }

    public Trainers_lists[] getTrainers_list() {
        return trainers_list;
    }

    public String getStatus() {
        return status;
    }

    public class Trainers_lists {
        @SerializedName("address")
        private String address;

        @SerializedName("coaching_types")
        private ArrayList<String> coaching_types;

        @SerializedName("available_slots")
        private ArrayList<Available_timeslots> available_slots;

        @SerializedName("recurrence_types")
        private String recurrence_types;

        @SerializedName("trainer_name")
        private String trainer_name;

        @SerializedName("coaching_days")
        private ArrayList<String> coaching_days;

        @SerializedName("trainer_id")
        private String trainer_id;

        @SerializedName("charge_amount")
        private String charge_amount;

        public String getAddress() {
            return address;
        }

        public ArrayList<String> getCoaching_types() {
            return coaching_types;
        }

        public ArrayList<Available_timeslots> getAvailable_slots() {
            return available_slots;
        }

        public String getRecurrence_types() {
            return recurrence_types;
        }

        public String getTrainer_name() {
            return trainer_name;
        }

        public ArrayList<String> getCoaching_days() {
            return coaching_days;
        }

        public String getTrainer_id() {
            return trainer_id;
        }

        public String getCharge_amount() {
            return charge_amount;
        }



        private class Available_timeslots {
            @SerializedName("time_slot_id")
            private String time_slot_id;

            @SerializedName("start_time")
            private String start_time;

            @SerializedName("end_time")
            private String end_time;


            public String getTime_slot_id() {
                return time_slot_id;
            }

            public String getStart_time() {
                return start_time;
            }

            public String getEnd_time() {
                return end_time;
            }



        }
    }
}
