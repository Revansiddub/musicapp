package com.gsatechworld.musicapp.modules.home.earnings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class EarningsViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private EarningsRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public EarningsViewModel() {
        repository = new EarningsRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    public LiveData<EarningResponse> getEarningDetailsList(String trainerID) {
        return repository.getEarningDetailsList(trainerID);
    }
}