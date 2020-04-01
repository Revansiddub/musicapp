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
import com.gsatechworld.musicapp.databinding.ListStudentsBinding;
import com.gsatechworld.musicapp.modules.home.trainer_home.AddAttendanceActivity;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.StudentAttendance;

import java.util.List;

public class StudentsAttendanceAdapter extends RecyclerView.Adapter<StudentsAttendanceAdapter.StudentViewHolder> {
 public List<StudentAttendance> attendanceList;

 public Context context;

    public StudentsAttendanceAdapter(List<StudentAttendance> attendanceList, Context context) {
        this.attendanceList = attendanceList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListStudentsBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_students,parent,false);
        return new StudentViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
     StudentAttendance attendance=attendanceList.get(position);
     holder.binding.setAttendance(attendance);

     if(attendance.gender.equals("Male")){
         holder.binding.imageStudent
                 .setImageDrawable(context.getDrawable(R.drawable.icon_male_student));
         holder.binding.imageStudent
                 .setImageTintList(context.getColorStateList(R.color.colorPrimaryDark));
     }
     else {
         holder.binding.imageStudent
                 .setImageDrawable(context.getDrawable(R.drawable.icon_female_student));
         holder.binding.imageStudent
                 .setImageTintList(context.getColorStateList(R.color.colorAccent));
     }

     holder.binding.layoutApprove.setOnClickListener(v -> {
         Intent intent=new Intent(context.getApplicationContext(), AddAttendanceActivity.class);
         intent.putExtra("name",attendance.getName());
         intent.putExtra("age",attendance.getAge());
         intent.putExtra("mobile",attendance.getPhone());
         intent.putExtra("timing",attendance.getTiming());
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.getApplicationContext().startActivity(intent);
     });

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        ListStudentsBinding binding;
        public StudentViewHolder(ListStudentsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }
}
