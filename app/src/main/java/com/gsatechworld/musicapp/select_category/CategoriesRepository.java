package com.gsatechworld.musicapp.select_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.pojo.Categories;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

public class CategoriesRepository {
    private NetworkAPI networkAPI;
    ArrayList<CategoriesResponse> categoryList;

    public CategoriesRepository() {
    }

    LiveData<CategoriesResponse> fetchCategories(PinCodeInfo pinCode) {
        MutableLiveData<CategoriesResponse> categoryMutableLiveData = new MutableLiveData<>();



        networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
        Call<CategoriesResponse> responseCall=networkAPI.getCategories(pinCode);

        responseCall.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
             CategoriesResponse categoriesResponse= response.body();
             if (categoriesResponse != null){
              categoryMutableLiveData.setValue(categoriesResponse);

             }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
             categoryMutableLiveData.setValue(null);
            }
        });




        return categoryMutableLiveData;
    }
}
