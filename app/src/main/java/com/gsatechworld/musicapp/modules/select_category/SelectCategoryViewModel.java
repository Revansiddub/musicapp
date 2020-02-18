package com.gsatechworld.musicapp.modules.select_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_category.pojo.CategoryResponse;

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

    LiveData<CategoryResponse> fetchCategories(String pinCode) {
        return repository.fetchCategories(pinCode);
    }
}