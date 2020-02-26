package com.gsatechworld.musicapp.modules.select_trainer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerResponse;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class SelectTrainerViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private SelectTrainerRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SelectTrainerViewModel() {
        repository = new SelectTrainerRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainerResponse> fetchTrainers(TrainerInfo info) {
        return repository.fetchTrainers(info);
    }
}