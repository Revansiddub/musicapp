package com.gsatechworld.musicapp.modules.select_subcategory.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */
    @SerializedName("subcategory_id")
    private String subcategoryID;

    @SerializedName("subcategory_name")
    private String categoryName;



    public SubCategory(String subcategoryID, String categoryName) {
        this.subcategoryID = subcategoryID;
        this.categoryName = categoryName;
    }


    public String getSubcategoryID() {
        return subcategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }


    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */





}