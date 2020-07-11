package com.gsatechworld.musicapp.modules.select_time_slot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivitySelectTimeSlotBinding;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.StudentTimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter.OnTimeSlotSelectedListener;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;
import com.gsatechworld.musicapp.modules.student_details.StudentDetailsActivity;
import com.gsatechworld.musicapp.modules.student_details.pojo.OnboardingRequest;
import com.gsatechworld.musicapp.modules.student_home.AddEnrollmentViewModel;
import com.gsatechworld.musicapp.modules.student_home.EntrollmentsViewModel;
import com.gsatechworld.musicapp.modules.student_home.StudentHomeActivity;
import com.gsatechworld.musicapp.modules.student_home.pojo.AddEntrollmentRequest;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;

import static com.gsatechworld.musicapp.utilities.Constants.ALREADY_REGISTERED;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.END_TIME;
import static com.gsatechworld.musicapp.utilities.Constants.IsStudentLogin;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_FAILED;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.START_TIME;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.SUBCATEGORY_ID;
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
    public boolean isLogedIn;
    public String category_ID,sub_categoryID,student_ID;
    public ArrayList<AddEntrollmentRequest.Time_slot_Details> timeSlotes;
    public AddEnrollmentViewModel enrollmentViewModel;


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

        enrollmentViewModel=new ViewModelProvider(this).get(AddEnrollmentViewModel.class);

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
                SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
                //sharedPreferences.getBoolean(ALREADY_REGISTERED,false);
                isLogedIn=sharedPreferences.getBoolean(IsStudentLogin,false);
                if (isLogedIn == true){
                    timeSlotes=new ArrayList<>();
                    timeSlotes.add(new AddEntrollmentRequest.Time_slot_Details(start_time,end_time));
                    student_ID=sharedPreferences.getString(STUDENT_ID,null);
                    category_ID=sharedPreferences.getString(CATEGORY_ID,null);
                    sub_categoryID=sharedPreferences.getString(SUBCATEGORY_ID,null);
                    addEntrollment();
                }else {
                    Intent intent = new Intent(this, StudentDetailsActivity.class);
                    intent.putExtra(TRAINER_ID, trainerID);
                    // intent.putExtra(TIME_SLOT, selectedTimeSlot);
                    intent.putExtra(START_TIME,start_time);
                    intent.putExtra(END_TIME,end_time);
                    startActivity(intent);
                }

            } else
                showErrorSnackBar(this, "Please select time slot first");
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
                    binding.recyclerTimeSlots.setLayoutManager(new LinearLayoutManager(this));
                    slotAdapter=new StudentTimeSlotAdapter(this,availableTimesSlotResponse.getAvailable_slots());
                    slotAdapter.setClickListner(this);
                   binding.recyclerTimeSlots.setAdapter(slotAdapter);

                } else
                    showErrorSnackBar(this, availableTimesSlotResponse.getMessage());
            });
        } else
            showErrorSnackBar(this, getString(R.string.no_internet_message));
    }

    public void checkAlreadyRegistered(){
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        //sharedPreferences.getBoolean(ALREADY_REGISTERED,false);
        isLogedIn=sharedPreferences.getBoolean(ALREADY_REGISTERED,false);
        if (isLogedIn == true){
            startActivity(new Intent(this, StudentHomeActivity.class));
        }
    }

    //Add new Enrollment for student
    public void addEntrollment(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            enrollmentViewModel.addNewEnrollment(new AddEntrollmentRequest(student_ID,trainerID,category_ID,sub_categoryID,timeSlotes)).observe(this,commonResponse -> {
              hideLoadingIndicator();
              if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                  openSuccessDialog(commonResponse.getMessage());
                  startActivity(new Intent(this, StudentHomeActivity.class));
              }
              if (commonResponse.getStatus().equals(SERVER_RESPONSE_FAILED)){
                  showSnackBar(this,commonResponse.getMessage());
              }

            });
        }

    }


}