package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo;

public class PaymentActionInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String paymentID;
    private String actionTaken;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PaymentActionInfo(String paymentID, String actionTaken) {
        this.paymentID = paymentID;
        this.actionTaken = actionTaken;
    }
}