package com.gsatechworld.musicapp.modules.student_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutEntrollmentsBinding;
import com.gsatechworld.musicapp.modules.student_home.EntrollmentDetailsActivity;
import com.gsatechworld.musicapp.modules.student_home.pojo.EntrollmentResponse;
import com.gsatechworld.musicapp.modules.student_home.pojo.Entrollments;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class EntrollmentAdapter extends RecyclerView.Adapter<EntrollmentAdapter.EntrollmentViewHolder> {
public Context context;
public List<EntrollmentResponse.Enrollment_details> entrollmentsList;
public LayoutEntrollmentsBinding entrollmentsBinding;
    public int postion=0;

    public EntrollmentAdapter(Context context, List<EntrollmentResponse.Enrollment_details> entrollmentsList) {
        this.context = context;
        this.entrollmentsList = entrollmentsList;
    }

    @NonNull
    @Override
    public EntrollmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        entrollmentsBinding=inflate(from(context), R.layout.layout_entrollments, parent,
                false);
        return new EntrollmentViewHolder(entrollmentsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EntrollmentViewHolder holder, int position) {

      EntrollmentResponse.Enrollment_details entrollments=entrollmentsList.get(position);
      holder.entrollmentsBinding.setEntrollments(entrollments);
      holder.entrollmentsBinding.cardEntrollment.setOnClickListener(v -> {
          postion=holder.getAdapterPosition();
       Intent intent=new Intent(context.getApplicationContext(),EntrollmentDetailsActivity.class);
       intent.putExtra("entroll_name",entrollments.getEntrollment_name());
       intent.putExtra("entrillment_id",entrollments.getEntrollment_id());
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       context.getApplicationContext().startActivity(intent);
      });


    }

    @Override
    public int getItemCount() {
        return entrollmentsList.size();
    }

    public class EntrollmentViewHolder extends RecyclerView.ViewHolder {
        public LayoutEntrollmentsBinding entrollmentsBinding;

        public EntrollmentViewHolder(@NonNull LayoutEntrollmentsBinding binding) {
            super(binding.getRoot());
            this.entrollmentsBinding=binding;

        }
    }
}
