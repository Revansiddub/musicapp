package com.gsatechworld.musicapp.select_sub_category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.modules.select_category.pojo.CategoryResponse;
import com.gsatechworld.musicapp.select_sub_category.pojo.SubCategoryResponse;


public class SelectSubCategoryViewModel extends ViewModel {
    private SubCategoryRepository repository;


    public SelectSubCategoryViewModel() {

        repository = new SubCategoryRepository();
    }

    public LiveData<SubCategoryResponse> fecthCategory(String pinCode){
        return repository.fetchCategories(pinCode);
    }
}