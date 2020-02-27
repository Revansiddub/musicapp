package com.gsatechworld.musicapp.modules.home.approval;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.approval.pojo.ActionInfo;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ApprovalResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class ApprovalViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ApprovalRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public ApprovalViewModel() {
        repository = new ApprovalRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<ApprovalResponse> fetchApprovalList(String trainerID) {
        return repository.fetchApprovalList(trainerID);
    }

    LiveData<CommonResponse> storeAction(ActionInfo actionInfo) {
        return repository.storeAction(actionInfo);
    }
}