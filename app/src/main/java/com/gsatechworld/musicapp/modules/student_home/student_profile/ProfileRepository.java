package com.gsatechworld.musicapp.modules.student_home.student_profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.student_home.student_profile.pojo.StudentProfileResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    public NetworkAPI networkAPI;
    private ArrayList<StudentProfileResponse> responseArrayList = new ArrayList<>();

    public ProfileRepository() {
    }

    public LiveData<StudentProfileResponse> fetchProfile(String student_id){
        MutableLiveData<StudentProfileResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<StudentProfileResponse> responseCall=networkAPI.fetchstudentProfile(student_id);
        responseCall.enqueue(new Callback<StudentProfileResponse>() {
            @Override
            public void onResponse(Call<StudentProfileResponse> call, Response<StudentProfileResponse> response) {
                StudentProfileResponse profileResponse=response.body();
                if (profileResponse != null){
                    mutableLiveData.setValue(profileResponse);
                }
            }

            @Override
            public void onFailure(Call<StudentProfileResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
