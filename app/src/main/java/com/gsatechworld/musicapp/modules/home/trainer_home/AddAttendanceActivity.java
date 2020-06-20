package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityAddAttendanceBinding;
import com.gsatechworld.musicapp.modules.login.LoginViewModel;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class AddAttendanceActivity extends BaseActivity {
    public String name,phone,timing,age;
    ActivityAddAttendanceBinding binding;
    AttendanceViewModel attendanceViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_attendance);

        attendanceViewModel = new ViewModelProvider(this).get(AttendanceViewModel.class);
        name=getIntent().getStringExtra("name");
        age=getIntent().getStringExtra("age");
        phone=getIntent().getStringExtra("mobile");
     //   timing=getIntent().getStringExtra("timing");

        binding.textviewName.setText(name);
        binding.textviewAge.setText(age);
        binding.textviewMobile.setText(phone);
      //  binding.textviewTiming.setText(timing);



        binding.buttonSubmit.setOnClickListener(v -> {
            addStudenceAttendance();
        });




    }

    public void addStudenceAttendance(){
        String trainer_id="1";
        String enrollment_id="1";
        String date="20-6-2020";
        String start_time="20:00";
        String end_time="21:00";

        if (getNetworkInstance(this).isConnectedToInternet()) {
            attendanceViewModel.addAttendance(new AttendanceRequest(trainer_id, enrollment_id, date, start_time, end_time)).observe(this, commonResponse -> {
              if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                openSuccessDialog(commonResponse.getMessage());
                startActivity(new Intent(AddAttendanceActivity.this,AttendanceActivity.class));
              }

            });

        }

    }
}
