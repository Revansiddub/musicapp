package com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.CancelClass;
import com.gsatechworld.musicapp.modules.home.trainer_home.repository.CancelClassRepository;
import com.gsatechworld.musicapp.utilities.CommonResponse;

public class CancelClassViewModel extends ViewModel {

    public CancelClassRepository classRepository;


    public CancelClassViewModel() {
        this.classRepository=new CancelClassRepository();
    }

    public LiveData<CommonResponse> cancel_Class(CancelClass cancelClass){
        return classRepository.cancel_Class(cancelClass);
    }
}
