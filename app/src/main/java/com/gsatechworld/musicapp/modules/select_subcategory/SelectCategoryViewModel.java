package com.gsatechworld.musicapp.modules.select_subcategory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_subcategory.pojo.CategoryResponse;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;

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

    LiveData<CategoryResponse> fetchCategories(PinCodeInfo pinCode) {
        return repository.fetchCategories(pinCode);
    }
}