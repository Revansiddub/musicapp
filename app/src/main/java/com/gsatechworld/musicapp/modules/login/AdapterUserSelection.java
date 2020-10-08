package com.gsatechworld.musicapp.modules.login;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.AdapterUserBinding;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import java.util.List;


public class AdapterUserSelection extends RecyclerView.Adapter<AdapterUserSelection.ViewHolder> {


    private ItemClickListener clickListener;
    private List<StudentResponse.User> brandLists;
    private Context context;

    public AdapterUserSelection(List<StudentResponse.User> brandLists, Context context) {

        this.brandLists = brandLists;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterUserBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_user, parent, false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.adapterBrandBinding.tvName.setText(brandLists.get(position).getStudentName());


        Glide.with(context)
                .load(brandLists.get(position).getProfile()).apply(new RequestOptions().circleCrop())
                .placeholder(R.drawable.ic_user)
                .into(holder.adapterBrandBinding.imageProfiles);


    }

    @Override
    public int getItemCount() {
        return brandLists.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AdapterUserBinding adapterBrandBinding;

        ViewHolder(AdapterUserBinding flightItemLayoutBinding) {
            super(flightItemLayoutBinding.getRoot());
            adapterBrandBinding = flightItemLayoutBinding;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getPosition());
        }
    }

}