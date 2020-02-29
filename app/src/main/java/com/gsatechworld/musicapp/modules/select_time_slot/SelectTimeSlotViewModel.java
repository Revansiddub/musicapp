package com.gsatechworld.musicapp.modules.select_time_slot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;

public class SelectTimeSlotViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private SelectTimeSlotRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SelectTimeSlotViewModel() {
        repository = new SelectTimeSlotRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TimeSlotResponse> fetchTimeSlots(String trainerID) {
        return repository.fetchTimeSlots(trainerID);
    }
}