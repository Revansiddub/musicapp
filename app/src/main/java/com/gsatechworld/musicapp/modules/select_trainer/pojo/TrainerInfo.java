package com.gsatechworld.musicapp.modules.select_trainer.pojo;

public class TrainerInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String pinCode;
    private String categoryID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerInfo(String pinCode, String categoryID) {
        this.pinCode = pinCode;
        this.categoryID = categoryID;
    }
}