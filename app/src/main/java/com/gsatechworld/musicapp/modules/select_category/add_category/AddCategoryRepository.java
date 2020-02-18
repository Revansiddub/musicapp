package com.gsatechworld.musicapp.modules.select_category.add_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class AddCategoryRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    AddCategoryRepository() {
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