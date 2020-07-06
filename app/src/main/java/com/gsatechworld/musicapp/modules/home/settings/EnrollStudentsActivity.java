package com.gsatechworld.musicapp.modules.home.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityEnrollStudentsBinding;
import com.gsatechworld.musicapp.modules.home.settings.adapter.EnrolledStudenceAdapter;
import com.gsatechworld.musicapp.utilities.Constants;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.TrainerId;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class EnrollStudentsActivity extends AppCompatActivity {
    public ActivityEnrollStudentsBinding enrollStudentsBinding;
    public EnrolledStudenceAdapter adapter;
    public EnrolledViewModel enrolledViewModel;
    public String trainerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enrollStudentsBinding= DataBindingUtil.setContentView(this,R.layout.activity_enroll_students);
        enrolledViewModel=new ViewModelProvider(this).get(EnrolledViewModel.class);
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        trainerID=String.valueOf(sharedPreferences.getInt(TrainerId,0));

        enrollStudentsBinding.layoutBase.toolbar.setTitle(getString(R.string.students_details));
        setSupportActionBar(enrollStudentsBinding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        enrollStudentsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        getStudents();



    }

    public void getStudents(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            enrolledViewModel.getStudents(trainerID).observe(this,enrollStudentsResponse -> {
                if (enrollStudentsResponse != null && enrollStudentsResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    enrollStudentsBinding.recyclerStudents.setLayoutManager(new LinearLayoutManager(this));
                    enrollStudentsBinding.recyclerStudents.setHasFixedSize(true);
                    adapter=new EnrolledStudenceAdapter(this,enrollStudentsResponse.getEnrollStudents());
                    enrollStudentsBinding.recyclerStudents.setAdapter(adapter);
                }
            });
        }


    }
}