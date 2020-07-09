package com.gsatechworld.musicapp.modules.student_home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpcomingResponse {
    @SerializedName("status")
    public String status;

    @SerializedName("upcoming_class")
    private ArrayList<Upcoming_class> upcoming_class;

    public ArrayList<Upcoming_class> getUpcoming_class() {
        return upcoming_class;
    }


    public String getStatus() {
        return status;
    }


    public class Upcoming_class {
        @SerializedName("date")
        public String date;

        @SerializedName("start_time")
        public String start_time;

        @SerializedName("end_time")
        public String end_time;


        @SerializedName("cancel_status")
        private String cancel_status;


        public String getCancel_status() {
            return cancel_status;
        }


        public String getDate() {
            return date;
        }

        public String getStart_time() {
            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }


    }
}
