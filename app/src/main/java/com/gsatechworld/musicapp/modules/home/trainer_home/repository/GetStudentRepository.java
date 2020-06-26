package com.gsatechworld.musicapp.modules.home.trainer_home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.GetStudentsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStudentRepository {

    public NetworkAPI networkAPI;

    public GetStudentRepository() {
    }

    public LiveData<GetStudentsResponse> getStudents(int trainer_ID) {

        MutableLiveData<GetStudentsResponse> mutableLiveData = new MutableLiveData<>();

        networkAPI = NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<GetStudentsResponse> responseCall = networkAPI.getStudentBydate(trainer_ID);
        responseCall.enqueue(new Callback<GetStudentsResponse>() {
            @Override
            public void onResponse(Call<GetStudentsResponse> call, Response<GetStudentsResponse> response) {
                GetStudentsResponse getStudentsResponse = response.body();
                if (getStudentsResponse != null) {
                    mutableLiveData.setValue(getStudentsResponse);
                }

            }

            @Override
            public void onFailure(Call<GetStudentsResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }

}

