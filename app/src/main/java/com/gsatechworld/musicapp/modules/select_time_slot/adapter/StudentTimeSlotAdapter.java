package com.gsatechworld.musicapp.modules.select_time_slot.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutAvilableSlotsBinding;
import com.gsatechworld.musicapp.modules.home.payment.adapter.PaymentRequestAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.AvailableTimesSlotResponse;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class StudentTimeSlotAdapter extends RecyclerView.Adapter<StudentTimeSlotAdapter.TimeSlotHolder> {
    private Context mCtx;
    public ArrayList<AvailableTimesSlotResponse.AvailablesSlotes> timeSlotList;
    private RelativeLayout seletedTimeSlot;
    private TextView selectedTime;
    public String start_time,end_time;
    public OnTimeSelectedListener selectedListener;
    private int index=-1;

    public StudentTimeSlotAdapter(Context mCtx, ArrayList<AvailableTimesSlotResponse.AvailablesSlotes> timeSlotList) {
        this.mCtx = mCtx;
        this.timeSlotList = timeSlotList;
    }



    @NonNull
    @Override
    public TimeSlotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAvilableSlotsBinding binding = inflate(from(mCtx), R.layout.layout_avilable_slots, parent,
                false);
        return new TimeSlotHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull TimeSlotHolder holder, int position) {
        AvailableTimesSlotResponse.AvailablesSlotes availablesSlotes=timeSlotList.get(position);
        holder.binding.setAvailableSlot(availablesSlotes);
        holder.binding.layoutTimeSlot.setOnClickListener(v -> {
            index = position;
            notifyDataSetChanged();
          String selected=availablesSlotes.getStart_time() + "-" +availablesSlotes.getEnd_time();
          start_time=availablesSlotes.getStart_time();
          end_time=availablesSlotes.getEnd_time();
          selectedListener.onTimeAction(start_time,end_time);


        });

        if(index==position){
            holder.binding.layoutTimeSlot.setBackgroundColor(Color.parseColor("#FF4081"));
            holder.binding.textTime.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.binding.layoutTimeSlot.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.binding.textTime.setTextColor(Color.parseColor("#000000"));
        }


    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }

    public class TimeSlotHolder extends RecyclerView.ViewHolder {

        LayoutAvilableSlotsBinding binding;

        public TimeSlotHolder(final LayoutAvilableSlotsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public interface OnTimeSelectedListener{
        void onTimeAction(String startTime,String endTime);
    }

    public void setClickListner(OnTimeSelectedListener listner) {
        this.selectedListener = listner;
    }

}
