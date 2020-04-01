package com.gsatechworld.musicapp.select_sub_category.pojo;

public class SubCategory {
    private String categoryID;

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    private String categoryName;

    public SubCategory(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

}
