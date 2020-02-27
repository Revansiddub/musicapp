package com.gsatechworld.musicapp.modules.home.approval.pojo;

public class ActionInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String requestID;
    private String actionTaken;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public ActionInfo(String requestID, String actionTaken) {
        this.requestID = requestID;
        this.actionTaken = actionTaken;
    }
}