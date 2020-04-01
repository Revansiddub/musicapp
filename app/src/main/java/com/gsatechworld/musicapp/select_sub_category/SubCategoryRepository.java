package com.gsatechworld.musicapp.select_sub_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.select_category.pojo.Category;
import com.gsatechworld.musicapp.modules.select_category.pojo.CategoryResponse;
import com.gsatechworld.musicapp.select_sub_category.pojo.SubCategory;
import com.gsatechworld.musicapp.select_sub_category.pojo.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

public class SubCategoryRepository {
    private NetworkAPI networkAPI;


    public SubCategoryRepository() {
    }

    LiveData<SubCategoryResponse> fetchCategories(String pinCode) {
        MutableLiveData<SubCategoryResponse> categoryMutableLiveData = new MutableLiveData<>();

        List<SubCategory> categoryList = new ArrayList<>();
        categoryList.add(new SubCategory("1", "Music"));
        categoryList.add(new SubCategory("2", "Sports"));


        categoryMutableLiveData.postValue(new SubCategoryResponse(SERVER_RESPONSE_SUCCESS, "Oops! something went wrong. Please try again later.", categoryList));

        return categoryMutableLiveData;
    }
}
