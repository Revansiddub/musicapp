package com.gsatechworld.musicapp.modules.select_subcategory.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.select_category.pojo.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {



    @SerializedName("categories_list")
    private ArrayList<Categories> categoriesList;
    @SerializedName("status")
    private String status;

    public ArrayList<Categories> getCategoriesList() {
        return categoriesList;
    }




    public String getStatus() {
        return status;
    }


    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CategoryResponse(String status, ArrayList<Categories> categoriesList) {
        this.status = status;
        this.categoriesList = categoriesList;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */




}