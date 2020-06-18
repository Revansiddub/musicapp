package com.gsatechworld.musicapp.modules.student_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityEntrollmentDetailsBinding;

public class EntrollmentDetailsActivity extends AppCompatActivity {
    public String entrollment_name;
    ActivityEntrollmentDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrollment_details);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_entrollment_details);

        entrollment_name=getIntent().getStringExtra("entroll_name");
        binding.textEntrollment.setText(entrollment_name);

    }
}
