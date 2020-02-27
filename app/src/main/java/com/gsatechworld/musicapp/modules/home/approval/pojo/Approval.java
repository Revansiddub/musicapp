package com.gsatechworld.musicapp.modules.home.approval.pojo;

public class Approval {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String requestID;
    private String studentName;
    private String gender;
    private String age;
    private String startTime;
    private String endTime;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Approval(String requestID, String studentName, String gender, String age,
                    String startTime, String endTime) {
        this.requestID = requestID;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}