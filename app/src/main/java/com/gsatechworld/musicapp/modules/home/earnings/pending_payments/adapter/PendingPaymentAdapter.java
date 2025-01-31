package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutPendingPaymentBinding;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PendingPaymentsResp;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static com.gsatechworld.musicapp.utilities.Constants.NOT_PAID;
import static com.gsatechworld.musicapp.utilities.Constants.PAID;
import static java.util.Locale.getDefault;

public class PendingPaymentAdapter extends Adapter<PendingPaymentAdapter.PendingPaymentHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private ArrayList<PendingPaymentsResp.PendingPayments> pendingPaymentList;
  //  private List<Payment> searchablePaymentList;

    public PendingPaymentAdapter(Context mCtx, ArrayList<PendingPaymentsResp.PendingPayments> pendingPaymentList) {
        this.mCtx = mCtx;
        this.pendingPaymentList = pendingPaymentList;
      //  searchablePaymentList = new ArrayList<>(pendingPaymentList);
    }

    @NonNull
    @Override
    public PendingPaymentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPendingPaymentBinding binding = inflate(from(mCtx), R.layout.layout_pending_payment,
                parent, false);
        return new PendingPaymentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingPaymentHolder holder, int position) {
        holder.binding.setStudentlist(pendingPaymentList.get(position));
        holder.binding.textPaid.setOnClickListener(v -> {
            pendingPaymentList.remove(position);
            notifyDataSetChanged();
            showSnackBar((Activity) mCtx,"Request Accepted");
        });
    }

    @Override
    public int getItemCount() {
        return pendingPaymentList.size();
    }

    /* ------------------------------------------------------------- *
     * Public Method
     * ------------------------------------------------------------- */

//    /**
//     * This method is invoked to search item in the list based on the character or string.
//     *
//     * @param charText char sequence that need to be searched in the list.
//     */
//    public void filter(String charText) {
//        charText = charText.toLowerCase(getDefault());
//
//        pendingPaymentList.clear();
//
//        if (charText.length() == 0)
//            pendingPaymentList.addAll(searchablePaymentList);
//        else
//            for (Payment payment : searchablePaymentList)
//                if (payment.getStudentName().toLowerCase().contains(charText.toLowerCase()))
//                    pendingPaymentList.add(payment);
//
//        notifyDataSetChanged();
//    }

    public interface OnActionPerformedListener {
        void onActionPerformed(String studentID, String action);
    }

    class PendingPaymentHolder extends ViewHolder implements OnClickListener {

        private final LayoutPendingPaymentBinding binding;

        PendingPaymentHolder(final LayoutPendingPaymentBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting listeners to the views*/
            binding.textPaid.setOnClickListener(this);
            binding.textNotPaid.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            PendingPaymentsResp.PendingPayments payment = pendingPaymentList.get(getAdapterPosition());
            OnActionPerformedListener onActionPerformed = (OnActionPerformedListener) mCtx;
            switch (view.getId()) {
                case R.id.textPaid:
                    onActionPerformed.onActionPerformed(String.valueOf(payment.getStudent_id()), PAID);
                    break;
                case R.id.textNotPaid:
                    onActionPerformed.onActionPerformed(String.valueOf(payment.getStudent_id()), NOT_PAID);
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
}