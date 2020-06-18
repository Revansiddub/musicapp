package com.gsatechworld.musicapp.modules.home.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.payment.adapter.AcceptPayment;
import com.gsatechworld.musicapp.modules.home.payment.pojo.Payment_requests;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceptPaymentRepository {
    public NetworkAPI networkAPI;



    public AcceptPaymentRepository() {

    }

    public LiveData<CommonResponse> acceptPaymentRequest(AcceptPayment acceptPayment){
        MutableLiveData<CommonResponse> mutableLiveData=new MutableLiveData<>();
        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<CommonResponse> responseCall=networkAPI.acceptPamentRequest(acceptPayment);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse != null){
                    mutableLiveData.setValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
            mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }


}
