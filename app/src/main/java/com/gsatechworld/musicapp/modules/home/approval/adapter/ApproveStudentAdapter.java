package com.gsatechworld.musicapp.modules.home.approval.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.LayoutApproveStudentBinding;
import com.gsatechworld.musicapp.modules.home.approval.StudentsDetails;
import com.gsatechworld.musicapp.modules.home.approval.pojo.Approval;
import com.gsatechworld.musicapp.modules.home.payment.adapter.PaymentRequestAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.AddAttendanceActivity;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static com.gsatechworld.musicapp.utilities.Constants.ACCEPTED;
import static com.gsatechworld.musicapp.utilities.Constants.IGNORED;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static java.util.Locale.getDefault;
import static java.util.Objects.requireNonNull;

public class ApproveStudentAdapter extends Adapter<ApproveStudentAdapter.ApproveStudentHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<Approval> approvalList;
    private List<Approval> searchableApprovalList;
    private OnActionPerformedListener actionListener;
    private BaseActivity baseActivity;
   public  ConstraintLayout constraintLayout;
    String entrollmet_id,student_id;



    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public ApproveStudentAdapter(Context mCtx, List<Approval> approvalList) {
        this.mCtx = mCtx;
        this.approvalList = approvalList;
        searchableApprovalList = new ArrayList<>(approvalList);

    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public ApproveStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutApproveStudentBinding binding = inflate(from(mCtx), R.layout.layout_approve_student,
                parent, false);
        return new ApproveStudentHolder(binding);
    }

    @RequiresApi(api = M)
    @Override
    public void onBindViewHolder(@NonNull ApproveStudentHolder holder, int position) {
        Approval approval = approvalList.get(position);

        holder.binding.setApproval(approval);

//        if (approval.getGender().equals(MALE)) {
//            holder.binding.imageStudent
//                    .setImageDrawable(mCtx.getDrawable(R.drawable.icon_male_student));
//            holder.binding.imageStudent
//                    .setImageTintList(mCtx.getColorStateList(R.color.colorPrimary));
//        } else {
//            holder.binding.imageStudent
//                    .setImageDrawable(mCtx.getDrawable(R.drawable.icon_female_student));
//            holder.binding.imageStudent
//                    .setImageTintList(mCtx.getColorStateList(R.color.colorAccent));
//        }




        holder.binding.layoutApprove.setOnClickListener(v -> {
            Intent intent=new Intent(mCtx.getApplicationContext(), StudentsDetails.class);
            intent.putExtra("name",approval.getStudentName());
            intent.putExtra("age",approval.getAge());
            intent.putExtra("gender",approval.getGender());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mCtx.getApplicationContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return approvalList.size();
    }

    /* ------------------------------------------------------------- *
     * Public Method
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to search item in the list based on the character or string.
     *
     * @param charText char sequence that need to be searched in the list.
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());

        approvalList.clear();

        if (charText.length() == 0)
            approvalList.addAll(searchableApprovalList);
        else
            for (Approval approval : searchableApprovalList)
                if (approval.getStudentName().toLowerCase().contains(charText.toLowerCase()))
                    approvalList.add(approval);

        notifyDataSetChanged();
    }

    /* ------------------------------------------------------------- *
     * Public Interface
     * ------------------------------------------------------------- */



    /* ------------------------------------------------------------- *
     * Trainer Holder Class
     * ------------------------------------------------------------- */

    class ApproveStudentHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutApproveStudentBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        ApproveStudentHolder(final LayoutApproveStudentBinding binding) {
            super(binding.getRoot());

            constraintLayout=binding.layoutApprove;

            this.binding = binding;

            /*Setting listeners to the views*/
            binding.textAccept.setOnClickListener(this);
            binding.textIgnore.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClickListener Method
         * ------------------------------------------------------------- */

        @Override
        public void onClick(View view) {
            Approval approval = approvalList.get(getAdapterPosition());

            switch (view.getId()) {
                case R.id.textAccept:
                    entrollmet_id=approval.getEnrollment_id();
                    student_id=approval.getStudentID();
                    actionListener.onActionPerformed(entrollmet_id,student_id, "2");
                    showSnackBar((Activity) mCtx,"Request Accepted");
                    break;
                case R.id.textIgnore:
                   actionListener.onActionPerformed(entrollmet_id,student_id, "0");
                    approvalList.remove(approval);
                    notifyDataSetChanged();
                    showSnackBar((Activity) mCtx,"Request Ignored");
                    break;
            }
        }
    }

    public void showSnackBar(Activity context, String message) {
        Snackbar snackbar = make(context.findViewById(android.R.id.content), message, LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }
    public interface OnActionPerformedListener {
        void onActionPerformed(String entrollmentID, String studentID,String action);

    }
    public void setActionListener(OnActionPerformedListener listener){
        this.actionListener=listener;
    }
}