package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityAttendanceBinding;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.StudentsAttendanceAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.TimesAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.CancelClass;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.AttendanceViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.CancelClassViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.GetStudentsViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.TimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.SelectTimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.student_home.CancelViewModel;
import com.gsatechworld.musicapp.utilities.Constants;
import com.gsatechworld.musicapp.utilities.NetworkUtilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class AttendanceActivity extends BaseActivity implements TimesAdapter.cancelClassListener, TimesAdapter.onRecyclerItemListener {
    ActivityAttendanceBinding binding;
    RecyclerView recyclerView_slot, recyclerView_studnts;
    TimeSlotAdapter adapter;
    public String trainerID;

    TimeSlotViewModel viewModel;
    AttendanceViewModel attendanceViewModel;
    SelectTimeSlotViewModel timeSlotViewModel;
    public StudentsAttendanceAdapter attendanceAdapter;
    public GetStudentsViewModel studentsViewModel;
    public TimesAdapter timesAdapter;
    public int position;
    public String selected_date, string_date;
    private String userType;
    public CancelViewModel cancelViewModel;
    public String star_time,end_time;
    public String startTime,endTime;
    public Date date;
    public Date date1,date2;
    public String formattedDate;
    public CancelClassViewModel classViewModel;
    public SimpleDateFormat simpleDateFormat;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendance);

        cancelViewModel=new ViewModelProvider(this).get(CancelViewModel.class);
        classViewModel=new ViewModelProvider(this).get(CancelClassViewModel.class);

        selected_date = getIntent().getStringExtra("date");
        string_date=selected_date;



        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            date = new Date(simpleDateFormat.parse(string_date).getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate=sdf.format(date);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        binding.textDate.setText(formattedDate);
        //LocalDate localDate=LocalDate.parse(selected_date);

        binding.layoutBase.toolbar.setTitle(getString(R.string.students_details));
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        recyclerView_slot = binding.recyclerTimeSlots;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView_slot.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView_slot.addItemDecoration(dividerItemDecoration);
        recyclerView_slot.setHasFixedSize(true);
        recyclerView_studnts = binding.recyclerStudents;
        recyclerView_studnts.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(TimeSlotViewModel.class);
        timeSlotViewModel = new ViewModelProvider(this).get(SelectTimeSlotViewModel.class);
        attendanceViewModel = new ViewModelProvider(this).get(AttendanceViewModel.class);
        studentsViewModel = new ViewModelProvider(this).get(GetStudentsViewModel.class);

        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        userType = sharedpreferences.getString(Constants.TrainerType, null);
        trainerID = String.valueOf(sharedpreferences.getInt(Constants.TrainerId, 0));
        getTimeLots();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudents();
    }


    public void showSnackBar(Activity context, String message) {
        Snackbar snackbar = make(context.findViewById(android.R.id.content), message, LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }

    @Override
    public void onRecyclerClick(int position) {
        this.position=position;
    }


    private void getStudents() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            studentsViewModel.getStudents(trainerID, selected_date).observe(this, fetchStudentsResponse -> {
                hideLoadingIndicator();
                if (fetchStudentsResponse != null && fetchStudentsResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                    recyclerView_studnts.setLayoutManager(new LinearLayoutManager(this));
                    star_time = fetchStudentsResponse.getResult().getTime_slots().get(position).getStart_time();
                    end_time = fetchStudentsResponse.getResult().getTime_slots().get(position).getEnd_time();
                    attendanceAdapter = new StudentsAttendanceAdapter(fetchStudentsResponse.getResult().getTime_slots().get(position)
                            .getStudent_list(), this, star_time, end_time, selected_date,startTime,endTime, this);

                    recyclerView_studnts.setAdapter(attendanceAdapter);
                    try {
                        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        date1 = simpleDateFormat.parse(star_time);
                        DateFormat outputformat = new SimpleDateFormat("hh:mm a");
                        startTime=outputformat.format(date1);
                        date2=simpleDateFormat.parse(end_time);
                        endTime=outputformat.format(date2);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }




                } else {
                    showSnackBar(this, "You have no class scheduled in this date");
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
        System.out.println("Output date is = " + outputDateFormat.format(tempDate));

        return "fdgdf";
    }

    private void getTimeLots() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {
            timeSlotViewModel.fetchTimeSlots(trainerID).observe(this, availableTimeSlotResponse -> {
                if (availableTimeSlotResponse.getStatus().equals("success")) {
                    timesAdapter = new TimesAdapter(this, availableTimeSlotResponse.getAvailable_slots(),startTime,endTime);
                    binding.recyclerTimeSlots.setLayoutManager(new GridLayoutManager(this, 2));
                    timesAdapter.setActionListener(this);
                    binding.recyclerTimeSlots.setAdapter(timesAdapter);
                }
            });
        }

    }

//    @Override
//    public void onActionCancel(String enrollment_id, String date,String star_time,String end_time) {
//        if (getNetworkInstance(this).isConnectedToInternet()) {
//            showLoadingIndicator();
//
//            cancelViewModel.cancelClass(enrollment_id,selected_date,star_time,end_time).observe(this,commonResponse -> {
//                if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
//                   openSuccessDialog("Successfully canceled this class");
//                }
//            });
//
//
//        }
//    }

    @Override
    public void onClassCancel(String startTime, String endTime) {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            classViewModel.cancel_Class(new CancelClass(trainerID,selected_date,startTime,endTime)).observe(this,commonResponse -> {
                hideLoadingIndicator();

                if (commonResponse != null && commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    openSuccessDialog(commonResponse.getMessage());
                    getStudents();
                }

            });
        }
    }


//    public void cancelClass(){
//        cancelViewModel.cancelClass(new CancelClass(trainerID,))
//    }
}


