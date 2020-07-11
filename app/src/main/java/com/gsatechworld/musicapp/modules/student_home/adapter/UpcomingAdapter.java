package com.gsatechworld.musicapp.modules.student_home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutUpcomingClassBinding;
import com.gsatechworld.musicapp.modules.student_home.pojo.UpcomingResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewModel> {
public Context context;
public ArrayList<UpcomingResponse.Upcoming_class> upcomingClasses;
public LayoutUpcomingClassBinding binding;
public String dates,selected_date;
public  Date date1,date,startdate,enddate;
public DateFormat dateFormat,starting,ending;
public SimpleDateFormat simpleDateFormat,startFormat,endFormat;
public  static long MILLIS_PER_DAY;
public String start,end;
public boolean greater;
public studentCancelListener listener;
public String enrollment_id,start_time,end_time,status;
public String starting_Time,ending_Time;


    public UpcomingAdapter(Context context, ArrayList<UpcomingResponse.Upcoming_class> upcomingClasses,String enrollment_id) {
        this.context = context;
        this.upcomingClasses = upcomingClasses;
        this.enrollment_id=enrollment_id;
    }

    @NonNull
    @Override
    public UpcomingViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=inflate(from(context), R.layout.layout_upcoming_class, parent,
                false);
        return new UpcomingViewModel(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewModel holder, int position) {
        UpcomingResponse.Upcoming_class upcoming_class=upcomingClasses.get(position);
        holder.binding.setUpcomingclass(upcoming_class);
        dates=upcoming_class.getDate();
        status=upcoming_class.getCancel_status();
        start=upcoming_class.getStart_time();
        end=upcoming_class.getEnd_time();




        try {
            startFormat=new SimpleDateFormat("HH:mm:ss");
            startdate=startFormat.parse(start);
            starting=new SimpleDateFormat("hh:mm a");
            starting_Time=starting.format(startdate);
            holder.binding.textStartValue.setText(starting_Time);
            endFormat=new SimpleDateFormat("HH:mm:ss");
            enddate=endFormat.parse(end);
            ending=new SimpleDateFormat("hh:mm a");
            ending_Time=ending.format(enddate);
            holder.binding.textEndValue.setText(ending_Time);




        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            MILLIS_PER_DAY=48*60*60*1000L;

             simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            date1 = new Date(simpleDateFormat.parse(dates).getTime());
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            date = new Date();
            greater=Math.abs(date1.getTime() - date.getTime()) < MILLIS_PER_DAY;
            if (greater){
             holder.binding.buttonCancel.setVisibility(View.GONE);
            }
            else {
                holder.binding.buttonCancel.setVisibility(View.VISIBLE);
                holder.binding.buttonCancel.setText(status);
            }


            holder.binding.buttonCancel.setOnClickListener(v -> {
                selected_date=upcoming_class.getDate();
                start_time=upcoming_class.getStart_time();
                end_time=upcoming_class.getEnd_time();


                if (status.equals("Cancelled")){
                    holder.binding.buttonCancel.setClickable(false);
                }


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Cancel Class");
                alertDialog.setMessage("Are you sure want to cancel this class?");

                alertDialog.setPositiveButton("Yes",(dialog, which) -> {
                  listener.onClasscancel(enrollment_id,selected_date,start_time,end_time);
                });

                alertDialog.setNegativeButton("No", (dialog, which) -> dialog.cancel());

                alertDialog.show();
            });




        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return upcomingClasses.size();
    }

    public class UpcomingViewModel extends RecyclerView.ViewHolder {
        public LayoutUpcomingClassBinding binding;


        public UpcomingViewModel(@NonNull LayoutUpcomingClassBinding binding) {
            super(binding.getRoot());

            this.binding=binding;
        }
    }

    public interface studentCancelListener{
        void onClasscancel(String enrollment_id,String date,String startTime,String endTime);
    }
    public void setCancelListener(studentCancelListener listener){
        this.listener=listener;
    }
}
