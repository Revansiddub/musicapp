package com.gsatechworld.musicapp.modules.home.trainer_home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AttendanceRepository {


    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    AttendanceRepository() {
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> addAttendance(AttendanceRequest request) {
        MutableLiveData<CommonResponse> trainerMutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<CommonResponse> responseCall=networkAPI.addAttendance(request);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse != null){
                    trainerMutableLiveData.postValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage().toString());
            }
        });



        return trainerMutableLiveData;
    }
}
