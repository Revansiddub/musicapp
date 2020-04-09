package com.gsatechworld.musicapp.select_category.add_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class AddCategoryViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private AddCategoryRepository repository;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public AddCategoryViewModel() {
        repository = new AddCategoryRepository();
    }

    /* ------------------------------------------------------------- *
     * Default Methods
     * ------------------------------------------------------------- */

    LiveData<CommonResponse> addCategory(AddCategory category) {
        return repository.addCategory(category);
    }
}