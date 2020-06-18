package com.gsatechworld.musicapp.modules.otp.pojo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.otp.TrainerOtpVerification;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class TrainerOTPViewModel extends ViewModel {

    public TrainerOTPRepository trainerOTPRepository;

    public TrainerOTPViewModel() {
        this.trainerOTPRepository = new TrainerOTPRepository();
    }

   public LiveData<CommonResponse> verifyTrainerOTP(TrainerOTPVerification otpVerification){
        return trainerOTPRepository.verifyTrainerOTP(otpVerification);
    }
}
