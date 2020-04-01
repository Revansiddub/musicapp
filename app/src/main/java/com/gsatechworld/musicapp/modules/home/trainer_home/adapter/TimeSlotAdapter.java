package com.gsatechworld.musicapp.modules.home.trainer_home.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTimeSlotsBinding;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.TimeslotViewHolder> {

    private Context mCtx;
    private List<TimeSlot> timeSlotList;
    private ConstraintLayout seletedTimeSlot;
    private TextView selectedTime;

    public TimeSlotAdapter(Context mCtx, List<TimeSlot> timeSlotList) {
        this.mCtx = mCtx;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public TimeslotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTimeSlotsBinding binding = inflate(from(mCtx), R.layout.layout_time_slots, parent,
                false);
        return new TimeslotViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotAdapter.TimeslotViewHolder holder, int position) {
        holder.binding.setTimeSlot(timeSlotList.get(position));
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
}
