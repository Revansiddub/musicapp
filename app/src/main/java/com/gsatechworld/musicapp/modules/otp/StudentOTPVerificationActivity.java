package com.gsatechworld.musicapp.modules.otp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.core.manager.SessionManager;
import com.gsatechworld.musicapp.databinding.ActivityStudentOtpverificationBinding;
import com.gsatechworld.musicapp.modules.otp.pojo.TrainerOTPViewModel;
import com.gsatechworld.musicapp.modules.otp.student.StudentOTPRequest;
import com.gsatechworld.musicapp.modules.otp.student.StudentOTPViewModel;
import com.gsatechworld.musicapp.modules.student_home.StudentHomeActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_FAILED;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_PINCODE;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

public class StudentOTPVerificationActivity extends BaseActivity {
public ActivityStudentOtpverificationBinding otpverificationBinding;
public StudentOTPViewModel viewModel;
public String mobile_number;
public String otp;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        otpverificationBinding= DataBindingUtil.setContentView(this,R.layout.activity_student_otpverification);
        sessionManager = new SessionManager(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MyPREFERENCES, MODE_PRIVATE);
        mobile_number=sharedPreferences.getString(Constants.MOBILE_NUMBER,null);

        viewModel= new ViewModelProvider(this).get(StudentOTPViewModel.class);

        otpverificationBinding.etOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otpverificationBinding.etOtp2.requestFocus();
                }
            }
        });

        otpverificationBinding.etOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    otpverificationBinding.etOtp3.requestFocus();
                } else {
                    otpverificationBinding.etOtp1.requestFocus();
                }
            }
        });

        otpverificationBinding.etOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    otpverificationBinding.etOtp4.requestFocus();
                } else {
                    otpverificationBinding.etOtp2.requestFocus();
                }
            }
        });

        otpverificationBinding.etOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    otpverificationBinding.etOtp3.requestFocus();
                }
            }
        });



        otpverificationBinding.buttonSubmit.setOnClickListener(v -> {
            otp = otpverificationBinding.etOtp1.getText().toString() + otpverificationBinding.etOtp2.getText().toString() + otpverificationBinding.etOtp3.getText().toString()
                    + otpverificationBinding.etOtp4.getText().toString();

            verifyStudentOTP(otp);


        });








    }

    public void verifyStudentOTP(String OTP){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.verifyStudentOTP(new StudentOTPRequest(mobile_number,OTP, sessionManager.getFcmToken())).observe(this,studentOTPResponse -> {
                hideLoadingIndicator();
                if (studentOTPResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    openSuccessDialog("OTP Verification successfully Completed.");
                    String student_id=studentOTPResponse.getStudent_id();
                    String pincode_id = studentOTPResponse.getPincode_id();
                    String pincode = studentOTPResponse.getPincode();
                    SharedPreferences preferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(STUDENT_ID, student_id);
                    editor.putString(STUDENT_PINCODE,pincode);
                    editor.putString(STUDENT_PINCODE_ID,pincode_id);
                    editor.putBoolean(Constants.IsStudentLogin, true);
                    editor.putBoolean(Constants.IsTrainerLogin, false);
                    editor.commit();
                    Intent intent = new Intent(this, StudentHomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
            });
        }
    }
}
