package com.gsatechworld.musicapp.select_category.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoriesResponse {
    private String response;
    @SerializedName("status")
    private String status;
    @SerializedName("categories_list")
    private ArrayList<Categories> categoryList;


    public String getStatus() {
        return status;
    }



    public String getResponse() {
        return response;
    }







    public CategoriesResponse(String response, String status, ArrayList<Categories> categoryList) {
        this.response = response;
        this.status = status;
        this.categoryList = categoryList;
    }

    public List<Categories> getCategoriesList() {
        return categoryList;
    }


}
