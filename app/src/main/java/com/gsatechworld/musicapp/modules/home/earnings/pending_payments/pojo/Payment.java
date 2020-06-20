package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo;

public class Payment {

    private String paymentID;
    private String studentName;
    private String date;
    private String amount;

    public Payment(String paymentID, String studentName, String date, String amount) {
        this.paymentID = paymentID;
        this.studentName = studentName;
        this.date = date;
        this.amount = amount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}