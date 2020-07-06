package com.gsatechworld.musicapp.modules.home.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutStudentListBinding;
import com.gsatechworld.musicapp.modules.home.settings.pojo.EnrollStudentsResponse;

import java.util.List;

import static androidx.databinding.DataBindingUtil.inflate;

public class EnrolledStudenceAdapter extends RecyclerView.Adapter<EnrolledStudenceAdapter.EnrollViewHolder> {

    private Context mCtx;
    private List<EnrollStudentsResponse.EnrollStudents> studentsList;

    public EnrolledStudenceAdapter(Context mCtx, List<EnrollStudentsResponse.EnrollStudents> studentsList) {
        this.mCtx = mCtx;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public EnrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutStudentListBinding binding = inflate(LayoutInflater.from(mCtx),
                R.layout.layout_student_list, parent, false);
        return new EnrollViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollViewHolder holder, int position) {

        EnrollStudentsResponse.EnrollStudents enrollStudents=studentsList.get(position);
        holder.studentListBinding.setEnrolledstudent(enrollStudents);


    }

    @Override
    public int getItemCount() {

        return studentsList.size();
    }

    public class EnrollViewHolder extends RecyclerView.ViewHolder {
        public LayoutStudentListBinding studentListBinding;
        public EnrollViewHolder(@NonNull LayoutStudentListBinding binding) {
            super(binding.getRoot());

            this.studentListBinding=binding;
        }
    }
}
