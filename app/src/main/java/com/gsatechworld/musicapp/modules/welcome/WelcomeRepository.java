package com.gsatechworld.musicapp.modules.welcome;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class WelcomeRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;
    public Context context;
    private static final String TAG = "WelcomeRepository";


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

        NetworkAPI api= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<CommonResponse> call=api.checkAvailability(pinCodeInfo);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
            pinCodeMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {

            }
        });




        return pinCodeMutableLiveData;
    }
}