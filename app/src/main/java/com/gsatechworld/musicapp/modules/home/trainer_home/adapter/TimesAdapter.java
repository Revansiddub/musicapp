package com.gsatechworld.musicapp.modules.home.trainer_home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTimeSlotsBinding;
import com.gsatechworld.musicapp.modules.home.trainer_home.AttendanceActivity;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.AvailableTimeSlotResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.Available_slots;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.Slot_details;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.TimeslotViewHolder> {

    private Context mCtx;
    private ArrayList<Available_slots> timeSlotList;
    private ConstraintLayout seletedTimeSlot;
    private TextView selectedTime;
    private int index=0;
    public cancelClassListener listener;
    public String startTime,endTime;
    public String cstartTime,cendTime;
    public String start_time,end_time;
    public Date date1,date2;
    public SimpleDateFormat simpleDateFormat;
    public onRecyclerItemListener recyclerItemListener;
    public DateFormat dateFormat;


    public TimesAdapter(Context mCtx, ArrayList<Available_slots> timeSlotList,String startTime,String endTime) {
        this.mCtx = mCtx;
        this.timeSlotList = timeSlotList;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    @NonNull
    @Override
    public TimeslotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTimeSlotsBinding binding = inflate(from(mCtx), R.layout.layout_time_slots, parent,
                false);
        return new TimeslotViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull TimesAdapter.TimeslotViewHolder holder, int position) {
        holder.binding.setTimeSlot(timeSlotList.get(position));
        start_time=timeSlotList.get(position).getStart_time();
        end_time=timeSlotList.get(position).getEnd_time();
        holder.binding.layoutTimeSlot.setOnClickListener(v -> {
            index = position;
            recyclerItemListener.onRecyclerClick(index);
            notifyDataSetChanged();
        });

        holder.binding.imageClose.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mCtx);
            alertDialog.setTitle("Cancel Class");
           alertDialog.setMessage("Are you sure want to cancel this class?");

           alertDialog.setPositiveButton("Yes",(dialog, which) -> {
               listener.onClassCancel(start_time,end_time);
           });

           alertDialog.setNegativeButton("No", (dialog, which) -> dialog.cancel());

           alertDialog.show();


        });

        try {
            simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
            date1 = simpleDateFormat.parse(start_time);
            dateFormat = new SimpleDateFormat("hh:mm a");
            cstartTime=dateFormat.format(date1);
            date2=simpleDateFormat.parse(end_time);
            cendTime=dateFormat.format(date2);
            holder.binding.textTime.setText(cstartTime + "-" + cendTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(index==position){
            holder.binding.layoutTimeSlot.setBackgroundColor(Color.parseColor("#FF4081"));
            holder.binding.textTime.setTextColor(Color.parseColor("#000000"));
        }else{
            holder.binding.layoutTimeSlot.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.binding.textTime.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }

    public class TimeslotViewHolder extends RecyclerView.ViewHolder {
        private final LayoutTimeSlotsBinding binding;
        public TimeslotViewHolder(final LayoutTimeSlotsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }

    public interface cancelClassListener{
        void onClassCancel(String start_time,String end_time);
    }

    public void setActionListener(cancelClassListener listener){
        this.listener=listener;
    }

    public interface onRecyclerItemListener{
        void onRecyclerClick(int position);
    }

    public  void  setRecyclerItemListener(onRecyclerItemListener recyclerItemListener){
        this.recyclerItemListener=recyclerItemListener;
    }

}
