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

public class EntrollmentsRepository {
    private NetworkAPI networkAPI;



    public EntrollmentsRepository() {
        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
    }

    public LiveData<EntrollmentResponse> fetchEntrollments(String student_Id){
        MutableLiveData<EntrollmentResponse> responseMutableLiveData=new MutableLiveData<>();

        List<Entrollments> entrollmentsList = new ArrayList<>();
        entrollmentsList.add(new Entrollments("Guitar"));
        entrollmentsList.add(new Entrollments("Violin"));
        entrollmentsList.add(new Entrollments("Country Music"));
        entrollmentsList.add(new Entrollments("Electronic Dance Music"));



        responseMutableLiveData.postValue(new EntrollmentResponse(Constants.SERVER_RESPONSE_SUCCESS,"Oops! something went wrong. Please try again later.",entrollmentsList));

        return responseMutableLiveData;

    }

}
