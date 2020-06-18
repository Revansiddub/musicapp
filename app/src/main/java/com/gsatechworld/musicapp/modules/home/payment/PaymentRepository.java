package com.gsatechworld.musicapp.modules.home.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.payment.pojo.PaymentRequestResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentRepository {

    public NetworkAPI networkAPI;


    public PaymentRepository() {
    }

    public LiveData<PaymentRequestResponse> getPaymentRequest(String trainer_Id){
        MutableLiveData<PaymentRequestResponse> responseMutableLiveData=new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<PaymentRequestResponse> responseCall=networkAPI.getPaymenrRequest(trainer_Id);
        responseCall.enqueue(new Callback<PaymentRequestResponse>() {
            @Override
            public void onResponse(Call<PaymentRequestResponse> call, Response<PaymentRequestResponse> response) {
                PaymentRequestResponse requestResponse=response.body();
                if (requestResponse != null){
                    responseMutableLiveData.setValue(requestResponse);
                }
            }

            @Override
            public void onFailure(Call<PaymentRequestResponse> call, Throwable t) {
             responseMutableLiveData.setValue(null);
            }
        });

        return responseMutableLiveData;
    }


}
