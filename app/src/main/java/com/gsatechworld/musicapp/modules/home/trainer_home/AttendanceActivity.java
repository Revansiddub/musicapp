package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityAttendanceBinding;
import com.gsatechworld.musicapp.modules.home.HomeActivity;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.StudentsAttendanceAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.TimesAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.AvailableTimeSlotResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.AttendanceViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.TimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.SelectTimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;
import com.gsatechworld.musicapp.utilities.NetworkUtilities;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static java.util.Objects.requireNonNull;

public class AttendanceActivity extends BaseActivity {
    ActivityAttendanceBinding binding;
    RecyclerView recyclerView_slot,recyclerView_studnts;
    TimeSlotAdapter adapter;
    public String trainerID;

    TimeSlotViewModel viewModel;
    AttendanceViewModel attendanceViewModel;
    SelectTimeSlotViewModel timeSlotViewModel;
    StudentsAttendanceAdapter attendanceAdapter;
    public int trainerId;
    public TimesAdapter timesAdapter;
    public int position;
    public String date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_attendance);


        date=getIntent().getStringExtra("date");

        binding.textDate.setText(date);



        binding.layoutBase.toolbar.setTitle(getString(R.string.students_details));
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        recyclerView_slot=binding.recyclerTimeSlots;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView_slot.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView_slot.addItemDecoration(dividerItemDecoration);
        recyclerView_slot.setHasFixedSize(true);
        recyclerView_studnts=binding.recyclerStudents;
        recyclerView_studnts.setHasFixedSize(true);

        viewModel=new ViewModelProvider(this).get(TimeSlotViewModel.class);
        timeSlotViewModel=new ViewModelProvider(this).get(SelectTimeSlotViewModel.class);
        attendanceViewModel=new ViewModelProvider(this).get(AttendanceViewModel.class);

        trainerId=getIntent().getIntExtra("trainerID",0);
        trainerID=String.valueOf(trainerId);
        getTimeLots();
        getStudents();

        binding.buttonSubmit.setOnClickListener(v -> {
            openSuccessDialog("Attendance Added Succesfully");
            String userType = "Daily";
            Intent intent=new Intent(AttendanceActivity.this, HomeActivity.class);
            intent.putExtra("type",userType);
            startActivity(intent);
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
//            alertDialog.setTitle("Add Attendance");
//            alertDialog.setMessage("Are you sure want to Submit");
//            alertDialog.setPositiveButton("YES",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            startActivity(new Intent(AttendanceActivity.this, HomeActivity.class));
//                        }
//                    });
//            alertDialog.setNegativeButton("NO",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//            alertDialog.show();
        });


    }

    public void showSnackBar(Activity context, String message) {
        Snackbar snackbar = make(context.findViewById(android.R.id.content), message, LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }

    private void getStudents() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            attendanceViewModel.getStudentsDetails().observe(this, new Observer<StudentsResponse>() {
                @Override
                public void onChanged(StudentsResponse response) {
                    recyclerView_studnts.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
                    recyclerView_studnts.setAdapter(new StudentsAttendanceAdapter(response.getAttendanceList(),AttendanceActivity.this));

                }
            });

        }
    }

    private void getTimeLots() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {

         timeSlotViewModel.fetchTimeSlots(trainerID).observe(this, avilableResponse ->{
             if (avilableResponse.getStatus().equals("success")){
               //  timesAdapter=new TimesAdapter(this,avilableResponse.getAvailable_slots().get(position));
                 binding.recyclerTimeSlots.setLayoutManager(new GridLayoutManager(this,2));
                 binding.recyclerTimeSlots.setAdapter(timesAdapter);
             }
         });
        }

        }

}


