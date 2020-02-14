package com.gsatechworld.musicapp.modules.select_category.pojo;

public class Category {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String categoryID;
    private String categoryName;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public Category(String categoryID, String categoryName) {
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