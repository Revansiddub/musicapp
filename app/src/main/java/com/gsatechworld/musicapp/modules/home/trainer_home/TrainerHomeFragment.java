package com.gsatechworld.musicapp.modules.home.trainer_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.applandeo.materialcalendarview.utils.DateUtils;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentTrainerHomeBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static androidx.databinding.DataBindingUtil.inflate;

public class TrainerHomeFragment extends Fragment implements View.OnClickListener,CoachingDetailsFragment.CoachingDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentTrainerHomeBinding binding;
    private TrainerHomeViewModel viewModel;
    private  CoachingDetails coachingDetails;
    public String type;


    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_trainer_home, container, false);
        Bundle bundle=this.getArguments();
        type = bundle.getString("coaching_type");
        binding.calendarView.setSelectedDates(getSelectedDays());
        binding.calendarView.setOnDayClickListener(eventDay -> {
            Intent intent = new Intent(getActivity(), AttendanceActivity.class);
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
                calendar.add(Calendar.DAY_OF_MONTH, i);
                calendars.add(calendar);
            }

            }
        else {
            for (int i=0;i<30;i++){
                Calendar calendar = DateUtils.getCalendar();

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
}