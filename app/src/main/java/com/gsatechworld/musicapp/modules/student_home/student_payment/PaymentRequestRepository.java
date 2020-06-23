package com.gsatechworld.musicapp.modules.student_home.student_payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentRequest;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentRequestRepository {
    public NetworkAPI networkAPI;

    public PaymentRequestRepository() {

    }

    public LiveData<CommonResponse> sendPaymentRequest(StudentPaymentRequest paymentRequest){
        MutableLiveData<CommonResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<CommonResponse> responseCall=networkAPI.studentsPaymentRequests(paymentRequest);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse != null){
                    mutableLiveData.postValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
             mutableLiveData.postValue(null);
            }
        });

        return mutableLiveData;

    }
}
