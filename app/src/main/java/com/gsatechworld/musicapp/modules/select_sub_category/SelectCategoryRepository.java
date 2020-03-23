package com.gsatechworld.musicapp.modules.select_sub_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.select_sub_category.pojo.SubCategory;
import com.gsatechworld.musicapp.modules.select_sub_category.pojo.SubCategoryResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class SelectCategoryRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    SelectCategoryRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<SubCategoryResponse> fetchCategories(String pinCode) {
        MutableLiveData<SubCategoryResponse> categoryMutableLiveData = new MutableLiveData<>();

        List<SubCategory> subCategoryList = new ArrayList<>();
        subCategoryList.add(new SubCategory("1", "Indie Rock"));
        subCategoryList.add(new SubCategory("2", "Acoustic Blues"));
        subCategoryList.add(new SubCategory("3", "Piano"));
        subCategoryList.add(new SubCategory("4", "Contemporary Classical"));
        subCategoryList.add(new SubCategory("5", "Trance"));
        subCategoryList.add(new SubCategory("6", "Underground Rap"));
        subCategoryList.add(new SubCategory("7", "Musical Soundtracks"));
        subCategoryList.add(new SubCategory("8", "Electronic Dance Music"));
        subCategoryList.add(new SubCategory("9", "Rock Music"));
        subCategoryList.add(new SubCategory("10", "Jazz"));
        subCategoryList.add(new SubCategory("11", "Dubstep"));
        subCategoryList.add(new SubCategory("12", "Rhythm and Blues"));
        subCategoryList.add(new SubCategory("13", "Techno"));
        subCategoryList.add(new SubCategory("14", "Country Music"));

        categoryMutableLiveData.postValue(new SubCategoryResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", subCategoryList));

        return categoryMutableLiveData;
    }
}