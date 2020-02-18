package com.gsatechworld.musicapp.modules.welcome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class WelcomeRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    WelcomeRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> checkPinCodeAvailability(PinCodeInfo pinCodeInfo) {
        MutableLiveData<CommonResponse> pinCodeMutableLiveData = new MutableLiveData<>();

        pinCodeMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later."));

        return pinCodeMutableLiveData;
    }
}