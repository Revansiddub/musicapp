package com.gsatechworld.musicapp.modules.home.trainer_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.LayoutAttendanceBinding;
import com.gsatechworld.musicapp.modules.home.trainer_home.AddAttendanceActivity;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.GetStudentsResponse;
import com.gsatechworld.musicapp.modules.select_time_slot.adapter.StudentTimeSlotAdapter;

import java.util.List;

import static com.gsatechworld.musicapp.utilities.Constants.ENROLLMENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_ID;

public class StudentsAttendanceAdapter extends RecyclerView.Adapter<StudentsAttendanceAdapter.StudentViewHolder> implements AddAttendanceActivity.onStatusListener {
    public List<GetStudentsResponse.GetStudentsResult.Time_slots.Student_list> attendanceList;
    public List<GetStudentsResponse.GetStudentsResult>  studentsResults;
    public List<GetStudentsResponse.GetStudentsResult.Time_slots> time_slotsList;
    public String start_time,end_time;
    public String statusString;
    public int position;

    public Context context;
    BaseActivity baseActivity;
    public boolean stat=false;

    public StudentsAttendanceAdapter(List<GetStudentsResponse.GetStudentsResult.Time_slots.Student_list> attendanceList, Context context,String startTime,String endTime) {
        this.attendanceList = attendanceList;
        this.context = context;
        this.start_time=startTime;
        this.end_time=endTime;
    }

    public StudentsAttendanceAdapter(Context context,String attendance_status) {
        this.context=context;
        this.statusString=attendance_status;

        if (statusString.equals("present")){
            this.statusString="present";
            }
            else {
            this.statusString="absent";
            }


    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAttendanceBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_attendance,parent,false);
        return new StudentViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
     //   GetStudentsResponse.GetStudentsResult.Dates.Time_slots.Studentslist attendance=attendanceList.get(position);
        GetStudentsResponse.GetStudentsResult.Time_slots.Student_list student_list=attendanceList.get(position);

        holder.binding.setAttendance(student_list);
        holder.binding.startTime.setText(start_time);
        holder.binding.endtime.setText(end_time);



        holder.binding.attendanceStatus.setText(statusString);


        holder.binding.layoutApprove.setOnClickListener(v -> {
            Intent intent=new Intent(context.getApplicationContext(), AddAttendanceActivity.class);
            intent.putExtra("name",student_list.getStudent_name());
             intent.putExtra("age",student_list.getStudent_age());
             intent.putExtra("mobile",student_list.getMobile_number());
             intent.putExtra("startTime",start_time);
             intent.putExtra("endTime",end_time);
             intent.putExtra(Intent.EXTRA_TEXT,student_list.getStudent_image());
             intent.putExtra(ENROLLMENT_ID,student_list.getEnrollment_id());
             intent.putExtra(STUDENT_ID,student_list.getStudent_id());
             //intent.putExtra("timing",attendance.getTiming());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    @Override
    public void onStatusPerformed(String status) {

    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        LayoutAttendanceBinding binding;
        public StudentViewHolder(LayoutAttendanceBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
