package com.gsatechworld.musicapp.modules.login.pojo;

public class StudentResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private String studentID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public StudentResponse(String response, String message, String studentID) {
        this.response = response;
        this.message = message;
        this.studentID = studentID;
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

    public String getStudentID() {
        return studentID;
    }
}