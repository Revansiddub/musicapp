package com.gsatechworld.musicapp.modules.home.payment.adapter;

import com.google.gson.annotations.SerializedName;

public class AcceptPayment {

    @SerializedName("trainer_id")
    public String trainer_id;

    @SerializedName("payment_request_id")
    public String payment_request_id;

    public AcceptPayment(String trainer_id, String payment_request_id) {
        this.trainer_id = trainer_id;
        this.payment_request_id = payment_request_id;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public String getPayment_request_id() {
        return payment_request_id;
    }



}
