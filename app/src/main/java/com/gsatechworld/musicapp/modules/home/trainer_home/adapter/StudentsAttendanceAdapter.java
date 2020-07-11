package com.gsatechworld.musicapp.modules.home.trainer_home.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.LayoutAttendanceBinding;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.AddAttendanceActivity;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.FetchStudentsResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gsatechworld.musicapp.utilities.Constants.ENROLLMENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.SELECTED_DATE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.time.LocalTime.now;
import static java.time.LocalTime.parse;

public class StudentsAttendanceAdapter extends RecyclerView.Adapter<StudentsAttendanceAdapter.StudentViewHolder> implements AddAttendanceActivity.onStatusListener {
    public List<FetchStudentsResponse.GetStudentsResult.Time_slots.Student_list> attendanceList;
    public List<FetchStudentsResponse.GetStudentsResult>  studentsResults;
    public List<FetchStudentsResponse.GetStudentsResult.Time_slots> time_slotsList;
    public String start_time,end_time;
    public String statusString;
    public int position;
    public cancelPerformedLister performedLister;
    public Context context;
    BaseActivity baseActivity;
    public boolean stat=false;
    private String selected_date;
    public String today_date;
    private Activity activity;
    public String enorllment_id;
    public Date date1,date2,date3;
    public String starTime;
    public Time startingTime;
    public String cstartTime,cendTime;

    public StudentsAttendanceAdapter(List<FetchStudentsResponse.GetStudentsResult.Time_slots.Student_list> attendanceList,
                                     Context context, String startTime, String endTime, String selected_date,String cstartTime,
                                     String cendTime,
                                     Activity activity) {
        this.attendanceList = attendanceList;
        this.context = context;
        this.start_time=startTime;
        this.end_time=endTime;
        this.selected_date = selected_date;
        this.cstartTime=cstartTime;
        this.cendTime=cendTime;
        this.activity = activity;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAttendanceBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.layout_attendance,parent,false);
        return new StudentViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
     //   GetStudentsResponse.GetStudentsResult.Dates.Time_slots.Studentslist attendance=attendanceList.get(position);
        FetchStudentsResponse.GetStudentsResult.Time_slots.Student_list student_list=attendanceList.get(position);

        holder.binding.setAttendance(student_list);

//        holder.binding.startTime.setText(cstartTime);
//        holder.binding.endtime.setText(cendTime);



        holder.binding.attendanceStatus.setText(statusString);

        enorllment_id=String.valueOf(student_list.getEnrollment_id());
        holder.binding.layoutApprove.setOnClickListener(v -> {
            Intent intent=new Intent(context.getApplicationContext(), AddAttendanceActivity.class);
            intent.putExtra("name", student_list.getStudent_name());
            intent.putExtra("age", student_list.getStudent_age());
            intent.putExtra("mobile",student_list.getMobile_number());
            intent.putExtra("startTime",start_time);
            intent.putExtra("endTime",end_time);
            intent.putExtra(Intent.EXTRA_TEXT,student_list.getStudent_image());
            intent.putExtra(ENROLLMENT_ID,student_list.getEnrollment_id());
            intent.putExtra(STUDENT_ID,student_list.getStudent_id());
            intent.putExtra(SELECTED_DATE, selected_date);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);




            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            today_date=dateFormat.format(date);



            try {
                starTime=start_time;
                startingTime = new Time (starTime);
                DateFormat format=new SimpleDateFormat("HH:mm:ss");
                date2= format.parse(starTime);
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                date3=cal.getTime();
                if (date2.before(date3)){
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }



            try {
                 date1=new SimpleDateFormat("yyyy-MM-dd").parse(selected_date);
                 if (date1.after(date)){
                     Toast.makeText(v.getContext(),"Can't Add Attendance before the selected date",Toast.LENGTH_LONG).show();
                 }
                 else {
                     context.getApplicationContext().startActivity(intent);
                 }

            } catch (ParseException e) {
                e.printStackTrace();
            }





        });

//        holder.binding.cancelClass.setOnClickListener(v -> {
//
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//
//            alertDialog.setTitle("Cancel Class");
//            alertDialog.setMessage("Are you sure want to cancel this class?");
//
//            alertDialog.setPositiveButton("Yes",(dialog, which) -> {
//                performedLister.onActionCancel(enorllment_id,selected_date,start_time,end_time);
//            });
//            alertDialog.setNegativeButton("No", (dialog, which) -> dialog.cancel());
//
//            alertDialog.show();
//        });

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

    public interface cancelPerformedLister{
        void onActionCancel(String enrollment_id,String date,String start_time,String end_time);
    }

    public void setActionListener(cancelPerformedLister listener){
        this.performedLister=listener;
    }


}
