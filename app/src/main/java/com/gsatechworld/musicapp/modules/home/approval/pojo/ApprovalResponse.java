package com.gsatechworld.musicapp.modules.home.approval.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApprovalResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("status")
    private String response;

    @SerializedName("message")
    private String message;
    @SerializedName("approval_list")
    private ArrayList<Approval> approvalList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public ApprovalResponse(String response, String message, ArrayList<Approval> approvalList) {
        this.response = response;
        this.message = message;
        this.approvalList = approvalList;
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

    public List<Approval> getApprovalList() {
        return approvalList;
    }
}