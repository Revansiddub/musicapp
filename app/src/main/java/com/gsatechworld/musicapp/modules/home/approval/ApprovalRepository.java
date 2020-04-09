package com.gsatechworld.musicapp.modules.home.approval;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ActionInfo;
import com.gsatechworld.musicapp.modules.home.approval.pojo.Approval;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApprovalResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.FEMALE;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class ApprovalRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    ApprovalRepository() {
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<ApprovalResponse> fetchApprovalList(int trainerID) {
        MutableLiveData<ApprovalResponse> approvalMutableLiveData = new MutableLiveData<>();


        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<ApprovalResponse> responseCall=networkAPI.approvalRequest(trainerID);

        responseCall.enqueue(new Callback<ApprovalResponse>() {
            @Override
            public void onResponse(Call<ApprovalResponse> call, Response<ApprovalResponse> response) {
                ApprovalResponse approvalResponse=response.body();
                if (approvalResponse != null){
                    approvalMutableLiveData.setValue(approvalResponse);
                }
            }

            @Override
            public void onFailure(Call<ApprovalResponse> call, Throwable t) {
             approvalMutableLiveData.setValue(null);
            }
        });
        return approvalMutableLiveData;
    }

    LiveData<CommonResponse> storeAction(ActionInfo actionInfo) {
        MutableLiveData<CommonResponse> actionMutableLiveData = new MutableLiveData<>();




        actionMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                "Request Accepted"));

        return actionMutableLiveData;
    }
}