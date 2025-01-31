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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.GetStudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.AttendanceViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.GetStudentsViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.TimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.SelectTimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;
import com.gsatechworld.musicapp.utilities.Constants;
import com.gsatechworld.musicapp.utilities.NetworkUtilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static com.gsatechworld.musicapp.utilities.Constants.TrainerId;
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
    public GetStudentsViewModel studentsViewModel;
    public int trainerId;
    public TimesAdapter timesAdapter;
    public int position;
    public String date ;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_attendance);


        date = getIntent().getStringExtra("date");

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
        studentsViewModel=new ViewModelProvider(this).get(GetStudentsViewModel.class);

        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        userType = sharedpreferences.getString(Constants.TrainerType, null);
        trainerID = String.valueOf(sharedpreferences.getInt(Constants.TrainerId, 0));
        getTimeLots();
        getStudents();

        binding.buttonSubmit.setOnClickListener(v -> {
            openSuccessDialog("Attendance Added Succesfully");
            String userType = "Daily";
            Intent intent = new Intent(AttendanceActivity.this, HomeActivity.class);
            intent.putExtra("type", userType);
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
            recyclerView_studnts.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
            StudentsAttendanceAdapter studentsAttendanceAdapter = new StudentsAttendanceAdapter(null,AttendanceActivity.this);
            recyclerView_studnts.setAdapter(studentsAttendanceAdapter);
            studentsViewModel.getStudents(trainerID , getDate(date)).observe(this, new Observer<GetStudentsResponse>() {
                @Override
                public void onChanged(GetStudentsResponse response) {
                    if (response.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
//                        recyclerView_studnts.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
//                        recyclerView_studnts.setAdapter(new StudentsAttendanceAdapter(response.getResult().getDates()
//                        .get(position).getTime_slots().get(position).getStudent_list(),AttendanceActivity.this));
                    }
                }
            });

        }
    }

    private String getDate(String date) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date tempDate = null;
        try {
            tempDate = simpledateformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Output date is = "+outputDateFormat.format(tempDate));

        return "fdgdf";
    }

    private void getTimeLots() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {
            trainerID="1";
            timeSlotViewModel.fetchTimeSlots(trainerID).observe(this, availableTimeSlotResponse -> {
                if (availableTimeSlotResponse.getStatus().equals("success")){
                    timesAdapter=new TimesAdapter(this,availableTimeSlotResponse.getAvailable_slots());
                    binding.recyclerTimeSlots.setLayoutManager(new GridLayoutManager(this,2));
                    binding.recyclerTimeSlots.setAdapter(timesAdapter);
                }
            });
        }

    }

}


