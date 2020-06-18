package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PaymentResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    private String response;
    private String message;

    private List<Payment> pendingPaymentList;

    public ArrayList<Student_list> getStudentLists() {
        return studentLists;
    }

    @SerializedName("student_list")
    private ArrayList<Student_list> studentLists;



    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PaymentResponse(String response, String message, ArrayList<Student_list> pendingPaymentList) {
        this.response = response;
        this.message = message;
        this.studentLists = pendingPaymentList;
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

    public List<Payment> getPendingPaymentList() {
        return pendingPaymentList;
    }
}