package com.gsatechworld.musicapp.modules.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerResponse;

public class LoginViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private LoginRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public LoginViewModel() {
        repository = new LoginRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainerResponse> authenticateTrainer(TrainerLoginInfo info) {
        return repository.authenticateTrainer(info);
    }

  public LiveData<StudentResponse> authenticateStudent(String mobileNumber) {
        return repository.authenticateStudent(mobileNumber);
    }
}