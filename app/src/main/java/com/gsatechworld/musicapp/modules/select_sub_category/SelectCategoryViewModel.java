package com.gsatechworld.musicapp.modules.select_sub_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_sub_category.pojo.SubCategoryResponse;

public class SelectCategoryViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private SelectCategoryRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SelectCategoryViewModel() {
        repository = new SelectCategoryRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<SubCategoryResponse> fetchCategories(String pinCode) {
        return repository.fetchCategories(pinCode);
    }
}