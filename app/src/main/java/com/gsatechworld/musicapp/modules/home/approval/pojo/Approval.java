package com.gsatechworld.musicapp.modules.home.approval.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Approval {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String requestID;
    @SerializedName("student_name")
    private String studentName;

    private String gender;
    @SerializedName("age")
    private String age;



    @SerializedName("slot_details")
    public List<Slot_Details> slot_detailsList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Approval(String requestID, String studentName, String gender, String age,
                    List<Slot_Details> slot_details) {
        this.requestID = requestID;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.slot_detailsList=slot_details;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getRequestID() {
        return requestID;
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

    public List<Slot_Details> getSlot_detailsList() {
        return slot_detailsList;
    }


}