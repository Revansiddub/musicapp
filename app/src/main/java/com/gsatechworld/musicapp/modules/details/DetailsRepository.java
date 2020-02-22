package com.gsatechworld.musicapp.modules.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.details.pojo.TrainerDetails;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class DetailsRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    DetailsRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> submitTrainerDetails(TrainerDetails details) {
        MutableLiveData<CommonResponse> trainerMutableLiveData = new MutableLiveData<>();

        trainerMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later."));

        return trainerMutableLiveData;
    }
}