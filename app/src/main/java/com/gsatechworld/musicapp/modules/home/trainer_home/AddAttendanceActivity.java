package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityAddAttendanceBinding;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.Attendance;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.StudentsAttendanceAdapter;
import com.gsatechworld.musicapp.modules.login.LoginViewModel;
import com.gsatechworld.musicapp.utilities.Constants;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.gsatechworld.musicapp.utilities.Constants.ABSENT;
import static com.gsatechworld.musicapp.utilities.Constants.ENROLLMENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PRESENT;
import static com.gsatechworld.musicapp.utilities.Constants.SELECTED_DATE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TrainerId;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class AddAttendanceActivity extends BaseActivity {
    public String name,phone,timing,age,startTime,endTime,enrollment_id,student_id;
    ActivityAddAttendanceBinding binding;
    AttendanceViewModel attendanceViewModel;
    public String selected_date,student_image;
    private String trainerID;
    public int status;
    public onStatusListener listener;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_attendance);

        attendanceViewModel = new ViewModelProvider(this).get(AttendanceViewModel.class);
        name=getIntent().getStringExtra("name");
        age=getIntent().getStringExtra("age");
        phone=getIntent().getStringExtra("mobile");
        startTime=getIntent().getStringExtra("startTime");
        endTime=getIntent().getStringExtra("endTime");
        selected_date = getIntent().getStringExtra(SELECTED_DATE);

        Intent intent=getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)){
            student_image=getIntent().getExtras().getString(Intent.EXTRA_TEXT);
            Glide.with(this).load(student_image).into(binding.imageIcon);

        }

        enrollment_id=String.valueOf(getIntent().getIntExtra(ENROLLMENT_ID,0));
        student_id=getIntent().getStringExtra(STUDENT_ID);
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        trainerID=String.valueOf(sharedPreferences.getInt(TrainerId, 0));


        binding.textviewName.setText(name);
        binding.textviewAge.setText(age);
        binding.textviewMobile.setText(phone);
        binding.textviewStart.setText(startTime);
        binding.endTime.setText(endTime);
      //  binding.textviewTiming.setText(timing);

        status=1;



        binding.buttonSubmit.setOnClickListener(v -> {
            addStudenceAttendance();
        });

        binding.textPresent.setOnClickListener(v -> {
            binding.textPresent.setTextColor(getResources().getColor(R.color.white));
            binding.textPresent.setCompoundDrawableTintList
                    (getResources().getColorStateList(R.color.white));
            binding.textPresent.setBackground(getDrawable(R.drawable.edit_text_rounded_corner));
            binding.textPresent.setBackgroundTintList(getColorStateList(R.color.colorAccent));


            binding.textAbsent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.textAbsent.setCompoundDrawableTintList
                    (getResources().getColorStateList(R.color.colorPrimaryDark));
            binding.textAbsent.setBackground(null);


            status = 1;
        });

        binding.textAbsent.setOnClickListener(v -> {
            binding.textAbsent.setTextColor(getResources().getColor(R.color.white));
            binding.textAbsent.setCompoundDrawableTintList
                    (getResources().getColorStateList(R.color.white));
            binding.textAbsent.setBackground(getDrawable(R.drawable.edit_text_rounded_corner));
            binding.textAbsent.setBackgroundTintList(getColorStateList(R.color.colorAccent));


            binding.textPresent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.textPresent.setCompoundDrawableTintList
                    (getResources().getColorStateList(R.color.colorPrimaryDark));
            binding.textPresent.setBackground(null);

            status = 0;
        });

    }

    public void addStudenceAttendance(){

        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            attendanceViewModel.addAttendance(new AttendanceRequest(student_id, enrollment_id, selected_date,
                    startTime, endTime,status)).observe(this, commonResponse -> {
                hideLoadingIndicator();
              if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                openSuccessDialog(commonResponse.getMessage());
                startActivity(new Intent(AddAttendanceActivity.this,AttendanceActivity.class));
                finish();
              }

            });

        }

    }

    public interface onStatusListener{
        void onStatusPerformed(String status);
    }

    public void setActionListener(onStatusListener listener){
        this.listener=listener;
    }
}
