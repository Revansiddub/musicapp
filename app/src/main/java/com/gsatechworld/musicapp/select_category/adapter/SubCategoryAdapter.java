package com.gsatechworld.musicapp.select_category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutSubCategoryBinding;
import com.gsatechworld.musicapp.modules.details.DetailsActivity;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.select_subcategory.SelectCategoryActivity;
import com.gsatechworld.musicapp.modules.select_trainer.SelectTrainerActivity;
import com.gsatechworld.musicapp.select_category.pojo.Categories;
import com.gsatechworld.musicapp.select_category.pojo.CategoriesResponse;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_NAME;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static com.gsatechworld.musicapp.utilities.Constants.USER_TYPE;
import static java.util.Locale.getDefault;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    private Context mCtx;
    private List<CategoriesResponse> categoryList;
    private List<Categories> categoriesList;
    private String userType, pinCode;
    public int postion=0;


    public SubCategoryAdapter(Context mCtx, List<Categories> categoryList, String userType, String pinCode) {
        this.mCtx = mCtx;
        this.categoriesList = categoryList;
        this.userType = userType;
        this.pinCode = pinCode;
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

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        private final LayoutSubCategoryBinding binding;

        public SubCategoryViewHolder(LayoutSubCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }





//    public void filter(String charText) {
//        charText = charText.toLowerCase(getDefault());
//
//        subCategoryList.clear();
//
//        if (charText.length() == 0)
//            subCategoryList.addAll(searchableCategoryList);
//        else
//            for (Categories category : searchableCategoryList)
//                if (category.getCategoryName().toLowerCase().contains(charText.toLowerCase()))
//                    subCategoryList.add(category);
//
//        notifyDataSetChanged();
//
//    }
    }
}