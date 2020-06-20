package com.gsatechworld.musicapp.modules.select_trainer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.Trainer;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class SelectTrainerRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    SelectTrainerRepository() {

    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainersResponse> fetchTrainers(TrainerInfo info) {
        MutableLiveData<TrainersResponse> pinCodeMutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<TrainersResponse> call=networkAPI.fetchTrainers(info);
        call.enqueue(new Callback<TrainersResponse>() {
            @Override
            public void onResponse(Call<TrainersResponse> call, Response<TrainersResponse> response) {
                TrainersResponse trainersResponse=response.body();
                if (trainersResponse != null){
                    pinCodeMutableLiveData.setValue(trainersResponse);
                }
            }

            @Override
            public void onFailure(Call<TrainersResponse> call, Throwable t) {
                pinCodeMutableLiveData.setValue(null);

            }
        });




        return pinCodeMutableLiveData;
    }
}