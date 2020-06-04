package com.gsatechworld.musicapp.modules.home.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityStudentsDetailsBinding;

public class StudentsDetails extends AppCompatActivity {
    public String name,gender,timing,age;
    ActivityStudentsDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_details);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_students_details);
        name=getIntent().getStringExtra("name");
        age=getIntent().getStringExtra("age");
        gender=getIntent().getStringExtra("gender");
        timing=getIntent().getStringExtra("timing");

//        binding.textviewName.setText(name);
//        binding.textviewAge.setText(age);
//        binding.textviewGender.setText(gender);
//        binding.textviewTiming.setText(timing);



    }
}
