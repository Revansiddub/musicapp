package com.gsatechworld.musicapp.modules.select_category.pojo;

import java.util.List;

public class CategoryResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<Category> categoryList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CategoryResponse(String response, String message, List<Category> categoryList) {
        this.response = response;
        this.message = message;
        this.categoryList = categoryList;
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

    public List<Category> getCategoryList() {
        return categoryList;
    }
}