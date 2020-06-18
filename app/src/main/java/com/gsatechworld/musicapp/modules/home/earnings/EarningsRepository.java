package com.gsatechworld.musicapp.modules.home.earnings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class EarningsRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    EarningsRepository() {

    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

  public LiveData<EarningResponse> getEarningDetailsList(String trainerID) {
        MutableLiveData<EarningResponse> earningMutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<EarningResponse> responseCall=networkAPI.getEarnings(trainerID);
        responseCall.enqueue(new Callback<EarningResponse>() {
            @Override
            public void onResponse(Call<EarningResponse> call, Response<EarningResponse> response) {
                EarningResponse earningResponse=response.body();
                if (earningResponse != null){
                    earningMutableLiveData.setValue(earningResponse);
                }
            }

            @Override
            public void onFailure(Call<EarningResponse> call, Throwable t) {
              earningMutableLiveData.setValue(null);
            }
        });


        return earningMutableLiveData;
    }
}