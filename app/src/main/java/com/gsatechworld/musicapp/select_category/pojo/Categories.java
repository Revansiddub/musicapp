package com.gsatechworld.musicapp.select_category.pojo;

import com.google.gson.annotations.SerializedName;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class Categories {
    @SerializedName("category_id")
    private String categoryID;

    @SerializedName("category_name")
    public String categoryName;


    public ArrayList<SubCategory> getSubcategories_list() {
        return subcategories_list;
    }

    @SerializedName("subcategories_list")
    public ArrayList<SubCategory> subcategories_list;


    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }







    public Categories(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

}
