package com.gsatechworld.musicapp.modules.select_sub_category.pojo;

import java.util.List;

public class SubCategoryResponse {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String response;
    private String message;
    private List<SubCategory> subCategoryList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SubCategoryResponse(String response, String message, List<SubCategory> subCategoryList) {
        this.response = response;
        this.message = message;
        this.subCategoryList = subCategoryList;
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

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }
}