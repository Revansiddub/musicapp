package com.gsatechworld.musicapp.modules.home.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.payment.adapter.AcceptPayment;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class AcceptPaymentViewModel extends ViewModel {
    public AcceptPaymentRepository paymentRepository;

    public AcceptPaymentViewModel() {
        this.paymentRepository=new AcceptPaymentRepository();
    }

    public LiveData<CommonResponse> acceptPayments(AcceptPayment acceptPayment){
        return paymentRepository.acceptPaymentRequest(acceptPayment);
    }


}
