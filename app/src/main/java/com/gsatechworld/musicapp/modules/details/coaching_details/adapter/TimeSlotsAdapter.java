package com.gsatechworld.musicapp.modules.details.coaching_details.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTimeSlotsBinding;
import com.gsatechworld.musicapp.databinding.ListTimeslotBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.TimesAdapter;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static java.util.Objects.requireNonNull;

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<TimeSlot> timeSlotList;

    public TimeSlotsAdapter(Context context, ArrayList<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListTimeslotBinding   binding=inflate(from(context), R.layout.list_timeslot, parent,
                false);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.binding.setAvailableSlot(timeSlotList.get(position));
    holder.binding.textTime.setOnClickListener(v -> {
        holder.binding.textTime.setTextColor(context.getResources().getColor(R.color.colorAccent));
    });
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ListTimeslotBinding binding;
        public MyViewHolder(final ListTimeslotBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
