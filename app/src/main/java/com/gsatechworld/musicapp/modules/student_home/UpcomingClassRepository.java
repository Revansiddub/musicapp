package com.gsatechworld.musicapp.modules.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.student_home.pojo.UpcomingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingClassRepository {

    public NetworkAPI networkAPI;

    public UpcomingClassRepository() {

    }

    public LiveData<UpcomingResponse> fetchUpcoming_class(String student_id){
        MutableLiveData<UpcomingResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<UpcomingResponse> responseCall=networkAPI.upcomingResponse(student_id);
        responseCall.enqueue(new Callback<UpcomingResponse>() {
            @Override
            public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                UpcomingResponse upcomingResponse=response.body();
                if (upcomingResponse != null){
                    mutableLiveData.setValue(upcomingResponse);
                }
            }

            @Override
            public void onFailure(Call<UpcomingResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
