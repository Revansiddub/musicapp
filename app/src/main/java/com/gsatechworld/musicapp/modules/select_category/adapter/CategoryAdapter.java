package com.gsatechworld.musicapp.modules.select_category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutCategoryBinding;
import com.gsatechworld.musicapp.modules.details.DetailsActivity;
import com.gsatechworld.musicapp.modules.select_category.pojo.Category;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static java.util.Locale.getDefault;

public class CategoryAdapter extends Adapter<CategoryAdapter.CategoryHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<Category> categoryList;
    private List<Category> searchableCategoryList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CategoryAdapter(Context mCtx, List<Category> categoryList) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
        searchableCategoryList = new ArrayList<>(categoryList);
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCategoryBinding binding = inflate(from(mCtx), R.layout.layout_category, parent,
                false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.binding.setCategory(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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

        categoryList.clear();

        if (charText.length() == 0)
            categoryList.addAll(searchableCategoryList);
        else
            for (Category category : searchableCategoryList)
                if (category.getCategoryName().toLowerCase().contains(charText.toLowerCase()))
                    categoryList.add(category);

        notifyDataSetChanged();
    }

    /* ------------------------------------------------------------- *
     * Category Holder Class
     * ------------------------------------------------------------- */

    class CategoryHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutCategoryBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        CategoryHolder(final LayoutCategoryBinding binding) {
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
                Intent intent = new Intent(mCtx, DetailsActivity.class);
                mCtx.startActivity(intent);
            }
        }
    }
}