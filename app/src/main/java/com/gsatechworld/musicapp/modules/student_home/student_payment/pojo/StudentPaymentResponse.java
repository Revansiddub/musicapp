package com.gsatechworld.musicapp.modules.student_home.student_payment.pojo;

import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;

import java.util.List;

public class StudentPaymentResponse {
    private String response;
    private String message;

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public List<StudentPayment> getPaymentList() {
        return paymentList;
    }

    public StudentPaymentResponse(String response, String message, List<StudentPayment> paymentList) {
        this.response = response;
        this.message = message;
        this.paymentList = paymentList;
    }

    private List<StudentPayment> paymentList;


}
