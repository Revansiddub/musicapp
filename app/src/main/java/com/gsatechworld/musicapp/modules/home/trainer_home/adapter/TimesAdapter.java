package com.gsatechworld.musicapp.modules.home.trainer_home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
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

    private int row_index = 0;
    private Context mCtx;
    private ArrayList<Available_slots> timeSlotList;
    private ConstraintLayout seletedTimeSlot;
    private TextView selectedTime;
    private int index=0;
    public cancelClassListener listener;
    public String startTime,endTime;
    public String cstartTime,cendTime;
    public String start_time,end_time;
    public Date date1,date2,date3,today_date;
    public SimpleDateFormat simpleDateFormat,simpleDateFormat1;
    public onRecyclerItemListener recyclerItemListener;
    public DateFormat dateFormat,dateFormat1;
    public String selectedDate;
    public  static long MILLIS_PER_DAY;
    public boolean greater;
    public String trainerID;
    public String selectedStart,selectedEnd;


    private ItemClickListener clickListener;

    public TimesAdapter(Context mCtx, ArrayList<Available_slots> timeSlotList,String startTime,String endTime,String selectedDate,String trainerID) {
        this.mCtx = mCtx;
        this.timeSlotList = timeSlotList;
        this.startTime=startTime;
        this.endTime=endTime;
        this.selectedDate=selectedDate;
        this.trainerID=trainerID;
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


        try {
            MILLIS_PER_DAY=24*60*60*1000L;
            simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
            date3=simpleDateFormat1.parse(selectedDate);
            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            today_date=new Date();
            greater=Math.abs(date3.getTime() - today_date.getTime()) < MILLIS_PER_DAY;
            if (greater){
                holder.binding.imageClose.setVisibility(View.GONE);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.binding.imageClose.setOnClickListener(v -> {
            selectedStart=timeSlotList.get(position).getStart_time();
            selectedEnd=timeSlotList.get(position).getEnd_time();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mCtx);
            alertDialog.setTitle("Cancel Class");
           alertDialog.setMessage("Are you sure want to cancel this class?");

           alertDialog.setPositiveButton("Yes",(dialog, which) -> {
               listener.onClassCancel(trainerID,selectedDate,selectedStart,selectedEnd);
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

        if(row_index == position){
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

    public class TimeslotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final LayoutTimeSlotsBinding binding;
        public TimeslotViewHolder(final LayoutTimeSlotsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            row_index = getAdapterPosition();
            if (clickListener != null) clickListener.onClick(view, getPosition());
            notifyDataSetChanged();
        }
    }

    public interface cancelClassListener{
        void onClassCancel(String trainerID,String date,String start_time,String end_time);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setActionListener(cancelClassListener listener){
        this.listener=listener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public interface onRecyclerItemListener{
        void onRecyclerClick(int position);
    }

    public  void  setRecyclerItemListener(onRecyclerItemListener recyclerItemListener){
        this.recyclerItemListener=recyclerItemListener;
    }

}
