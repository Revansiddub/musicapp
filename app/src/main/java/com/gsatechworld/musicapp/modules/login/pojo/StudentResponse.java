package com.gsatechworld.musicapp.modules.login.pojo;

import com.google.gson.annotations.SerializedName;

public class StudentResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    private String studentID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public StudentResponse(String response, String message, String studentID) {
        this.status = response;
        this.message = message;
        this.studentID = studentID;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getStudentID() {
        return studentID;
    }
}