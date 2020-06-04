package com.gsatechworld.musicapp.modules.select_subcategory.add_subcategory;

import com.google.gson.annotations.SerializedName;

public class AddSubCategory {
    @SerializedName("pincode")
    public String pincode;

    public AddSubCategory(String pincode, String category_name) {
        this.pincode = pincode;
        this.category_name = category_name;
    }

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

}
