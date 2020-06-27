package com.gsatechworld.musicapp.modules.home.trainer_home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentTrainerHomeBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.DateViewModel;
import com.gsatechworld.musicapp.utilities.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

public class TrainerHomeFragment extends Fragment implements View.OnClickListener,CoachingDetailsFragment.CoachingDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentTrainerHomeBinding binding;
    private TrainerHomeViewModel viewModel;
    private  CoachingDetails coachingDetails;
    public String type;
    public int trainerId;
    public Context context;
    public DateViewModel dateViewModel;
    private BaseActivity baseActivity;
    public String  month,year;
    public String trainerID;
    public ArrayList<String> dateList;


    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_trainer_home, container, false);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(Constants.MyPREFERENCES,Context.MODE_PRIVATE);
        trainerID=String.valueOf(sharedPreferences.getInt(Constants.TrainerId,0));


        dateViewModel=new ViewModelProvider(this).get(DateViewModel.class);

        baseActivity = (BaseActivity) getActivity();


        Bundle bundle=this.getArguments();
        type = bundle.getString("coaching_type");
        trainerId=bundle.getInt("trainerID");
        binding.calendarView.setSelectedDates(getSelectedDays());
        Calendar calendar=binding.calendarView.getCurrentPageDate();
        Date date1=calendar.getTime();
        SimpleDateFormat simpleDateFormat1 =
                new SimpleDateFormat("dd-MM-yyyy");
        String formatted_date=simpleDateFormat1.format(date1);
        String[] current_date=formatted_date.split("-");
        month=current_date[1];
        year=current_date[2];

        fetchDates();
        binding.calendarView.setOnDayClickListener(eventDay -> {
            Calendar selectedDate=binding.calendarView.getSelectedDate();
            Date date=selectedDate.getTime();
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("dd-MM-yyyy");
            String timestamp= simpleDateFormat.format(date);


            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(Constants.SELECTED_DATE,timestamp);
            editor.commit();

            Intent intent = new Intent(getActivity(), AttendanceActivity.class);
            intent.putExtra("trainerID",trainerId);
            intent.putExtra("date",timestamp);
                startActivity(intent);
        });

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(TrainerHomeViewModel.class);





        return binding.getRoot();
    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();
        if(type.equals("Daily")){
            for (int i = 0; i < 30; i++) {
                Calendar calendar = DateUtils.getCalendar();
                //calendar.add(Calendar.DAY_OF_MONTH,i);
                calendar.add(Calendar.DAY_OF_MONTH,i);
                calendars.add(calendar);
            }

            }
        else {
            for (int i=0;i<30;i++){
                Calendar calendar = DateUtils.getCalendar();
                i=i+7;

            }
        }




        return calendars;
    }

    @Override
    public void coachingDetails(CoachingDetails coachingDetails) {
        this.coachingDetails=coachingDetails;
        if (coachingDetails.isDaily() == true){

        }

    }

    @Override
    public void onClick(View v) {

    }

    public void fetchDates(){
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            dateViewModel.getDates(trainerID,month,year).observe(getViewLifecycleOwner(),dateResponse -> {
                baseActivity.hideLoadingIndicator();
                if (dateResponse.getResponse().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    dateList=dateResponse.getDates();

                }
                else {

                }
            });
        }

    }
}