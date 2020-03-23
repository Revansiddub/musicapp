package com.gsatechworld.musicapp.modules.select_sub_category.add_sub_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class AddSubCategoryRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    AddSubCategoryRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> addCategory(String category) {
        MutableLiveData<CommonResponse> categoryMutableLiveData = new MutableLiveData<>();

        categoryMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later."));

        return categoryMutableLiveData;
    }
}