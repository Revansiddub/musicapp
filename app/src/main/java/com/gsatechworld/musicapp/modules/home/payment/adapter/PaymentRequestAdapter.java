package com.gsatechworld.musicapp.modules.home.payment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutEarningBinding;
import com.gsatechworld.musicapp.databinding.LayoutPaymentRequestsBinding;
import com.gsatechworld.musicapp.modules.home.earnings.adapter.EarningAdapter;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.Payment;
import com.gsatechworld.musicapp.modules.home.payment.PaymentsFragment;
import com.gsatechworld.musicapp.modules.home.payment.pojo.Payment_requests;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class PaymentRequestAdapter extends RecyclerView.Adapter<PaymentRequestAdapter.RequestViewHolder> {

    public Context context;
    public List<Payment_requests> requestsList;
    public List<AcceptPayment> acceptPaymentList;
    public String payment_request_id,trainedID;
    private OnActionPaymentPerformedListener listner;

    public PaymentRequestAdapter(Context context, List<Payment_requests> requestsList,String trainerID) {
        this.context = context;
        this.requestsList = requestsList;
        this.trainedID=trainerID;
    }




    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPaymentRequestsBinding binding=inflate(from(context), R.layout.layout_payment_requests, parent,
                false);
        return new RequestViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
     Payment_requests requests = requestsList.get(position);
     holder.binding.setRequestlists(requestsList.get(position));
     payment_request_id = requestsList.get(position).getPayment_request_id();
     holder.binding.textPaid.setOnClickListener(v -> {
         //OnActionPaymentPerformedListener onActionPerformed = (OnActionPaymentPerformedListener) context;
         listner.onActionPerformed(trainedID,payment_request_id);
     });
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public void setClickListner(OnActionPaymentPerformedListener listner) {
        this.listner = listner;
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        private final LayoutPaymentRequestsBinding binding;

        public RequestViewHolder(@NonNull LayoutPaymentRequestsBinding binding) {
            super(binding.getRoot());

            this.binding=binding;

        }
    }

    public interface OnActionPaymentPerformedListener {
        void onActionPerformed(String trainerID, String payment_request_id);
    }


}
