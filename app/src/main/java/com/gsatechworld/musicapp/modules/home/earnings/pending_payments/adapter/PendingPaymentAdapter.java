package com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutPendingPaymentBinding;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.Payment;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.NOT_PAID;
import static com.gsatechworld.musicapp.utilities.Constants.PAID;
import static java.util.Locale.getDefault;

public class PendingPaymentAdapter extends Adapter<PendingPaymentAdapter.PendingPaymentHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<Payment> pendingPaymentList;
    private List<Payment> searchablePaymentList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public PendingPaymentAdapter(Context mCtx, List<Payment> pendingPaymentList) {
        this.mCtx = mCtx;
        this.pendingPaymentList = pendingPaymentList;
        searchablePaymentList = new ArrayList<>(pendingPaymentList);
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public PendingPaymentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPendingPaymentBinding binding = inflate(from(mCtx), R.layout.layout_pending_payment,
                parent, false);
        return new PendingPaymentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingPaymentHolder holder, int position) {
        holder.binding.setPayment(pendingPaymentList.get(position));
    }

    @Override
    public int getItemCount() {
        return pendingPaymentList.size();
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

        pendingPaymentList.clear();

        if (charText.length() == 0)
            pendingPaymentList.addAll(searchablePaymentList);
        else
            for (Payment payment : searchablePaymentList)
                if (payment.getStudentName().toLowerCase().contains(charText.toLowerCase()))
                    pendingPaymentList.add(payment);

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

    class PendingPaymentHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutPendingPaymentBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        PendingPaymentHolder(final LayoutPendingPaymentBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting listeners to the views*/
            binding.textPaid.setOnClickListener(this);
            binding.textNotPaid.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClickListener Method
         * ------------------------------------------------------------- */

        @Override
        public void onClick(View view) {
            Payment payment = pendingPaymentList.get(getAdapterPosition());
            OnActionPerformedListener onActionPerformed = (OnActionPerformedListener) mCtx;
            switch (view.getId()) {
                case R.id.textPaid:
                    onActionPerformed.onActionPerformed(payment.getPaymentID(), PAID);
                    break;
                case R.id.textNotPaid:
                    onActionPerformed.onActionPerformed(payment.getPaymentID(), NOT_PAID);
                    break;
            }
        }
    }
}