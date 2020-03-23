package com.gsatechworld.musicapp.modules.select_sub_category.pojo;

public class SubCategory {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String categoryID;
    private String categoryName;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SubCategory(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}