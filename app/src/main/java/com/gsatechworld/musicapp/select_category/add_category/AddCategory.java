package com.gsatechworld.musicapp.select_category.add_category;

import com.google.gson.annotations.SerializedName;

public class AddCategory {
    @SerializedName("pincode")
    public String pincode;

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

    @SerializedName("category_name")
    public String category_name;

    public AddCategory(String pincode, String category_name) {
        this.pincode = pincode;
        this.category_name = category_name;
    }


}
