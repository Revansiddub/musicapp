package com.gsatechworld.musicapp.modules.select_subcategory.add_subcategory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gsatechworld.musicapp.utilities.CommonResponse;

public class AddSubCategoryViewModel extends ViewModel {
    public AddSubCategoryRepository subCategoryRepository;

    public AddSubCategoryViewModel() {
        subCategoryRepository=new AddSubCategoryRepository();
    }

    public LiveData<CommonResponse> addSubCategories(AddSubCategory subCategory){
        return subCategoryRepository.addSubCategory(subCategory);
    }




}
