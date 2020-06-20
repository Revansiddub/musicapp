package com.gsatechworld.musicapp.modules.student_home.student_payment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutApproveStudentBinding;
import com.gsatechworld.musicapp.databinding.LayoutEarningBinding;
import com.gsatechworld.musicapp.databinding.LayoutStudentPaymentsBinding;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPayment;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class StudentPaymentAdapter extends RecyclerView.Adapter<StudentPaymentAdapter.PaymentViewHolder> {
    public Context context;
    public List<StudentPayment> paymentList;
    public onStudentPaymentListener listener;

    public StudentPaymentAdapter(Context context, List<StudentPayment> paymentList) {
        this.context = context;
        this.paymentList = paymentList;
    }


    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutStudentPaymentsBinding binding= inflate(from(context), R.layout.layout_student_payments, parent,
                false);
        return new PaymentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
    StudentPayment payment=paymentList.get(position);
    holder.binding.setStudentpayment(payment);
    holder.binding.buttonPay.setOnClickListener(v -> {
     listener.onActionPerformed();
    });

    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {

        private final LayoutStudentPaymentsBinding binding;
        public PaymentViewHolder(@NonNull LayoutStudentPaymentsBinding paymentsBinding) {
            super(paymentsBinding.getRoot());
            this.binding=paymentsBinding;
        }
    }

    public interface onStudentPaymentListener{
        void onActionPerformed();
    }

    public void setActionListener(onStudentPaymentListener listener){
        this.listener=listener;
    }
}
