package com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.home.trainer_home.repository.TimeSlotRepository;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;

public class TimeSlotViewModel extends ViewModel {

    TimeSlotRepository repository;


    public TimeSlotViewModel() {
        this.repository = new TimeSlotRepository();
    }

    public LiveData<TimeSlotResponse> slotResponseLiveData() {
        return repository.fetchTimeSlots();
    }
}
