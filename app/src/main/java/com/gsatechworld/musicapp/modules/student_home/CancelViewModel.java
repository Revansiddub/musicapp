package com.gsatechworld.musicapp.modules.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class CancelViewModel extends ViewModel {

    public CancelRepository cancelRepository;


    public CancelViewModel() {
        this.cancelRepository=new CancelRepository();
    }

    public LiveData<CommonResponse> cancelClass(String enrollment_id,String cancellation){
     return cancelRepository.cancelClass(enrollment_id,cancellation);
    }
}
