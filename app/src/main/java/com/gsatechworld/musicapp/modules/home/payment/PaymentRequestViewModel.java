package com.gsatechworld.musicapp.modules.home.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.payment.pojo.PaymentRequestResponse;

public class PaymentRequestViewModel extends ViewModel {

    PaymentRepository paymentRepository;


    public PaymentRequestViewModel() {
        this.paymentRepository=new PaymentRepository();
    }

    public LiveData<PaymentRequestResponse> getPaymentRequests(String trainer_ID){
        return paymentRepository.getPaymentRequest(trainer_ID);
    }
}
