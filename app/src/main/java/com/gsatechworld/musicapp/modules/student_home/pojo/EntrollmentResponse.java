package com.gsatechworld.musicapp.modules.student_home.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EntrollmentResponse {
    @SerializedName("status")
    private String response;

    public String getResponse() {
        return response;
    }

    public ArrayList<Enrollment_details> getEnrollment_details() {
        return enrollment_details;
    }

    @SerializedName("enrollment_details")
    private ArrayList<Enrollment_details> enrollment_details;


    public static class Enrollment_details {
        @SerializedName("entrollment_name")
        private String entrollment_name;

        @SerializedName("entrollment_id")
        private String entrollment_id;

        public String getEntrollment_name() {
            return entrollment_name;
        }

        public String getEntrollment_id() {
            return entrollment_id;
        }





    }
}
