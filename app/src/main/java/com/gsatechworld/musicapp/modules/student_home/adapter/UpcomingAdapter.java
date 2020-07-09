package com.gsatechworld.musicapp.modules.student_home.adapter;

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
public String dates;
public  Date date1,date;
public DateFormat dateFormat;
public SimpleDateFormat simpleDateFormat;
public  static long MILLIS_PER_DAY;
public boolean greater;

    public UpcomingAdapter(Context context, ArrayList<UpcomingResponse.Upcoming_class> upcomingClasses) {
        this.context = context;
        this.upcomingClasses = upcomingClasses;
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
            }




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
}
