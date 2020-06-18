package com.gsatechworld.musicapp.modules.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.details.pojo.OnBoadingTrainer;
import com.gsatechworld.musicapp.modules.details.pojo.TrainerDetails;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class DetailsViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private DetailsRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public DetailsViewModel() {
        repository = new DetailsRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> submitTrainerDetails(OnBoadingTrainer details) {
        return repository.submitTrainerDetails(details);
    }
}