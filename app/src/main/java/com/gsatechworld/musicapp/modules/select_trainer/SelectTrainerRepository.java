package com.gsatechworld.musicapp.modules.select_trainer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.Trainer;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class SelectTrainerRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    SelectTrainerRepository() {

    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainersResponse> fetchTrainers(TrainerInfo info) {
        MutableLiveData<TrainersResponse> pinCodeMutableLiveData = new MutableLiveData<>();

        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<TrainersResponse> call=networkAPI.fetchTrainers(info);
        call.enqueue(new Callback<TrainersResponse>() {
            @Override
            public void onResponse(Call<TrainersResponse> call, Response<TrainersResponse> response) {
                TrainersResponse trainersResponse=response.body();
                if (trainersResponse != null){
                    pinCodeMutableLiveData.setValue(trainersResponse);
                }
            }

            @Override
            public void onFailure(Call<TrainersResponse> call, Throwable t) {
                pinCodeMutableLiveData.setValue(null);

            }
        });


//        List<String> coachingTypeList = new ArrayList<>();
//        coachingTypeList.add("Home");
//        coachingTypeList.add("Institute");
//
//        List<String> recurrenceTypeList = new ArrayList<>();
//        recurrenceTypeList.add("Daily");
//        recurrenceTypeList.add("Weekly");
//        recurrenceTypeList.add("Biweekly");
//
//        List<String> daysList = new ArrayList<>();
//        daysList.add("Sunday");
//        daysList.add("Monday");
//        daysList.add("Tuesday");
//        daysList.add("Wednesday");
//        daysList.add("Thursday");
//        daysList.add("Friday");
//        daysList.add("Saturday");
//
//        List<Trainer> trainerList = new ArrayList<>();
//        trainerList.add(new Trainer("1", "Mohan Pal", coachingTypeList,
//                "567", "BTM 1st stage", recurrenceTypeList, daysList));
//
//        trainerList.add(new Trainer("2", "Yash Sharma", coachingTypeList,
//                "982", "BTM 2nd stage", recurrenceTypeList, daysList));
//
//        trainerList.add(new Trainer("3", "Ritik Joshi", coachingTypeList,
//                "542", "HSR Layout", recurrenceTypeList, daysList));
//
//        trainerList.add(new Trainer("4", "Mayank Pandey", coachingTypeList,
//                "8956", "Marathalli", recurrenceTypeList, daysList));
//
//        trainerList.add(new Trainer("5", "Anindya Paul", coachingTypeList,
//                "213", "Banshankari", recurrenceTypeList, daysList));
//
//        trainerList.add(new Trainer("6", "Rohit Kumar", coachingTypeList,
//                "752", "Jayanagar", recurrenceTypeList, daysList));
//
//        pinCodeMutableLiveData.postValue(new TrainersResponse(SERVER_RESPONSE_SUCCESS,
//                "Oops! something went wrong. Please try again later.", trainerList));

        return pinCodeMutableLiveData;
    }
}