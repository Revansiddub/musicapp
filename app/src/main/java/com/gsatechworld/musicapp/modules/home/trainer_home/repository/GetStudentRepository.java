package com.gsatechworld.musicapp.modules.home.trainer_home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.FetchStudentsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStudentRepository {

    public NetworkAPI networkAPI;

    public GetStudentRepository() {
    }

    public LiveData<FetchStudentsResponse> getStudents(String trainer_ID, String date) {

        MutableLiveData<FetchStudentsResponse> mutableLiveData = new MutableLiveData<>();

        networkAPI = NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<FetchStudentsResponse> responseCall = networkAPI.getStudentBydate(trainer_ID,date);
        responseCall.enqueue(new Callback<FetchStudentsResponse>() {
            @Override
            public void onResponse(Call<FetchStudentsResponse> call, Response<FetchStudentsResponse> response) {
                FetchStudentsResponse fetchStudentsResponse = response.body();
                if (fetchStudentsResponse != null) {
                    mutableLiveData.setValue(fetchStudentsResponse);
                }

            }

            @Override
            public void onFailure(Call<FetchStudentsResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }

}

