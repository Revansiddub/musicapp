package com.gsatechworld.musicapp.modules.student_details.pojo;

public class StudentDetailsInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String fullName;
    private String age;
    private String gender;
    private String standard;
    private String schoolName;
    private String mobileNumber;
    private String address;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public StudentDetailsInfo(String fullName, String age, String gender, String standard,
                              String schoolName, String mobileNumber, String address) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.standard = standard;
        this.schoolName = schoolName;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }
}