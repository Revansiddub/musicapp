package com.gsatechworld.musicapp.modules.student_home.student_payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPayment;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentResponse;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;

public class StudentPaymentRepository {

    private NetworkAPI networkAPI;


    public StudentPaymentRepository() {

    }

    public LiveData<StudentPaymentResponse> getPaymentList(String studentId){
        MutableLiveData<StudentPaymentResponse> mutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<StudentPaymentResponse> responseCall=networkAPI.getStudentPayments(studentId);
        responseCall.enqueue(new Callback<StudentPaymentResponse>() {
            @Override
            public void onResponse(Call<StudentPaymentResponse> call, Response<StudentPaymentResponse> response) {
                StudentPaymentResponse studentPaymentResponse=response.body();
                if (studentPaymentResponse != null){
                    mutableLiveData.setValue(studentPaymentResponse);
                }
            }

            @Override
            public void onFailure(Call<StudentPaymentResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;

    }
}
