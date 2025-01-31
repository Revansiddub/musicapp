package com.gsatechworld.musicapp.modules.home.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.student_details.pojo.StudentDetailsInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class SettingsViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private SettingsRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SettingsViewModel() {
        repository = new SettingsRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> onBoardStudent(StudentDetailsInfo info) {
        return repository.onBoardStudent(info);
    }
}