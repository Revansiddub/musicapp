package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrainersResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    private String response;
    @SerializedName("message")
    private String message;
    @SerializedName("trainers_list")
    private ArrayList<Trainers_list> trainers_list;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainersResponse(String response, String message, ArrayList<Trainers_list> trainerList) {
        this.response = response;
        this.message = message;
        this.trainers_list = trainerList;
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

    public ArrayList<Trainers_list> getTrainerList() {
        return trainers_list;
    }

    public class Trainers_list {
        private String address;

        private String[] coaching_types;

        private ArrayList<Availables_slots> available_slots;

        private String recurrence_types;

        private String trainer_name;

        private String coaching_days;

        private String trainer_id;

        public String getAddress() {
            return address;
        }

        public String[] getCoaching_types() {
            return coaching_types;
        }

        public ArrayList<Availables_slots> getAvailable_slots() {
            return available_slots;
        }

        public String getRecurrence_types() {
            return recurrence_types;
        }

        public String getTrainer_name() {
            return trainer_name;
        }

        public String getCoaching_days() {
            return coaching_days;
        }

        public String getTrainer_id() {
            return trainer_id;
        }

        public String getCharge_amount() {
            return charge_amount;
        }

        private String charge_amount;


        public class Availables_slots{
            private String time_slot_id;

            private String start_time;

            public String getTime_slot_id() {
                return time_slot_id;
            }

            public String getStart_time() {
                return start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            private String end_time;

        }
    }

}