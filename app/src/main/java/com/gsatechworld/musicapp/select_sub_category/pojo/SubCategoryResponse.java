package com.gsatechworld.musicapp.select_sub_category.pojo;

import com.gsatechworld.musicapp.modules.select_category.pojo.Category;

import java.util.List;

public class SubCategoryResponse {
    private String response;
    private String message;

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }



    private List<SubCategory> categoryList;


    public SubCategoryResponse(String response, String message, List<SubCategory> categoryList) {
        this.response = response;
        this.message = message;
        this.categoryList = categoryList;
    }

    public List<SubCategory> getSubCategoryList() {
        return categoryList;
    }


}
