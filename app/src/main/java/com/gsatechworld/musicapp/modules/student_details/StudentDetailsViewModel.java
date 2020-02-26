package com.gsatechworld.musicapp.modules.student_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerResponse;

public class StudentDetailsViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private StudentDetailsRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public StudentDetailsViewModel() {
        repository = new StudentDetailsRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainerResponse> fetchTrainers(TrainerInfo info) {
        return repository.fetchTrainers(info);
    }
}