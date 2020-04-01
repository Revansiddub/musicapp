package com.gsatechworld.musicapp.modules.otp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.goodiebag.pinview.Pinview;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.ActivityTrainerOtpVerificationBinding;
import com.gsatechworld.musicapp.modules.login.LoginActivity;

public class TrainerOtpVerification extends AppCompatActivity {
    ActivityTrainerOtpVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_otp_verification);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_trainer_otp_verification);
        binding.pinView.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {

            }
        });

        binding.buttonSubmit.setOnClickListener(v -> {
            startActivity(new Intent(TrainerOtpVerification.this, LoginActivity.class));
        });



    }
}
