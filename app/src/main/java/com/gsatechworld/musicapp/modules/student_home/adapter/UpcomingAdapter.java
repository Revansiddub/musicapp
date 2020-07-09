package com.gsatechworld.musicapp.modules.student_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutUpcomingClassBinding;
import com.gsatechworld.musicapp.modules.student_home.pojo.UpcomingResponse;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewModel> {
public Context context;
public ArrayList<UpcomingResponse.Upcoming_class> upcomingClasses;
public LayoutUpcomingClassBinding binding;

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
