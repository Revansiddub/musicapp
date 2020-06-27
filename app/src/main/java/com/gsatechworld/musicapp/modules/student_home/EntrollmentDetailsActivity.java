package com.gsatechworld.musicapp.modules.student_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityEntrollmentDetailsBinding;
import com.gsatechworld.musicapp.utilities.Constants;

import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

public class EntrollmentDetailsActivity extends BaseActivity {
    public String entrollment_name;
    ActivityEntrollmentDetailsBinding binding;
    public UpcomingClassViewModel classViewModel;
    public String student_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrollment_details);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_entrollment_details);

        SharedPreferences preferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        student_id=preferences.getString(Constants.STUDENT_ID,null);

        classViewModel=new ViewModelProvider(this).get(UpcomingClassViewModel.class);


        entrollment_name=getIntent().getStringExtra("entroll_name");
        binding.textEntrollment.setText(entrollment_name);

        binding.buttonCancel.setOnClickListener(v -> {

        });



        fetchUpcomingClass();



    }
    public void fetchUpcomingClass(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            classViewModel.getUpcoming_class(student_id).observe(this,upcomingResponse -> {
                hideLoadingIndicator();
                if (upcomingResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    String upcoming_class=upcomingResponse.getUpcoming_class();
                    String time=upcomingResponse.getTime();
                    binding.date.setText(upcoming_class);
                    binding.time.setText(time);
                }
            });

        }
    }
}
