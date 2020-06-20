package com.gsatechworld.musicapp.modules.home.earnings.pending_payments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PaymentActionInfo;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PendingPaymentsResp;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class PendingPaymentViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private PendingPaymentRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PendingPaymentViewModel() {
        repository = new PendingPaymentRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<PendingPaymentsResp> fetchPendingPaymentList(String trainerID) {
        return repository.fetchPendingPaymentList(trainerID);
    }

    LiveData<CommonResponse> storeAction(PaymentActionInfo actionInfo) {
        return repository.storeAction(actionInfo);
    }
}