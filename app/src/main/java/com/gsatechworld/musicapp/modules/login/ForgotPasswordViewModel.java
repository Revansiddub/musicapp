package com.gsatechworld.musicapp.modules.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class ForgotPasswordViewModel extends ViewModel {

    public ForgotPasswordRepository passwordRepository;
    public ForgotPasswordViewModel() {
        this.passwordRepository=new ForgotPasswordRepository();
    }

    public LiveData<CommonResponse> forgot_password(String email){
        return passwordRepository.forgotPassword(email);
    }
}
