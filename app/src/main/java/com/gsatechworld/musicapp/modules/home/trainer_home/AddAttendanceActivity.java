package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityAddAttendanceBinding;

public class AddAttendanceActivity extends AppCompatActivity {
    public String name,phone,timing,age;
    ActivityAddAttendanceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_attendance);
        name=getIntent().getStringExtra("name");
        age=getIntent().getStringExtra("age");
        phone=getIntent().getStringExtra("mobile");
        timing=getIntent().getStringExtra("timing");

        binding.textviewName.setText(name);
        binding.textviewAge.setText(age);
        binding.textviewMobile.setText(phone);
        binding.textviewTiming.setText(timing);




    }
}
