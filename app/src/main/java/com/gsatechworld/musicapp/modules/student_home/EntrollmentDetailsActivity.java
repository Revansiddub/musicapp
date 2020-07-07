package com.gsatechworld.musicapp.modules.student_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityEntrollmentDetailsBinding;
import com.gsatechworld.musicapp.modules.login.LoginActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public Date selected_date;
    public Date date1,date2,date3;
    public String formattedDate;
    public SimpleDateFormat simpleDateFormat;
    public String cstartTime,cendTime;
    public String starting_time,ending_time;
    public DateFormat dateFormat;


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

            try {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                selected_date = new Date(sdf.parse(date).getTime());

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                formattedDate=simpleDateFormat.format(selected_date);


            } catch (ParseException e) {
                e.printStackTrace();
            }
         String start_time=binding.startTime.getText().toString();
         String end_time=binding.endTime.getText().toString();
         cancellation="3";

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setTitle("Cancel Class");
            alertDialog.setMessage("Are you sure want to cancel this class?");

            alertDialog.setPositiveButton(this.getString(R.string.yes), (dialog, which) -> {

                if (getNetworkInstance(this).isConnectedToInternet()){

                    cancelViewModel.cancelClass(enrollment_id,formattedDate,start_time,end_time).observe(this,commonResponse -> {
                        if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                            openSuccessDialog(commonResponse.getMessage());
                            binding.buttonCancel.setVisibility(View.GONE);
//                            finish();
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
                    String date=upcomingResponse.getDate();
                    starting_time=upcomingResponse.getStart_time();
                    ending_time=upcomingResponse.getEnd_time();
                    try {
                        simpleDateFormat=new SimpleDateFormat("HH:mm");
                        date1 = simpleDateFormat.parse(starting_time);
                        dateFormat = new SimpleDateFormat("hh:mm a");
                        cstartTime=dateFormat.format(date1);
                        date2=simpleDateFormat.parse(ending_time);
                        cendTime=dateFormat.format(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    binding.date.setText(date);
                    binding.startTime.setText(cstartTime);
                    binding.endTime.setText(cendTime);


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
