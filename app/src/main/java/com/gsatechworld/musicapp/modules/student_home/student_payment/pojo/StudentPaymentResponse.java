package com.gsatechworld.musicapp.modules.student_home.student_payment.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;

import java.util.ArrayList;
import java.util.List;

public class StudentPaymentResponse {
    @SerializedName("status")
    private String response;

    @SerializedName("pending_payments")
    private ArrayList<Pending_payments> pending_payments;

    public String getResponse() {
        return response;
    }

    public ArrayList<Pending_payments> getPending_payments() {
        return pending_payments;
    }




    public class Pending_payments {
        @SerializedName("enrollment_id")
        private String enrollment_id;

        @SerializedName("enrollment_name")
        private String enrollment_name;

        @SerializedName("amount")
        private String amount;

        public String getEnrollment_id() {
            return enrollment_id;
        }

        public String getEnrollment_name() {
            return enrollment_name;
        }

        public String getAmount() {
            return amount;
        }
    }
}
