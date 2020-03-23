package com.gsatechworld.musicapp.modules.select_sub_category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutSubCategoryBinding;
import com.gsatechworld.musicapp.modules.details.DetailsActivity;
import com.gsatechworld.musicapp.modules.select_sub_category.pojo.SubCategory;
import com.gsatechworld.musicapp.modules.select_trainer.SelectTrainerActivity;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static java.util.Locale.getDefault;

public class SubCategoryAdapter extends Adapter<SubCategoryAdapter.CategoryHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<SubCategory> subCategoryList;
    private List<SubCategory> searchableSubCategoryList;
    private String userType, pinCode;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SubCategoryAdapter(Context mCtx, List<SubCategory> subCategoryList, String userType, String pinCode) {
        this.mCtx = mCtx;
        this.subCategoryList = subCategoryList;
        searchableSubCategoryList = new ArrayList<>(subCategoryList);
        this.userType = userType;
        this.pinCode = pinCode;
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubCategoryBinding binding = inflate(from(mCtx), R.layout.layout_sub_category, parent,
                false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.binding.setSubCategory(subCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    /* ------------------------------------------------------------- *
     * Public Method
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to search item in the list based on the character or string.
     *
     * @param charText char sequence that need to be searched in the list.
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());

        subCategoryList.clear();

        if (charText.length() == 0)
            subCategoryList.addAll(searchableSubCategoryList);
        else
            for (SubCategory subCategory : searchableSubCategoryList)
                if (subCategory.getCategoryName().toLowerCase().contains(charText.toLowerCase()))
                    subCategoryList.add(subCategory);

        notifyDataSetChanged();
    }

    /* ------------------------------------------------------------- *
     * SubCategory Holder Class
     * ------------------------------------------------------------- */

    class CategoryHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutSubCategoryBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        CategoryHolder(final LayoutSubCategoryBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting Listeners to the view*/
            binding.layoutCategory.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClickListener Method
         * ------------------------------------------------------------- */

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.layoutCategory) {
                SubCategory subCategory = subCategoryList.get(getAdapterPosition());

                Intent intent = new Intent(mCtx, (userType.equals(TRAINER) ? DetailsActivity.class :
                        SelectTrainerActivity.class));
                intent.putExtra(CATEGORY_ID, subCategory.getCategoryID());
                intent.putExtra(PIN_CODE, pinCode);
                mCtx.startActivity(intent);
            }
        }
    }
}