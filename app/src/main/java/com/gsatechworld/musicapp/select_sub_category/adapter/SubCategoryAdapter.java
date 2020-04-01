package com.gsatechworld.musicapp.select_sub_category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutCategoryBinding;
import com.gsatechworld.musicapp.databinding.LayoutSubCategoryBinding;
import com.gsatechworld.musicapp.modules.details.DetailsActivity;
import com.gsatechworld.musicapp.modules.select_category.pojo.Category;
import com.gsatechworld.musicapp.modules.select_trainer.SelectTrainerActivity;
import com.gsatechworld.musicapp.select_sub_category.pojo.SubCategory;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static java.util.Locale.getDefault;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    private Context mCtx;
    private List<SubCategory> categoryList;
    private List<SubCategory> searchableCategoryList;
    private String userType, pinCode;

    public SubCategoryAdapter(Context mCtx, List<SubCategory> categoryList, String userType, String pinCode) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
        this.userType = userType;
        this.pinCode = pinCode;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubCategoryBinding binding=inflate(from(mCtx), R.layout.layout_sub_category, parent,
                false);
        return new SubCategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.binding.setSubcategory(categoryList.get(position));

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final LayoutSubCategoryBinding binding;

        public SubCategoryViewHolder(LayoutSubCategoryBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.layoutCategory.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.layoutCategory) {
                SubCategory category = categoryList.get(getAdapterPosition());

                Intent intent = new Intent(mCtx, (userType.equals(TRAINER) ? DetailsActivity.class :
                        SelectTrainerActivity.class));
                intent.putExtra(CATEGORY_ID, category.getCategoryID());
                intent.putExtra(PIN_CODE, pinCode);
                mCtx.startActivity(intent);
            }
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());

        categoryList.clear();

        if (charText.length() == 0)
            categoryList.addAll(searchableCategoryList);
        else
            for (SubCategory category : searchableCategoryList)
                if (category.getCategoryName().toLowerCase().contains(charText.toLowerCase()))
                    categoryList.add(category);

        notifyDataSetChanged();

    }
}
