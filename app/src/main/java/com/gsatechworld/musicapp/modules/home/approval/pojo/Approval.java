package com.gsatechworld.musicapp.modules.home.approval.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Approval {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    @SerializedName("student_id")
    private String studentID;


    @SerializedName("student_name")
    private String studentName;



    private String gender;
    @SerializedName("age")
    private String age;



    @SerializedName("slot_details")
    public TimeSlot_Details slot_detailsList;



    @SerializedName("enrollment_id")
    private String enrollment_id;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Approval(String enrollment_id,String studentID, String studentName, String gender, String age,
                    TimeSlot_Details slot_details) {
        this.enrollment_id=enrollment_id;
        this.studentID = studentID;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.slot_detailsList=slot_details;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public TimeSlot_Details getSlot_detailsList() {
        return slot_detailsList;
    }

    public String getEnrollment_id() {
        return enrollment_id;
    }


    public class TimeSlot_Details {
        @SerializedName("start_time")
        private String start_time;

        @SerializedName("end_time")
        private String end_time;

        public String getStart_time() {
            return start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

    }
}