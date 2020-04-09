package com.gsatechworld.musicapp.modules.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentsResponse;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class LoginRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    LoginRepository() {
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainerResponse> authenticateTrainer(TrainerLoginInfo info) {
        MutableLiveData<TrainerResponse> trainerMutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<TrainerResponse>responseCall=networkAPI.checkLogin(info);
        responseCall.enqueue(new Callback<TrainerResponse>() {
            @Override
            public void onResponse(Call<TrainerResponse> call, Response<TrainerResponse> response) {
                TrainerResponse trainerResponse=response.body();
                if (trainerResponse != null){
                    trainerMutableLiveData.setValue(trainerResponse);

                }
            }

            @Override
            public void onFailure(Call<TrainerResponse> call, Throwable t) {
             trainerMutableLiveData.setValue(null);
            }
        });



        return trainerMutableLiveData;
    }

    LiveData<StudentResponse> authenticateStudent(String mobileNumber) {
        MutableLiveData<StudentResponse> studentMutableLiveData = new MutableLiveData<>();

        networkAPI=NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<StudentResponse> call=networkAPI.loginStudents(mobileNumber);
        call.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                StudentResponse studentsResponse=response.body();
                studentMutableLiveData.setValue(studentsResponse);

            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                studentMutableLiveData.setValue(null);
            }
        });




        return studentMutableLiveData;
    }
}