package com.gsatechworld.musicapp.modules.otp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityTrainerOtpVerificationBinding;
import com.gsatechworld.musicapp.modules.login.LoginActivity;
import com.gsatechworld.musicapp.modules.otp.pojo.TrainerOTPVerification;
import com.gsatechworld.musicapp.modules.otp.pojo.TrainerOTPViewModel;
import com.gsatechworld.musicapp.modules.welcome.WelcomeViewModel;
import com.gsatechworld.musicapp.utilities.Constants;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;

public class TrainerOtpVerification extends BaseActivity {
    ActivityTrainerOtpVerificationBinding binding;
    public TrainerOTPViewModel otpViewModel;
    public String phone;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_otp_verification);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_trainer_otp_verification);

        otpViewModel=new ViewModelProvider(this).get(TrainerOTPViewModel.class);

        phone=getIntent().getStringExtra("mobile_number");


        binding.etOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    binding.etOtp2.requestFocus();
                }
            }
        });

        binding.etOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    binding.etOtp3.requestFocus();
                } else {
                    binding.etOtp1.requestFocus();
                }
            }
        });

        binding.etOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    binding.etOtp4.requestFocus();
                } else {
                    binding.etOtp2.requestFocus();
                }
            }
        });

        binding.etOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    binding.etOtp3.requestFocus();
                }
            }
        });



        binding.buttonSubmit.setOnClickListener(v -> {
            otp = binding.etOtp1.getText().toString() + binding.etOtp2.getText().toString() + binding.etOtp3.getText().toString()
                    + binding.etOtp4.getText().toString();
            verifyTrainerOTP(otp);

        });



    }

    public void verifyTrainerOTP(String otp){
     otpViewModel.verifyTrainerOTP(new TrainerOTPVerification(phone,otp)).observe(this,commonResponse -> {
         if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
             openSuccessDialog("OTP Verification successfully Completed.");
             startActivity(new Intent(TrainerOtpVerification.this, LoginActivity.class));
         }

     });
    }
}
