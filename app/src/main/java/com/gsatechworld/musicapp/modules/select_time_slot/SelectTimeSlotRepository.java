package com.gsatechworld.musicapp.modules.select_time_slot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class SelectTimeSlotRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    SelectTimeSlotRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TimeSlotResponse> fetchTimeSlots(String trainerID) {
        MutableLiveData<TimeSlotResponse> timeSlotsMutableLiveData = new MutableLiveData<>();

        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("09:00", "10:00"));
        timeSlots.add(new TimeSlot("10:00", "11:00"));
        timeSlots.add(new TimeSlot("11:00", "12:00"));
        timeSlots.add(new TimeSlot("12:00", "13:00"));
        timeSlots.add(new TimeSlot("13:00", "14:00"));
        timeSlots.add(new TimeSlot("14:00", "15:00"));
        timeSlots.add(new TimeSlot("15:00", "16:00"));
        timeSlots.add(new TimeSlot("16:00", "17:00"));
        timeSlots.add(new TimeSlot("17:00", "18:00"));

        timeSlotsMutableLiveData.postValue(new TimeSlotResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", timeSlots));

        return timeSlotsMutableLiveData;
    }
}