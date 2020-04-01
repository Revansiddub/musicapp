package com.gsatechworld.musicapp.modules.home.approval;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ActionInfo;
import com.gsatechworld.musicapp.modules.home.approval.pojo.Approval;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApprovalResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import java.util.ArrayList;
import java.util.List;

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
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<ApprovalResponse> fetchApprovalList(String trainerID) {
        MutableLiveData<ApprovalResponse> approvalMutableLiveData = new MutableLiveData<>();

        List<Approval> approvalList = new ArrayList<>();
        approvalList.add(new Approval("1", "Rajesh Kumar", MALE, "17",
                "09:00 AM", "10:00 AM"));
        approvalList.add(new Approval("2", "Yash Panday", MALE, "20",
                "13:00 AM", "14:00 AM"));
        approvalList.add(new Approval("3", "Priya Chauhan", FEMALE, "15",
                "19:00 AM", "20:00 AM"));
        approvalList.add(new Approval("1", "Rajesh Kumar", MALE, "17",
                "09:00 AM", "10:00 AM"));
        approvalList.add(new Approval("2", "Yash Panday", MALE, "20",
                "13:00 AM", "14:00 AM"));
        approvalList.add(new Approval("3", "Priya Chauhan", FEMALE, "15",
                "19:00 AM", "20:00 AM"));
        approvalList.add(new Approval("1", "Rajesh Kumar", MALE, "17",
                "09:00 AM", "10:00 AM"));
        approvalList.add(new Approval("2", "Yash Panday", MALE, "20",
                "13:00 AM", "14:00 AM"));
        approvalList.add(new Approval("3", "Priya Chauhan", FEMALE, "15",
                "19:00 AM", "20:00 AM"));


        approvalMutableLiveData.postValue(new ApprovalResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", approvalList));

        return approvalMutableLiveData;
    }

    LiveData<CommonResponse> storeAction(ActionInfo actionInfo) {
        MutableLiveData<CommonResponse> actionMutableLiveData = new MutableLiveData<>();




        actionMutableLiveData.postValue(new CommonResponse(SERVER_RESPONSE_SUCCESS,
                "Request Accepted"));

        return actionMutableLiveData;
    }
}