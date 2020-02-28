package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo;

import java.util.List;

public class PaymentResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<Payment> pendingPaymentList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PaymentResponse(String response, String message, List<Payment> pendingPaymentList) {
        this.response = response;
        this.message = message;
        this.pendingPaymentList = pendingPaymentList;
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