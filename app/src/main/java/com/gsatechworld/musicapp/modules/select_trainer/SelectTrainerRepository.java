package com.gsatechworld.musicapp.modules.select_trainer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.Trainer;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
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
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<TrainerResponse> fetchTrainers(TrainerInfo info) {
        MutableLiveData<TrainerResponse> pinCodeMutableLiveData = new MutableLiveData<>();

        List<Trainer> trainerList = new ArrayList<>();
        trainerList.add(new Trainer("1", "Mohan Pal", new ArrayList<>(),
                "", "", new ArrayList<>(), new ArrayList<>()));

        trainerList.add(new Trainer("1", "Yash Sharma", new ArrayList<>(),
                "", "", new ArrayList<>(), new ArrayList<>()));

        trainerList.add(new Trainer("1", "Ritik Joshi", new ArrayList<>(),
                "", "", new ArrayList<>(), new ArrayList<>()));

        trainerList.add(new Trainer("1", "Mayank Pandey", new ArrayList<>(),
                "", "", new ArrayList<>(), new ArrayList<>()));

        trainerList.add(new Trainer("1", "Anindya Paul", new ArrayList<>(),
                "", "", new ArrayList<>(), new ArrayList<>()));

        trainerList.add(new Trainer("1", "Rohit Kumar", new ArrayList<>(),
                "", "", new ArrayList<>(), new ArrayList<>()));

        pinCodeMutableLiveData.postValue(new TrainerResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.", trainerList));

        return pinCodeMutableLiveData;
    }
}