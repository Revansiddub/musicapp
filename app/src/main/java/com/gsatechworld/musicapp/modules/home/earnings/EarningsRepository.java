package com.gsatechworld.musicapp.modules.home.earnings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.EarningResponse;

import java.util.ArrayList;
import java.util.List;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

class EarningsRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    EarningsRepository() {
        networkAPI = getRetrofitInstance().create(NetworkAPI.class);
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<EarningResponse> getEarningDetailsList(String trainerID) {
        MutableLiveData<EarningResponse> earningMutableLiveData = new MutableLiveData<>();

        List<Earning> earningList = new ArrayList<>();
        earningList.add(new Earning("2000", "20 Feb, 2020", "Arjun"));
        earningList.add(new Earning("5000", "22 Feb, 2020", "Rakesh"));
        earningList.add(new Earning("5500", "24 Feb, 2020", "Vijay"));
        earningList.add(new Earning("9000", "25 Feb, 2020", "Preeti"));
        earningList.add(new Earning("1000", "27 Feb, 2020", "Varun"));
        earningList.add(new Earning("2560", "28 Feb, 2020", "Raja"));
        earningList.add(new Earning("2000", "20 Feb, 2020", "Arjun"));
        earningList.add(new Earning("5000", "22 Feb, 2020", "Rakesh"));
        earningList.add(new Earning("5500", "24 Feb, 2020", "Vijay"));
        earningList.add(new Earning("9000", "25 Feb, 2020", "Preeti"));
        earningList.add(new Earning("1000", "27 Feb, 2020", "Varun"));
        earningList.add(new Earning("2560", "28 Feb, 2020", "Raja"));

        earningMutableLiveData.postValue(new EarningResponse(SERVER_RESPONSE_SUCCESS,
                "Oops! something went wrong. Please try again later.",
                earningList));

        return earningMutableLiveData;
    }
}