package com.gsatechworld.musicapp.modules.select_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.select_category.pojo.Category;
import com.gsatechworld.musicapp.modules.select_category.pojo.CategoryResponse;

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

    LiveData<CategoryResponse> fetchCategories(String pinCode) {
        MutableLiveData<CategoryResponse> categoryMutableLiveData = new MutableLiveData<>();

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("1", "Indie Rock"));
        categoryList.add(new Category("2", "Acoustic Blues"));
        categoryList.add(new Category("3", "Piano"));
        categoryList.add(new Category("4", "Contemporary Classical"));
        categoryList.add(new Category("5", "Trance"));
        categoryList.add(new Category("6", "Underground Rap"));
        categoryList.add(new Category("7", "Musical Soundtracks"));
        categoryList.add(new Category("8", "Electronic Dance Music"));
        categoryList.add(new Category("9", "Rock Music"));
        categoryList.add(new Category("10", "Jazz"));
        categoryList.add(new Category("11", "Dubstep"));
        categoryList.add(new Category("12", "Rhythm and Blues"));
        categoryList.add(new Category("13", "Techno"));
        categoryList.add(new Category("14", "Country Music"));

        categoryMutableLiveData.postValue(new CategoryResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", categoryList));

        return categoryMutableLiveData;
    }
}