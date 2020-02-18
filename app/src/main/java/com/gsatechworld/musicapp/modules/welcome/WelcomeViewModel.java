package com.gsatechworld.musicapp.modules.welcome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class WelcomeViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private WelcomeRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public WelcomeViewModel() {
        repository = new WelcomeRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> checkPinCodeAvailability(PinCodeInfo pinCodeInfo) {
        return repository.checkPinCodeAvailability(pinCodeInfo);
    }
}