package com.gsatechworld.musicapp.modules.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;

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
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainerResponse> authenticateTrainer(TrainerLoginInfo info) {
        MutableLiveData<TrainerResponse> trainerMutableLiveData = new MutableLiveData<>();

        trainerMutableLiveData.postValue(new TrainerResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", ""));

        return trainerMutableLiveData;
    }

    LiveData<StudentResponse> authenticateStudent(String mobileNumber) {
        MutableLiveData<StudentResponse> studentMutableLiveData = new MutableLiveData<>();

        studentMutableLiveData.postValue(new StudentResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", ""));

        return studentMutableLiveData;
    }
}