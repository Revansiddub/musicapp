package com.gsatechworld.musicapp.modules.select_sub_category.add_sub_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class AddSubCategoryViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private AddSubCategoryRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public AddSubCategoryViewModel() {
        repository = new AddSubCategoryRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> addCategory(String category) {
        return repository.addCategory(category);
    }
}