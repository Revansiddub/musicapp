package com.gsatechworld.musicapp.modules.select_trainer.pojo;

import com.google.gson.annotations.SerializedName;

public class TrainerInfo {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("pincode_id")
    private String pinCode;
    @SerializedName("category_id")
    private String categoryID;

    public String getSubCategoryID() {
        return subCategoryID;
    }

    @SerializedName("sub_category_id")
    private String subCategoryID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerInfo(String pinCode, String categoryID,String subCategoryID) {
        this.pinCode = pinCode;
        this.categoryID = categoryID;
        this.subCategoryID=subCategoryID;
    }
}