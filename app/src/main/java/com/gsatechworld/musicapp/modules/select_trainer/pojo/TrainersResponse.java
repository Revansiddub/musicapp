package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrainersResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    public String response;
    @SerializedName("message")
    public String message;
    @SerializedName("trainers_list")
    private ArrayList<Trainers_list> trainers_list;

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Trainers_list> getTrainers_list() {
        return trainers_list;
    }

    public class Trainers_list {
        @SerializedName("trainer_id")
        private int trainer_id;

        @SerializedName("trainer_name")
        private String trainer_name;

        @SerializedName("charge_amount")
        private int charge_amount;

        @SerializedName("address")
        private String address;

        @SerializedName("coaching_types")
        private ArrayList<String> coaching_types;

        @SerializedName("coaching_days")
        private ArrayList<String> coaching_days;

        @SerializedName("recurrence_types")
        private String recurrence_types;

        @SerializedName("available_slots")
        public ArrayList<Availables_slots> available_slots;

        public int getTrainer_id() {
            return trainer_id;
        }

        public String getTrainer_name() {
            return trainer_name;
        }

        public int getCharge_amount() {
            return charge_amount;
        }

        public String getAddress() {
            return address;
        }

        public ArrayList<String> getCoaching_types() {
            return coaching_types;
        }

        public ArrayList<String> getCoaching_days() {
            return coaching_days;
        }

        public String getRecurrence_types() {
            return recurrence_types;
        }

        public ArrayList<Availables_slots> getAvailable_slots() {
            return available_slots;
        }


        public class Availables_slots {
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