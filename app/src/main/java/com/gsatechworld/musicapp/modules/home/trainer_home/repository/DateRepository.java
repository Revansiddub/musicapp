package com.gsatechworld.musicapp.modules.home.trainer_home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.DateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateRepository {
    public NetworkAPI networkAPI;

    public DateRepository() {
    }

    public LiveData<DateResponse> getDates(String trainer_id,String month,String year){
        MutableLiveData<DateResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<DateResponse> responseCall=networkAPI.getDates(trainer_id,month,year);
        responseCall.enqueue(new Callback<DateResponse>() {
            @Override
            public void onResponse(Call<DateResponse> call, Response<DateResponse> response) {
                DateResponse dateResponse=response.body();
                if (dateResponse != null){
                    mutableLiveData.setValue(dateResponse);
                }
            }

            @Override
            public void onFailure(Call<DateResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
