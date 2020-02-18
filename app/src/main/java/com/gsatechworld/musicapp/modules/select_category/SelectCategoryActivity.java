package com.gsatechworld.musicapp.modules.select_category;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivitySelectCategoryBinding;
import com.gsatechworld.musicapp.modules.select_category.adapter.CategoryAdapter;
import com.gsatechworld.musicapp.modules.select_category.add_category.AddCategoryFragment;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.ADD_CATEGORY_FRAGMENT_TAG;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.USER_TYPE;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SelectCategoryActivity extends BaseActivity implements OnQueryTextListener,
        OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivitySelectCategoryBinding binding;
    private SelectCategoryViewModel viewModel;
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

        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(SelectCategoryViewModel.class);

        if (getIntent().getStringExtra(USER_TYPE) != null) {
            userType = getIntent().getStringExtra(USER_TYPE);
            pinCode = getIntent().getStringExtra(PIN_CODE);
        }

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle(getString(R.string.select_category));
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        fetchCategories();

        /*Setting listeners to the views*/
        binding.searchCategory.setOnQueryTextListener(this);
        binding.textCategoryNotFound.setOnClickListener(this);
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
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textCategoryNotFound)
            openAddCategoryDialog();
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to fetch a list of all categories available in selected pin code
     */
    private void fetchCategories() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.fetchCategories(pinCode).observe(this, categoryResponse -> {
                hideLoadingIndicator();

                if (categoryResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)) {

                    categoryAdapter = new CategoryAdapter(this,
                            categoryResponse.getCategoryList(), userType);
                    binding.recyclerCategories.setLayoutManager
                            (new LinearLayoutManager(this, VERTICAL, false));
                    binding.recyclerCategories.setAdapter(categoryAdapter);
                } else
                    showSnackBar(this, categoryResponse.getMessage());

                binding.textResult.setText(format("%s categories found in '%s' area",
                        categoryResponse.getCategoryList().size(), pinCode));
            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }

    /**
     * This method is invoked to open a view where user can request admin to add category.
     */
    private void openAddCategoryDialog() {
        AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
        addCategoryFragment.show(getSupportFragmentManager(), ADD_CATEGORY_FRAGMENT_TAG);
    }
}