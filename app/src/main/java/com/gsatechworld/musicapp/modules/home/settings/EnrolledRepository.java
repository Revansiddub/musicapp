package com.gsatechworld.musicapp.modules.home.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.settings.pojo.EnrollStudentsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnrolledRepository {
    public NetworkAPI networkAPI;

    public EnrolledRepository() {

    }

    public LiveData<EnrollStudentsResponse> getStudents(String trainerID){
        MutableLiveData<EnrollStudentsResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<EnrollStudentsResponse> responseCall=networkAPI.getEntrolledStudents(trainerID);

        responseCall.enqueue(new Callback<EnrollStudentsResponse>() {
            @Override
            public void onResponse(Call<EnrollStudentsResponse> call, Response<EnrollStudentsResponse> response) {
                EnrollStudentsResponse studentsResponse=response.body();
                if (studentsResponse != null){
                    mutableLiveData.setValue(studentsResponse);
                }
            }

            @Override
            public void onFailure(Call<EnrollStudentsResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }
}
