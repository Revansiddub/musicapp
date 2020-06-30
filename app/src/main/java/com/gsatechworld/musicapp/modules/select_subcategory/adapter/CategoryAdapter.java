package com.gsatechworld.musicapp.modules.select_subcategory.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutCategoryBinding;
import com.gsatechworld.musicapp.modules.details.DetailsActivity;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.select_subcategory.SelectCategoryActivity;
import com.gsatechworld.musicapp.modules.select_subcategory.pojo.SubCategory;
import com.gsatechworld.musicapp.modules.select_trainer.SelectTrainerActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SUBCATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static java.util.Locale.getDefault;

public class CategoryAdapter extends Adapter<CategoryAdapter.CategoryHolder>  {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private ArrayList<SubCategory> subCategoryList;
    private List<SubCategory> searchableSubCategoryList;
    private String userType, pinCode,categoryID;
    private int pincode_Id;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public CategoryAdapter(Context mCtx, ArrayList<SubCategory> subCategoryList, String userType, String pinCode,String categoryID,int pincode_Id) {
        this.mCtx = mCtx;
        this.subCategoryList = subCategoryList;
        searchableSubCategoryList = new ArrayList<>(subCategoryList);
        this.userType = userType;
        this.pinCode = pinCode;
        this.categoryID=categoryID;
        this.pincode_Id=pincode_Id;
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
                SubCategory category = subCategoryList.get(getAdapterPosition());

                Intent intent = new Intent(mCtx, (userType.equals(TRAINER) ? DetailsActivity.class :
                        SelectTrainerActivity.class));
                intent.putExtra(CATEGORY_ID,categoryID);
                intent.putExtra(SUBCATEGORY_ID, category.getSubcategoryID());
                intent.putExtra(PIN_CODE, pinCode);
                intent.putExtra(PINCODE_ID,pincode_Id);
                SharedPreferences sharedPreferences=mCtx.getSharedPreferences(Constants.MyPREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(CATEGORY_ID,categoryID);
                editor.putString(SUBCATEGORY_ID,category.getSubcategoryID());
                editor.putInt(PINCODE_ID,pincode_Id);
                editor.commit();
                mCtx.startActivity(intent);
                ((Activity)mCtx).finish();



            }
        }
    }
}