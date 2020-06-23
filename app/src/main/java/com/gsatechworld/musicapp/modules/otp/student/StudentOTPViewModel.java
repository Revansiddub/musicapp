package com.gsatechworld.musicapp.modules.otp.student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class StudentOTPViewModel extends ViewModel {
    public StudentOTPRepository otpRepository;


    public StudentOTPViewModel() {
        this.otpRepository=new StudentOTPRepository();
    }

    public LiveData<CommonResponse> verifyStudentOTP(StudentOTPRequest otpRequest){
        return otpRepository.studentOTPVerify(otpRequest);
    }
}
