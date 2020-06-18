package com.gsatechworld.musicapp.modules.home.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.settings.pojo.ChangePasswordRequest;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class ChangePasswordViewModel extends ViewModel {
    public ChangePasswordRepositotory repositotory;


    public ChangePasswordViewModel() {
        this.repositotory=new ChangePasswordRepositotory();
    }

    public LiveData<CommonResponse> changePassword(ChangePasswordRequest changePasswordRequest){
        return repositotory.changePassword(changePasswordRequest);
    }
}
