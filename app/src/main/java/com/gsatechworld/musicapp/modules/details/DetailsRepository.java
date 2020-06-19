package com.gsatechworld.musicapp.modules.details;

import android.net.Network;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.details.pojo.OnBoadingTrainer;
import com.gsatechworld.musicapp.modules.details.pojo.TrainerDetails;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> submitTrainerDetails(OnBoadingTrainer details) {
        MutableLiveData<CommonResponse> trainerMutableLiveData = new MutableLiveData<>();

        networkAPI = NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<CommonResponse> responseCall= networkAPI.addTrainerDetails(details);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse= response.body();
                trainerMutableLiveData.postValue(commonResponse);

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                trainerMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                        "Oops! something went wrong. Please try again later."));
            }
        });

        return trainerMutableLiveData;
    }
}