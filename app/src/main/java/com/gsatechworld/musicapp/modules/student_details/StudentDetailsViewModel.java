package com.gsatechworld.musicapp.modules.student_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_details.pojo.StudentDetailsInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

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

    LiveData<CommonResponse> onBoardStudent(StudentDetailsInfo info) {
        return repository.onBoardStudent(info);
    }
}