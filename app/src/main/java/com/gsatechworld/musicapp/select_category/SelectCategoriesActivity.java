package com.gsatechworld.musicapp.select_category;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.core.manager.SessionManager;
import com.gsatechworld.musicapp.databinding.ActivitySelectSubCategoryBinding;
import com.gsatechworld.musicapp.select_category.add_category.AddCategoryFragment;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.adapter.CategoriesAdapter;

import static android.view.View.GONE;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.ADD_CATEGORY_FRAGMENT_TAG;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT;
import static com.gsatechworld.musicapp.utilities.Constants.USER_TYPE;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class SelectCategoriesActivity extends BaseActivity implements SearchView.OnQueryTextListener, View.OnClickListener {
private ActivitySelectSubCategoryBinding binding;
private SelectCategoriesViewModel viewModel;
    private String userType, pinCode;
    private int pinCodeId;
    CategoriesAdapter adapter;
    SessionManager sessionManager;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sub_category);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_select_sub_category);

        sessionManager=SessionManager.getSessionInstance(this);




        viewModel=new ViewModelProvider(this).get(SelectCategoriesViewModel.class);



        if (getIntent().getStringExtra(USER_TYPE) != null) {
            userType = getIntent().getStringExtra(USER_TYPE);
            pinCode = getIntent().getStringExtra(PIN_CODE);
            pinCodeId=getIntent().getIntExtra(PINCODE_ID,0);



            if (userType.equals(STUDENT))
                binding.textCategoryNotFound.setVisibility(GONE);
        }
        binding.layoutBase.toolbar.setTitle(getString(R.string.select_category));
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        fetchCategories();




        binding.searchCategory.setOnQueryTextListener(this);
        binding.textCategoryNotFound.setOnClickListener(this);
    }

    private void fetchCategories() {

        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.fecthCategory(new PinCodeInfo(pinCode)).observe(this, categoryResponse -> {
                hideLoadingIndicator();

                if (categoryResponse.getStatus().equals("success")) {

                    adapter = new CategoriesAdapter(this,  categoryResponse.getCategoriesList(),userType,pinCode,pinCodeId);
                    binding.recyclerCategories.setLayoutManager
                            (new LinearLayoutManager(this, VERTICAL, false));
                    binding.recyclerCategories.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

//                    binding.recyclerCategories.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), binding.recyclerCategories, new RecyclerTouchListener.ClickListener() {
//                        @Override
//                        public void onClick(View view, int position) {
//                            Intent intent = new Intent(SelectCategoriesActivity.this, SelectCategoryActivity.class);
//                            intent.putExtra(USER_TYPE, userType);
//                            intent.putExtra(PIN_CODE, pinCode);
//
//
//                            startActivity(intent);
//                        }
//                    }));
                    binding.recyclerCategories.setAdapter(adapter);



                } else
                    showSnackBar(this, categoryResponse.getStatus());

                binding.textResult.setText(format("%s categories found in '%s' area",
                        categoryResponse.getCategoriesList().size(), pinCode));


            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }
    private void openAddCategoryDialog() {
        Bundle bundle=new Bundle();
        bundle.putString(PIN_CODE,pinCode);
        AddCategoryFragment addSubCategoryFragment = new AddCategoryFragment();
        addSubCategoryFragment.setArguments(bundle);
        addSubCategoryFragment.show(getSupportFragmentManager(), ADD_CATEGORY_FRAGMENT_TAG);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textCategoryNotFound)
            openAddCategoryDialog();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
       if (adapter != null)
            adapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       if (adapter != null)
            adapter.filter(newText);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
