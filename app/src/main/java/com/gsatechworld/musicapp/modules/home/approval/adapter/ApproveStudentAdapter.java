package com.gsatechworld.musicapp.modules.home.approval.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutApproveStudentBinding;
import com.gsatechworld.musicapp.modules.home.approval.pojo.Approval;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.ACCEPTED;
import static com.gsatechworld.musicapp.utilities.Constants.IGNORED;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static java.util.Locale.getDefault;

public class ApproveStudentAdapter extends Adapter<ApproveStudentAdapter.ApproveStudentHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<Approval> approvalList;
    private List<Approval> searchableApprovalList;
    private OnActionPerformedListener actionListener;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public ApproveStudentAdapter(Context mCtx, List<Approval> approvalList,
                                 OnActionPerformedListener actionListener) {
        this.mCtx = mCtx;
        this.approvalList = approvalList;
        searchableApprovalList = new ArrayList<>(approvalList);
        this.actionListener = actionListener;
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

        if (approval.getGender().equals(MALE)) {
            holder.binding.imageStudent
                    .setImageDrawable(mCtx.getDrawable(R.drawable.icon_male_student));
            holder.binding.imageStudent
                    .setImageTintList(mCtx.getColorStateList(R.color.colorPrimaryDark));
        } else {
            holder.binding.imageStudent
                    .setImageDrawable(mCtx.getDrawable(R.drawable.icon_female_student));
            holder.binding.imageStudent
                    .setImageTintList(mCtx.getColorStateList(R.color.colorAccent));
        }
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

    public interface OnActionPerformedListener {
        void onActionPerformed(String requestID, String action);
    }

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
                    actionListener.onActionPerformed(approval.getRequestID(), ACCEPTED);
                    break;
                case R.id.textIgnore:
                    actionListener.onActionPerformed(approval.getRequestID(), IGNORED);
                    break;
            }
        }
    }
}