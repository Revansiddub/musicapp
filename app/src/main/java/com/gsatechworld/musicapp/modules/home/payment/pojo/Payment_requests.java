package com.gsatechworld.musicapp.modules.home.payment.pojo;

public class Payment_requests {
    private String student_name;

    private String amount;

    private String requested_date;

    private String student_id;

    private String payment_request_id;

    public String getStudent_name ()
    {
        return student_name;
    }

    public void setStudent_name (String student_name)
    {
        this.student_name = student_name;
    }

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getRequested_date ()
    {
        return requested_date;
    }

    public void setRequested_date (String requested_date)
    {
        this.requested_date = requested_date;
    }

    public String getStudent_id ()
    {
        return student_id;
    }

    public void setStudent_id (String student_id)
    {
        this.student_id = student_id;
    }

    public String getPayment_request_id ()
    {
        return payment_request_id;
    }

    public void setPayment_request_id (String payment_request_id)
    {
        this.payment_request_id = payment_request_id;
    }
}
