package com.gsatechworld.musicapp.modules.select_subcategory.add_subcategory;

import com.google.gson.annotations.SerializedName;

public class AddSubCategory {
    @SerializedName("pincode")
    public String pincode;

    @SerializedName("category_id")
    public String category_name;

    public AddSubCategory(String pincode, String category_name, String subcategory_name) {
        this.pincode = pincode;
        this.category_name = category_name;
        this.subcategory_name = subcategory_name;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    @SerializedName("subcategory_name")
    public String subcategory_name;




    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }



}
