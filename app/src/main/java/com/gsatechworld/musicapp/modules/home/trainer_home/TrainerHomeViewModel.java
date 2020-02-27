package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_details.pojo.StudentDetailsInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class TrainerHomeViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private TrainerHomeRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerHomeViewModel() {
        repository = new TrainerHomeRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> onBoardStudent(StudentDetailsInfo info) {
        return repository.onBoardStudent(info);
    }
}