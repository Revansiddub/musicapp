package com.gsatechworld.musicapp.modules.select_subcategory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsatechworld.musicapp.core.network.NetworkAPI;
import com.gsatechworld.musicapp.core.network.NetworkService;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.CategoryResponse;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.SubCategory;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gsatechworld.musicapp.core.network.NetworkService.getRetrofitInstance;

public class SelectCategoryRepository {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private NetworkAPI networkAPI;
    ArrayList<CategoryResponse> categoryResponses;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    SelectCategoryRepository() {

    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

   public LiveData<CategoryResponse> fetchCategories(PinCodeInfo pinCode) {
        MutableLiveData<CategoryResponse> subcategoryMutableLiveData = new MutableLiveData<>();

       networkAPI= NetworkService.getRetrofitInstance().create(NetworkAPI.class);
       Call<CategoryResponse> responseCall=networkAPI.getSubCategories(pinCode);

        responseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                CategoryResponse categoriesResponse= response.body();
                if (categoriesResponse != null){
                    subcategoryMutableLiveData.setValue(categoriesResponse);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });



        return subcategoryMutableLiveData;
    }
}