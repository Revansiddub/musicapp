package com.gsatechworld.musicapp.modules.select_time_slot;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivitySelectTimeSlotBinding;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter.OnTimeSlotSelectedListener;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;
import com.gsatechworld.musicapp.modules.student_details.StudentDetailsActivity;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.TIME_SLOT;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class SelectTimeSlotActivity extends BaseActivity implements OnClickListener,
        OnTimeSlotSelectedListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivitySelectTimeSlotBinding binding;
    private SelectTimeSlotViewModel viewModel;
    private String trainerID;
    private TimeSlot selectedTimeSlot;
    RecyclerView recyclerView;
    public int position;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_time_slot);
        recyclerView=binding.recyclerTimeSlots;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);

        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(SelectTimeSlotViewModel.class);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Select Time Slots");
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (getIntent().getStringExtra(TRAINER_ID) != null)
            trainerID = getIntent().getStringExtra(TRAINER_ID);

        fetchTimeSlots();

        /*Setting listeners to the view*/
        binding.buttonNext.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentDetailsActivity.class);
            intent.putExtra(TRAINER_ID, trainerID);
            intent.putExtra(TIME_SLOT, selectedTimeSlot);
            startActivity(intent);
        });


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
     * Overriding OnTimeSlotSelectedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onTimeSlotSelected(TimeSlot timeSlot) {
        selectedTimeSlot = timeSlot;
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonNext) {
            if (selectedTimeSlot != null) {
                Intent intent = new Intent(this, StudentDetailsActivity.class);
                intent.putExtra(TRAINER_ID, trainerID);
                intent.putExtra(TIME_SLOT, selectedTimeSlot);
                startActivity(intent);
            } else
                showSnackBar(this, "Please select time slot first");
        }
    }

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    /**
     * This method is invoked fetch a list of all available time slots of selected trainer
     */
    private void fetchTimeSlots() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.fetchTimeSlots(trainerID).observe(this, availableTimeSlotResponse -> {
                hideLoadingIndicator();

                if (availableTimeSlotResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                    binding.recyclerTimeSlots.setLayoutManager(new GridLayoutManager(this,
                            2));
//                    binding.recyclerTimeSlots.setAdapter(new TimeSlotAdapter(this,
//                            availableTimeSlotResponse.getAvailable_slots().get(position).getSlot_details()));
                } else
                    showSnackBar(this, availableTimeSlotResponse.getMessage());
            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }
}