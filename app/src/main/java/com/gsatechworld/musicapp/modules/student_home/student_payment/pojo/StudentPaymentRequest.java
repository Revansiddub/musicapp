package com.gsatechworld.musicapp.modules.student_home.student_payment.pojo;

import com.google.gson.annotations.SerializedName;

public class StudentPaymentRequest {
    @SerializedName("student_id")
    public String student_id;
    @SerializedName("enrollment_id")
    public String enrollment_id;

    @SerializedName("amount")
    public String amount;

    public String getStudent_id() {
        return student_id;
    }

    public String getEnrollment_id() {
        return enrollment_id;
    }

    public String getAmount() {
        return amount;
    }

    public StudentPaymentRequest(String student_id, String enrollment_id, String amount) {
        this.student_id = student_id;
        this.enrollment_id = enrollment_id;
        this.amount = amount;
    }





}
