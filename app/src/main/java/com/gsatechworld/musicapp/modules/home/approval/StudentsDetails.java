package com.gsatechworld.musicapp.modules.home.approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityStudentsDetailsBinding;
import com.gsatechworld.musicapp.modules.student_home.student_profile.ProfileViewModel;
import com.gsatechworld.musicapp.utilities.Constants;

import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

public class StudentsDetails extends AppCompatActivity {
    public String name,gender,timing,age;
    public String student_id;
    ActivityStudentsDetailsBinding binding;
    public ProfileViewModel profileViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_details);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_students_details);

        profileViewModel=new ViewModelProvider(this).get(ProfileViewModel.class);
        name=getIntent().getStringExtra("name");
        age=getIntent().getStringExtra("age");
        gender=getIntent().getStringExtra("gender");
        timing=getIntent().getStringExtra("timing");
        student_id=getIntent().getStringExtra(Constants.STUDENT_ID);


        fetchStudentProfile();


    }


    public void fetchStudentProfile(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            profileViewModel.fetchStudentProfile(student_id).observe(this,studentProfileResponse -> {
                if (studentProfileResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    binding.setStudentprofile(studentProfileResponse);

                }
            });
        }
    }
}
