package com.gsatechworld.musicapp.modules.student_home.student_payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentRequest;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class PaymentRequestViewModel extends ViewModel {
    public PaymentRequestRepository repository;

    public PaymentRequestViewModel() {
        repository=new PaymentRequestRepository();
    }

    public LiveData<CommonResponse> sendPaymentRequest(StudentPaymentRequest paymentRequest){
        return repository.sendPaymentRequest(paymentRequest);
    }
}
