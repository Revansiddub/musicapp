package com.gsatechworld.musicapp.modules.select_time_slot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.AvailableTimeSlotResponse;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.AvailableTimesSlotResponse;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;
import com.gsatechworld.musicapp.utilities.CommonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<AvailableTimeSlotResponse> fetchTimeSlots(String trainerID) {
        MutableLiveData<AvailableTimeSlotResponse> timeSlotsMutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);

        Call<AvailableTimeSlotResponse> responseCall=networkAPI.getTimeSlots(trainerID);
        responseCall.enqueue(new Callback<AvailableTimeSlotResponse>() {
            @Override
            public void onResponse(Call<AvailableTimeSlotResponse> call, Response<AvailableTimeSlotResponse> response) {
                AvailableTimeSlotResponse timeSlotResponse=response.body();
                if (timeSlotResponse != null){
                    timeSlotsMutableLiveData.setValue(timeSlotResponse);
                }

            }

            @Override
            public void onFailure(Call<AvailableTimeSlotResponse> call, Throwable t) {
             timeSlotsMutableLiveData.setValue(null);
            }
        });

        return timeSlotsMutableLiveData;
    }

   public LiveData<TimeSlotResponse> availableTimeSlots(String trainerID) {
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

    public LiveData<AvailableTimesSlotResponse> fetchstudentTimeslots(String trainer_id){
        MutableLiveData<AvailableTimesSlotResponse> mutableLiveData=new MutableLiveData<>();

        networkAPI=NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<AvailableTimesSlotResponse> responseCall=networkAPI.fecthTimeslots(trainer_id);
        responseCall.enqueue(new Callback<AvailableTimesSlotResponse>() {
            @Override
            public void onResponse(Call<AvailableTimesSlotResponse> call, Response<AvailableTimesSlotResponse> response) {
                AvailableTimesSlotResponse timesSlotResponse=response.body();
                if (timesSlotResponse != null){
                    mutableLiveData.setValue(timesSlotResponse);
                }
            }

            @Override
            public void onFailure(Call<AvailableTimesSlotResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;
    }


}