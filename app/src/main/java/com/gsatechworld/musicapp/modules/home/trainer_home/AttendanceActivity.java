package com.gsatechworld.musicapp.modules.home.trainer_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityAttendanceBinding;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.StudentsAttendanceAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentsResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.AttendanceViewModel;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.TimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.TimeSlotAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlotResponse;
import com.gsatechworld.musicapp.utilities.NetworkUtilities;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;

public class AttendanceActivity extends AppCompatActivity {
    ActivityAttendanceBinding binding;
    RecyclerView recyclerView_slot,recyclerView_studnts;
    TimeSlotAdapter adapter;
    private String trainerID;
    TimeSlotViewModel viewModel;
    AttendanceViewModel attendanceViewModel;
    StudentsAttendanceAdapter attendanceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_attendance);
        recyclerView_slot=binding.recyclerTimeSlots;
        recyclerView_slot.setHasFixedSize(true);
        recyclerView_studnts=binding.recyclerStudents;

        recyclerView_studnts.setHasFixedSize(true);

        viewModel=new ViewModelProvider(this).get(TimeSlotViewModel.class);
        attendanceViewModel=new ViewModelProvider(this).get(AttendanceViewModel.class);
        getTimeLots();
        getStudents();

    }

    public void showSnackBar(Activity context, String message) {
        Snackbar snackbar = make(context.findViewById(android.R.id.content), message, LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }

    private void getStudents() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            attendanceViewModel.getStudentsDetails().observe(this, new Observer<StudentsResponse>() {
                @Override
                public void onChanged(StudentsResponse response) {
                    recyclerView_studnts.setLayoutManager(new LinearLayoutManager(AttendanceActivity.this));
                    recyclerView_studnts.setAdapter(new StudentsAttendanceAdapter(response.getAttendanceList(),AttendanceActivity.this));

                }
            });

        }
    }

    private void getTimeLots() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){

            viewModel.slotResponseLiveData().observe(this, new Observer<TimeSlotResponse>() {
                @Override
                public void onChanged(TimeSlotResponse timeSlotResponse) {
                    recyclerView_slot.setLayoutManager(new GridLayoutManager(AttendanceActivity.this,2));
                    recyclerView_slot.setAdapter(new TimeSlotAdapter(AttendanceActivity.this, timeSlotResponse.getTimeSlotList()));

                }
            });

        }

    }
}
