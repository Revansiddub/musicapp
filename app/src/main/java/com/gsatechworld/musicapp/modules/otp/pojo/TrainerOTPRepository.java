package com.gsatechworld.musicapp.modules.otp.pojo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

public class TrainerOTPRepository {
    private NetworkAPI networkAPI;


    public TrainerOTPRepository() {

    }

    LiveData<CommonResponse> verifyTrainerOTP(TrainerOTPVerification otpVerification){
        MutableLiveData<CommonResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<CommonResponse> responseCall=networkAPI.verifyTrainer(otpVerification);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                mutableLiveData.postValue(commonResponse);
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
            mutableLiveData.postValue(null);
            }
        });
        return mutableLiveData;
    }

    LiveData<StudentResponse> authenticateStudent(String mobileNumber) {
        MutableLiveData<StudentResponse> studentMutableLiveData = new MutableLiveData<>();

        studentMutableLiveData.postValue(new StudentResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", ""));

        return studentMutableLiveData;
    }
}
