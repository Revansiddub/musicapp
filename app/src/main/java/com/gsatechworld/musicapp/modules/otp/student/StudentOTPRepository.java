package com.gsatechworld.musicapp.modules.otp.student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.otp.student.pojo.StudentOTPResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentOTPRepository {

    public NetworkAPI networkAPI;

    public StudentOTPRepository() {

    }

    public LiveData<StudentOTPResponse> studentOTPVerify(StudentOTPRequest otpRequest){
        MutableLiveData<StudentOTPResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<StudentOTPResponse> responseCall=networkAPI.verfyStudentOTP(otpRequest);
        responseCall.enqueue(new Callback<StudentOTPResponse>() {
            @Override
            public void onResponse(Call<StudentOTPResponse> call, Response<StudentOTPResponse> response) {
                StudentOTPResponse studentOTPResponse=response.body();
                if (studentOTPResponse != null){
                    mutableLiveData.postValue(studentOTPResponse);
                }
            }

            @Override
            public void onFailure(Call<StudentOTPResponse> call, Throwable t) {
             mutableLiveData.postValue(null);
            }
        });
        return mutableLiveData;
    }
}
