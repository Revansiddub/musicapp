package com.gsatechworld.musicapp.modules.student_home.student_payment.pojo;

public class StudentPayment {
    private String entrollment_name;

    public StudentPayment(String entrollment_name, String amount) {
        this.entrollment_name = entrollment_name;
        this.amount = amount;
    }

    public String getEntrollment_name() {
        return entrollment_name;
    }

    public void setEntrollment_name(String entrollment_name) {
        this.entrollment_name = entrollment_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String amount;

}
