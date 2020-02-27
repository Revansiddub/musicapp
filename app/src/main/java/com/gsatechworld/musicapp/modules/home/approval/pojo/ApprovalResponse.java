package com.gsatechworld.musicapp.modules.home.approval.pojo;

import java.util.List;

public class ApprovalResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<Approval> approvalList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public ApprovalResponse(String response, String message, List<Approval> approvalList) {
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