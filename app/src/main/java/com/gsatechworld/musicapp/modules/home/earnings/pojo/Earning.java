package com.gsatechworld.musicapp.modules.home.earnings.pojo;

public class Earning {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String amount;
    private String date;
    private String studentName;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Earning(String amount, String date, String studentName) {
        this.amount = amount;
        this.date = date;
        this.studentName = studentName;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getStudentName() {
        return studentName;
    }
}
