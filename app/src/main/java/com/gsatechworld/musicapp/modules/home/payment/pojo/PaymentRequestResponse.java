package com.gsatechworld.musicapp.modules.home.payment.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PaymentRequestResponse {

    @SerializedName("payment_requests")
    private ArrayList<Payment_requests> payment_requests;

    @SerializedName("status")
    private String status;


    public ArrayList<Payment_requests> getPayment_requests ()
    {
        return payment_requests;
    }

    public void setPayment_requests (ArrayList<Payment_requests> payment_requests)
    {
        this.payment_requests = payment_requests;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
