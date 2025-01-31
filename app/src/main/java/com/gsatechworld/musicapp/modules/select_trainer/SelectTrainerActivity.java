package com.gsatechworld.musicapp.modules.select_trainer;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivitySelectTrainerBinding;
import com.gsatechworld.musicapp.modules.select_trainer.adapter.TrainerAdapter;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainerInfo;
import com.gsatechworld.musicapp.utilities.Constants;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SUBCATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class SelectTrainerActivity extends BaseActivity implements OnQueryTextListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivitySelectTrainerBinding binding;
    private SelectTrainerViewModel viewModel;
    private TrainerAdapter adapter;
    private String pinCode, subcategoryID,categoryID,pincodeID;
    RecyclerView recyclerView;
    private Bitmap profileImageBitmap;


    /* ------------------------------------------------------------- *
     * Overriding Base Activity
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_trainer);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        pincodeID=String.valueOf(sharedPreferences.getInt(PINCODE_ID,0));

        recyclerView=binding.recyclerTrainer;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(SelectTrainerViewModel.class);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Select Trainer");
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (getIntent().getStringExtra(PIN_CODE) != null) {
            pinCode = getIntent().getStringExtra(PIN_CODE);
            subcategoryID = getIntent().getStringExtra(SUBCATEGORY_ID);
            categoryID=getIntent().getStringExtra(CATEGORY_ID);

        }

        fetchTrainerList();

        /*Setting listeners to the views*/
        binding.searchTrainer.setOnQueryTextListener(this);
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

    /**
     * This method is invoked to fetch a list of list trainer for particular category in  that area.
     */
    private void fetchTrainerList() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();



            viewModel.fetchTrainers(new TrainerInfo(pincodeID, subcategoryID,categoryID)).observe(this,
                    trainerResponse -> {
                        hideLoadingIndicator();

                        if (trainerResponse.getResponse().equals("success")){
                            binding.recyclerTrainer.setLayoutManager(new
                                    LinearLayoutManager(this, VERTICAL, false));
                            adapter = new TrainerAdapter(this, trainerResponse.getTrainerList());
                            binding.recyclerTrainer.setAdapter(adapter);
                        }

                        if (adapter.getItemCount() == 0){
                            showSnackBar(this,"No Trainer Available in your Area");
                        }





                    });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }
}