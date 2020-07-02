package com.gsatechworld.musicapp.modules.student_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityEntrollmentDetailsBinding;
import com.gsatechworld.musicapp.modules.login.LoginActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.gsatechworld.musicapp.core.manager.SessionManager.getSessionInstance;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

public class EntrollmentDetailsActivity extends BaseActivity {
    public String entrollment_name,enrollment_id,cancellation;
    ActivityEntrollmentDetailsBinding binding;
    public UpcomingClassViewModel classViewModel;
    public String student_id;
    public CancelViewModel cancelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrollment_details);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_entrollment_details);

        cancelViewModel=new ViewModelProvider(this).get(CancelViewModel.class);

        SharedPreferences preferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        student_id=preferences.getString(Constants.STUDENT_ID,null);

        classViewModel=new ViewModelProvider(this).get(UpcomingClassViewModel.class);


        entrollment_name=getIntent().getStringExtra("entroll_name");
        enrollment_id=getIntent().getStringExtra("entrillment_id");
        binding.textEntrollment.setText(entrollment_name);

        binding.buttonCancel.setOnClickListener(v -> {

         String date=binding.date.getText().toString();
         String start_time=binding.time.getText().toString();
         String end_time=binding.time.getText().toString();
         cancellation="3";

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setTitle("Cancel Class");
            alertDialog.setMessage("Are you sure want to cancel this class?");

            alertDialog.setPositiveButton(this.getString(R.string.yes), (dialog, which) -> {

                if (getNetworkInstance(this).isConnectedToInternet()){

                    cancelViewModel.cancelClass(enrollment_id,date,start_time,end_time).observe(this,commonResponse -> {
                        if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                            openSuccessDialog(commonResponse.getMessage());
                            finish();
                        }
                    });

                }

            });
            alertDialog.setNegativeButton(this.getString(R.string.no), (dialog, which) -> dialog.cancel());

            alertDialog.show();
        });



        fetchUpcomingClass();



    }
    public void fetchUpcomingClass(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            classViewModel.getUpcoming_class(student_id).observe(this,upcomingResponse -> {
                hideLoadingIndicator();
                if (upcomingResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    String upcoming_class=upcomingResponse.getUpcoming_class();
                    String time=upcomingResponse.getTime();
                    binding.date.setText(upcoming_class);
                    binding.time.setText(time);
                    String [] start_time=time.split(" - ");

                }

                else {
                    showSnackBar(this,upcomingResponse.getStatus());
                }
            });

        }

    }

    public void cancel_Class(){


    }
}
