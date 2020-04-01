package com.gsatechworld.musicapp.modules.home.trainer_home.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

public class TimeSlotRepository  {


    public TimeSlotRepository() {
    }

   public LiveData<TimeSlotResponse> fetchTimeSlots() {
        MutableLiveData<TimeSlotResponse> timeSlotsMutableLiveData = new MutableLiveData<>();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("09:00", "10:00"));
        timeSlots.add(new TimeSlot("10:00", "11:00"));
        timeSlots.add(new TimeSlot("11:00", "12:00"));
        timeSlots.add(new TimeSlot("12:00", "13:00"));
        timeSlots.add(new TimeSlot("13:00", "14:00"));
        timeSlots.add(new TimeSlot("14:00", "15:00"));

       timeSlotsMutableLiveData.postValue(new TimeSlotResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", timeSlots));

        return timeSlotsMutableLiveData;
    }
}
