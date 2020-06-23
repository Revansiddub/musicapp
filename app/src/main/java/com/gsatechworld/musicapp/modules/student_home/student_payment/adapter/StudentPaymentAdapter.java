package com.gsatechworld.musicapp.modules.student_home.student_payment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutApproveStudentBinding;
import com.gsatechworld.musicapp.databinding.LayoutEarningBinding;
import com.gsatechworld.musicapp.databinding.LayoutStudentPaymentsBinding;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPayment;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentResponse;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class StudentPaymentAdapter extends RecyclerView.Adapter<StudentPaymentAdapter.PaymentViewHolder> {
    public Context context;
    public List<StudentPaymentResponse.Pending_payments> paymentList;
    public onStudentPaymentListener listener;
    public View view;
    public String entrollment_id;

    public StudentPaymentAdapter(Context context, List<StudentPaymentResponse.Pending_payments> paymentList,onStudentPaymentListener listener) {
        this.context = context;
        this.paymentList = paymentList;
        this.listener=listener;
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
    StudentPaymentResponse.Pending_payments payment=paymentList.get(position);
    holder.binding.setStudentpayment(payment);
    holder.binding.buttonPay.setOnClickListener(v -> {
        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Constants.ENTROLLMENT_ID,payment.getEnrollment_id());
        editor.commit();
        entrollment_id=payment.getEnrollment_id();
        listener.onActionPerformed(v,entrollment_id);
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
        void onActionPerformed(View v,String entrollment_id);
    }

    public void setActionListener(onStudentPaymentListener listener){
        this.listener=listener;
    }
}
