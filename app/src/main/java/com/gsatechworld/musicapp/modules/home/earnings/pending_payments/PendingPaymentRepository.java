package com.gsatechworld.musicapp.modules.home.earnings.pending_payments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.Payment;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PaymentActionInfo;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PaymentResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class PendingPaymentRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    PendingPaymentRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<PaymentResponse> fetchPendingPaymentList(String trainerID) {
        MutableLiveData<PaymentResponse> paymentMutableLiveData = new MutableLiveData<>();

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment("", "Arjun", "20 Feb, 2020", "2000"));
        paymentList.add(new Payment("", "Rakesh", "22 Feb, 2020", "5500"));
        paymentList.add(new Payment("", "Vijay", "24 Feb, 2020", "7250"));

        paymentMutableLiveData.postValue(new PaymentResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.",
                paymentList));

        return paymentMutableLiveData;
    }

    LiveData<CommonResponse> storeAction(PaymentActionInfo actionInfo) {
        MutableLiveData<CommonResponse> actionMutableLiveData = new MutableLiveData<>();


        actionMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                "Request Accepted"));

        return actionMutableLiveData;
    }
}