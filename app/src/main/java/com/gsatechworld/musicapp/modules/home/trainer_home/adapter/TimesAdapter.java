package com.gsatechworld.musicapp.modules.home.trainer_home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTimeSlotsBinding;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.AvailableTimeSlotResponse;
import com.gsatechworld.musicapp.modules.home.trainer_home.pojo.Slot_details;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.TimeslotViewHolder> {

    private Context mCtx;
    private ArrayList<TimeSlot> timeSlotList;
    private ConstraintLayout seletedTimeSlot;
    private TextView selectedTime;

    public TimesAdapter(Context mCtx, ArrayList<TimeSlot> timeSlotList) {
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
    public void onBindViewHolder(@NonNull TimesAdapter.TimeslotViewHolder holder, int position) {
        holder.binding.setTimeSlot(timeSlotList.get(position));
        holder.binding.imageClose.setOnClickListener(v -> {

        });
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
