package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PendingPaymentsResp {

    @SerializedName("status")
    private String status;

    @SerializedName("pending_payments")
    private ArrayList<PendingPayments> pending_payments;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public ArrayList<PendingPayments> getPending_payments() {
        return pending_payments;
    }

    public String getMessage() {
        return message;
    }

    public class PendingPayments {

        @SerializedName("enrollment_id")
        private int enrollment_id;

        @SerializedName("student_name")
        private String student_name;

        @SerializedName("student_id")
        private int student_id;

        @SerializedName("amount")
        private int amount;

        public int getEnrollment_id() {
            return enrollment_id;
        }

        public String getStudent_name() {
            return student_name;
        }

        public int getStudent_id() {
            return student_id;
        }

        public int getAmount() {
            return amount;
        }
    }


}
