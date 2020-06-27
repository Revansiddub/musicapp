package com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.DateResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.repository.DateRepository;

public class DateViewModel extends ViewModel {

    public DateRepository repository;

    public DateViewModel() {
        repository=new DateRepository();
    }

    public LiveData<DateResponse> getDates(String trainer_id,String month,String year){
        return repository.getDates(trainer_id,month,year);
    }
}
