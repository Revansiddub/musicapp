package com.gsatechworld.musicapp.select_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;


public class SelectCategoriesViewModel extends ViewModel {
    private CategoriesRepository repository;


    public SelectCategoriesViewModel() {

        repository = new CategoriesRepository();
    }

    public LiveData<CategoriesResponse> fecthCategory(PinCodeInfo pinCode){
        return repository.fetchCategories(pinCode);
    }
}