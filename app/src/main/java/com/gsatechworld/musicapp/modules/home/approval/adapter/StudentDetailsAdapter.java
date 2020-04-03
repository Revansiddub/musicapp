package com.gsatechworld.musicapp.modules.home.approval.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentDetailsAdapter extends RecyclerView.Adapter<StudentDetailsAdapter.StudentViewHolder> {
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
