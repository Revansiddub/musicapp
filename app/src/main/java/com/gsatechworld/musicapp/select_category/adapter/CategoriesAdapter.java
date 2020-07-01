package com.gsatechworld.musicapp.select_category.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutSubCategoryBinding;
import com.gsatechworld.musicapp.modules.select_subcategory.SelectCategoryActivity;
import com.gsatechworld.musicapp.select_category.pojo.Categories;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_NAME;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.USER_TYPE;
import static java.util.Locale.getDefault;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.SubCategoryViewHolder> {
    private Context mCtx;
    private List<CategoriesResponse> categoryList;
    private List<Categories> searchableCategoryList;
    private List<Categories> categoriesList;
    private String userType, pinCode;
    private int pincode_Id;
    public int postion=0;


    public CategoriesAdapter(Context mCtx, List<Categories> categoryList, String userType, String pinCode, int pincode_Id) {
        this.mCtx = mCtx;
        this.categoriesList = categoryList;
        searchableCategoryList = new ArrayList<>(categoryList);
        this.userType = userType;
        this.pinCode = pinCode;
        this.pincode_Id=pincode_Id;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubCategoryBinding binding = inflate(from(mCtx), R.layout.layout_sub_category, parent,
                false);
        return new SubCategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.binding.setSubcategory(categoriesList.get(position));
         holder.binding.layoutCategory.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(mCtx.getApplicationContext(),SelectCategoryActivity.class);
                 postion=holder.getAdapterPosition();
                 Categories category = categoriesList.get(position);
                 intent.putExtra(USER_TYPE,userType);
                 intent.putExtra(CATEGORY_ID, category.getCategoryID());
                 intent.putExtra(CATEGORY_NAME, category.getCategoryName());
                 intent.putExtra(PIN_CODE, pinCode);
                 intent.putExtra(PINCODE_ID,pincode_Id);
                 intent.putExtra("position",String.valueOf(postion));
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 mCtx.getApplicationContext().startActivity(intent);
             }
         });


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public  void filter(String charText) {
        charText = charText.toLowerCase(getDefault());

        categoriesList.clear();

        if (charText.length() == 0)
            categoriesList.addAll(searchableCategoryList);
        else
            for (Categories category : searchableCategoryList)
                if (category.getCategoryName().toLowerCase().contains(charText.toLowerCase()))
                    categoriesList.add(category);

        notifyDataSetChanged();

    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        private final LayoutSubCategoryBinding binding;

        public SubCategoryViewHolder(LayoutSubCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }






    }


}