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
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.StudentTimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter.OnTimeSlotSelectedListener;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;
import com.gsatechworld.musicapp.modules.student_details.StudentDetailsActivity;

import static com.gsatechworld.musicapp.utilities.Constants.END_TIME;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.START_TIME;
import static com.gsatechworld.musicapp.utilities.Constants.TIME_SLOT;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class SelectTimeSlotActivity extends BaseActivity implements OnClickListener,
        OnTimeSlotSelectedListener, StudentTimeSlotAdapter.OnTimeSelectedListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivitySelectTimeSlotBinding binding;
    private SelectTimeSlotViewModel viewModel;
    private String trainerID;
    private TimeSlot selectedTimeSlot;
    RecyclerView recyclerView;
    public int position;
    public StudentTimeSlotAdapter slotAdapter;
    public String start_time,end_time;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_time_slot);

        trainerID = String.valueOf(getIntent().getIntExtra(TRAINER_ID,0));

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




        fetchTimeSlots();

        /*Setting listeners to the view*/
//        binding.buttonNext.setOnClickListener(v -> {
//            Intent intent = new Intent(this, StudentDetailsActivity.class);
//            intent.putExtra(TRAINER_ID, trainerID);
//            intent.putExtra(START_TIME,start_time);
//            intent.putExtra(END_TIME,end_time);
//            startActivity(intent);
//        });
         binding.buttonNext.setOnClickListener(this);

    }


    @Override
    public void onTimeAction(String startTime, String endTime) {
        start_time= startTime;
        end_time= endTime;
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
            if (start_time != null && end_time !=null) {
                Intent intent = new Intent(this, StudentDetailsActivity.class);
                intent.putExtra(TRAINER_ID, trainerID);
               // intent.putExtra(TIME_SLOT, selectedTimeSlot);
                intent.putExtra(START_TIME,start_time);
                intent.putExtra(END_TIME,end_time);
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

            viewModel.fetchStudentsTimeslot(trainerID).observe(this, availableTimesSlotResponse -> {
                hideLoadingIndicator();

                if (availableTimesSlotResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                    binding.recyclerTimeSlots.setLayoutManager(new GridLayoutManager(this,
                            2));
                    slotAdapter=new StudentTimeSlotAdapter(this,availableTimesSlotResponse.getAvailable_slots());
                    slotAdapter.setClickListner(this);
                   binding.recyclerTimeSlots.setAdapter(slotAdapter);

                } else
                    showSnackBar(this, availableTimesSlotResponse.getMessage());
            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }


}