package com.gsatechworld.musicapp.modules.select_time_slot.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTimeSlotsBinding;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class TimeSlotAdapter extends Adapter<TimeSlotAdapter.TimeSlotHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<TimeSlot> timeSlotList;
    private RelativeLayout seletedTimeSlot;
    private TextView selectedTime;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TimeSlotAdapter(Context mCtx, List<TimeSlot> timeSlotList) {
        this.mCtx = mCtx;
        this.timeSlotList = timeSlotList;
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public TimeSlotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTimeSlotsBinding binding = inflate(from(mCtx), R.layout.layout_time_slots, parent,
                false);
        return new TimeSlotHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotHolder holder, int position) {
        holder.binding.setTimeSlot(timeSlotList.get(position));
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }

    /* ------------------------------------------------------------- *
     * Public Interface
     * ------------------------------------------------------------- */

    public interface OnTimeSlotSelectedListener {
        void onTimeSlotSelected(TimeSlot timeSlot);
    }

    /* ------------------------------------------------------------- *
     * TimeSlot Holder Class
     * ------------------------------------------------------------- */

    class TimeSlotHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutTimeSlotsBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        TimeSlotHolder(final LayoutTimeSlotsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting Listeners to the view*/
            binding.layoutTimeSlot.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClickListener Method
         * ------------------------------------------------------------- */

        @RequiresApi(api = M)
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.layoutTimeSlot) {
                TimeSlot timeSlot = timeSlotList.get(getAdapterPosition());

                if (seletedTimeSlot != null) {
                    seletedTimeSlot.setBackground(mCtx.getDrawable(R.drawable.time_slot_unselected));
                    selectedTime.setTextColor(mCtx.getColorStateList(R.color.colorPrimaryDark));
                }

                binding.layoutTimeSlot.setBackground(mCtx.getDrawable(R.drawable.time_slot_selected));
                binding.textTime.setTextColor(mCtx.getColorStateList(R.color.white));

                seletedTimeSlot = binding.layoutTimeSlot;
                selectedTime = binding.textTime;

                OnTimeSlotSelectedListener timeSlotListener = (OnTimeSlotSelectedListener) mCtx;
                timeSlotListener.onTimeSlotSelected(timeSlot);
            }
        }
    }
}