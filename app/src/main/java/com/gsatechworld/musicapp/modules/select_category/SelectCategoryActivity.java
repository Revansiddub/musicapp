package com.gsatechworld.musicapp.modules.select_category;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivitySelectCategoryBinding;
import com.gsatechworld.musicapp.modules.select_category.adapter.CategoryAdapter;
import com.gsatechworld.musicapp.modules.select_category.pojo.Category;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static com.gsatechworld.musicapp.utilities.Constants.USER_TYPE;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SelectCategoryActivity extends BaseActivity implements OnQueryTextListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivitySelectCategoryBinding binding;
    private CategoryAdapter categoryAdapter;
    private String userType, pinCode;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_category);

        if (getIntent().getStringExtra(USER_TYPE) != null) {
            userType = getIntent().getStringExtra(USER_TYPE);
            pinCode = getIntent().getStringExtra(PIN_CODE);
        }

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle(requireNonNull(userType).equals(TRAINER) ?
                getString(R.string.select_category) : getString(R.string.select_trainer));
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initialiseRecyclerView();

        /*Setting listeners to the view*/
        binding.searchCategory.setOnQueryTextListener(this);
    }

    /* ------------------------------------------------------------- *
     * Overriding onOptionsItemSelected Method
     * ------------------------------------------------------------- */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* ------------------------------------------------------------- *
     * Overriding OnQueryTextListener Methods
     * ------------------------------------------------------------- */

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (categoryAdapter != null)
            categoryAdapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (categoryAdapter != null)
            categoryAdapter.filter(newText);
        return true;
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to initialise and set an adapter to RecyclerView.
     */
    private void initialiseRecyclerView() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("1", "Indie Rock"));
        categoryList.add(new Category("2", "Acoustic Blues"));
        categoryList.add(new Category("3", "Piano"));
        categoryList.add(new Category("4", "Contemporary Classical"));
        categoryList.add(new Category("5", "Trance"));
        categoryList.add(new Category("6", "Underground Rap"));
        categoryList.add(new Category("7", "Musical Soundtracks"));

        categoryAdapter = new CategoryAdapter(this, categoryList);

        binding.recyclerCategories.setLayoutManager(new LinearLayoutManager(this, VERTICAL,
                false));
        binding.recyclerCategories.setAdapter(categoryAdapter);

        if (userType.equals(TRAINER))
            binding.textResult.setText(format("%s categories found in '%s' area",
                    categoryList.size(), pinCode));
        else
            binding.textResult.setText(format("%s trainers found in '%s' area",
                    categoryList.size(), pinCode));
    }
}