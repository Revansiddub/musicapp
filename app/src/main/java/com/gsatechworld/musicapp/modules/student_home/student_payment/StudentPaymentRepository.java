package com.gsatechworld.musicapp.modules.student_home.student_payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPayment;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentResponse;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;

public class StudentPaymentRepository {

    private NetworkAPI networkAPI;


    public StudentPaymentRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    public LiveData<StudentPaymentResponse> getPaymentList(String studentId){
        MutableLiveData<StudentPaymentResponse> mutableLiveData = new MutableLiveData<>();

        List<StudentPayment> paymentArrayList = new ArrayList<>();
        paymentArrayList.add(new StudentPayment("Guitar","2000"));
        paymentArrayList.add(new StudentPayment("Violinn","4000"));

        mutableLiveData.postValue(new StudentPaymentResponse(Constants.SERVER_RESPONSE_SUCCESS,"Oops! something went wrong. Please try again later.",paymentArrayList));

        return mutableLiveData;

    }
}
