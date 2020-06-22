package com.gsatechworld.musicapp.modules.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.student_home.pojo.EntrollmentResponse;
import com.gsatechworld.musicapp.modules.student_home.pojo.Entrollments;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPayment;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentResponse;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntrollmentsRepository {
    private NetworkAPI networkAPI;



    public EntrollmentsRepository() {
        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
    }

    public LiveData<EntrollmentResponse> fetchEntrollments(String student_Id){
        MutableLiveData<EntrollmentResponse> responseMutableLiveData=new MutableLiveData<>();

        networkAPI=NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<EntrollmentResponse> responseCall=networkAPI.fetchEntrollments(student_Id);

        responseCall.enqueue(new Callback<EntrollmentResponse>() {
            @Override
            public void onResponse(Call<EntrollmentResponse> call, Response<EntrollmentResponse> response) {
                EntrollmentResponse entrollmentResponse=response.body();
                if (entrollmentResponse != null){
                    responseMutableLiveData.setValue(entrollmentResponse);
                }
            }

            @Override
            public void onFailure(Call<EntrollmentResponse> call, Throwable t) {
                responseMutableLiveData.setValue(null);
            }
        });


        return responseMutableLiveData;

    }

}
