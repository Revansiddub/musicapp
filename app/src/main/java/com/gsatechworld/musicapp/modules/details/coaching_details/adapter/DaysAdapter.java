package com.gsatechworld.musicapp.modules.details.coaching_details.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutDayBinding;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class DaysAdapter extends Adapter<DaysAdapter.DaysHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<String> daysList;
    private String recurrenceType;
    private OnDaySelectedListener selectedListener;
    private int index=-1;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public DaysAdapter(Context mCtx, List<String> daysList, String recurrenceType,
                       OnDaySelectedListener selectedListener) {
        this.mCtx = mCtx;
        this.daysList = daysList;
        this.recurrenceType = recurrenceType;
        this.selectedListener = selectedListener;
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public DaysHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutDayBinding binding = inflate(from(mCtx), R.layout.layout_day, parent,
                false);
        return new DaysHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysHolder holder, int position) {
        holder.binding.setDay(daysList.get(position));

        holder.binding.layoutDay.setOnClickListener(v -> {
            String selectedDay = daysList.get(position);
            index = position;
            notifyDataSetChanged();

            if(index==position){
                holder.binding.layoutDay.setBackgroundColor(Color.parseColor("#FF4081"));
            }else{
                holder.binding.layoutDay.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }


            if (selectedListener != null)
                selectedListener.onDaySelected(selectedDay, recurrenceType);



        });





    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    /* ------------------------------------------------------------- *
     * Public Interface
     * ------------------------------------------------------------- */

    public interface OnDaySelectedListener {
        void onDaySelected(String selectedDay, String recurrenceType);
    }

    /* ------------------------------------------------------------- *
     * Days Holder Class
     * ------------------------------------------------------------- */

    class DaysHolder extends ViewHolder{

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutDayBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        DaysHolder(final LayoutDayBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting listeners to the view*/
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClickListener Method
         * ------------------------------------------------------------- */


    }
}