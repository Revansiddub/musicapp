package com.gsatechworld.musicapp.modules.student_home.student_payment.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.gsatechworld.musicapp.R;

public class MyDialogFragment extends DialogFragment {

    private Context context;
    public EditText editText;
    public Button button_submit,button_cancel;
    public String amount;
    public onStudentPaymentListener listener;


//    public MyDialogFragment(Context context) {
//        this.context = context;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_payment, new LinearLayout(getActivity()), false);
        editText=view.findViewById(R.id.edt_amount);
        button_submit=view.findViewById(R.id.button_submit);
        button_cancel=view.findViewById(R.id.button_cancel);
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        button_cancel.setOnClickListener(v -> {
            builder.dismiss();
        });
        button_submit.setOnClickListener(v -> {
            amount=editText.getText().toString();
            listener.onActionListener(amount);
            builder.dismiss();
        });
        builder.setContentView(view);
        return builder;

    }

    public interface onStudentPaymentListener{
        void onActionListener(String amount);
    }

    public void setActionListener(onStudentPaymentListener listener){
        this.listener=listener;
    }


}