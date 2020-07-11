package com.gsatechworld.musicapp.modules.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.core.manager.SessionManager;
import com.gsatechworld.musicapp.databinding.ActivityLoginBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.home.HomeActivity;
import com.gsatechworld.musicapp.modules.home.trainer_home.TrainerHomeFragment;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.otp.StudentOTPVerificationActivity;
import com.gsatechworld.musicapp.modules.student_home.StudentHomeActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.gsatechworld.musicapp.utilities.Constants.MOBILE_NUMBER;
import static com.gsatechworld.musicapp.utilities.Constants.MOBILE_NUMBER_LENGTH;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_FAILED;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class LoginActivity extends BaseActivity implements OnClickListener, CoachingDetailsFragment.CoachingDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private String loginType, userName, password, mobileNumber;
    public String userType;
    public int  trainerId;
    public SessionManager sessionManager;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sessionManager = SessionManager.getSessionInstance(this);



        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginType = TRAINER;

        /*Setting listeners to the view*/
        binding.textTrainer.setOnClickListener(this);
        binding.textStudent.setOnClickListener(this);
        binding.buttonLogin.setOnClickListener(this);
        binding.forgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(this,ForgotPassword.class));
        });
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textTrainer:
                binding.textTrainer.setTextColor(getResources().getColor(R.color.white));
                binding.textTrainer.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.white));
                binding.textTrainer.setBackground(getDrawable(R.drawable.button_rounded));
                binding.textTrainer.setBackgroundTintList(getColorStateList(R.color.colorAccent));


                binding.textStudent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                binding.textStudent.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorPrimaryDark));
                binding.textStudent.setBackground(null);

                binding.UserName.setVisibility(VISIBLE);
                binding.Password.setVisibility(VISIBLE);
                binding.editMobileNumber.setVisibility(GONE);
                binding.forgotPassword.setVisibility(VISIBLE);

                loginType = TRAINER;
                break;
            case R.id.textStudent:
                binding.textStudent.setTextColor(getResources().getColor(R.color.white));
                binding.textStudent.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.white));
                binding.textStudent.setBackground(getDrawable(R.drawable.button_rounded));
                binding.textStudent.setBackgroundTintList(getColorStateList(R.color.colorAccent));


                binding.textTrainer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                binding.textTrainer.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorPrimaryDark));
                binding.textTrainer.setBackground(null);

                binding.UserName.setVisibility(GONE);
                binding.Password.setVisibility(GONE);
                binding.editMobileNumber.setVisibility(VISIBLE);
                binding.forgotPassword.setVisibility(GONE);
                loginType = STUDENT;
                break;
            case R.id.buttonLogin:
                if (validateFields())
                    if (loginType.equals(TRAINER)){
                        authenticateTrainer();
                    }
                    else
                        authenticateStudent();

                break;
        }
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to check entered credentials of any trainer or not.
     */
    private void authenticateTrainer() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
          showLoadingIndicator();

            viewModel.authenticateTrainer(new TrainerLoginInfo(userName, password, sessionManager.getFcmToken()))
                    .observe(this, trainerResponse -> {
                        hideLoadingIndicator();

                        if (trainerResponse != null && trainerResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)) {
                            trainerId = trainerResponse.getTrainerID();
                            SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt(Constants.TrainerId, trainerId);
                            editor.putBoolean(Constants.IsTrainerLogin, true);
                            editor.putBoolean(Constants.IsStudentLogin, false);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if (trainerResponse == null){
                            showErrorSnackBar(this,"Login Failed Check Username and Password");
                        }
//                        else
//                            showSnackBar(this, trainerResponse.getMessage());
                    });
        } else
            showErrorSnackBar(this, getString(R.string.no_internet_message));
    }

    /**
     * This method is invoked to check entered credentials of any student or not.
     */
    private void authenticateStudent() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.authenticateStudent(mobileNumber).observe(this, studentResponse -> {
                hideLoadingIndicator();

                if (studentResponse.getStatus() != null && studentResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                    SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putString(MOBILE_NUMBER, mobileNumber);
                    editor.commit();
                    startActivity(new Intent(this, StudentOTPVerificationActivity.class));
                    finish();
                } else
                    showErrorSnackBar(this, studentResponse.getMessage());
            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }


//    LiveData<StudentResponse> authenticateStudent(String mobileNumber) {
//        MutableLiveData<StudentResponse> studentMutableLiveData = new MutableLiveData<>();
//
//        studentMutableLiveData.postValue(new StudentResponse(SERVER_RESPONSE_SUCCESS,
//                "Oops! something went wrong. Please try again later.", ""));
//
//        startActivity(new Intent(this,StudentHomeActivity.class));
//
//        return studentMutableLiveData;
//    }

    /**
     * This method is invoked to validate all fields present in this screen.
     *
     * @return validation status of all edit fields.
     */
    private boolean validateFields() {
        if (loginType.equals(TRAINER)) {
            userName = requireNonNull(binding.editUserName.getText()).toString();
            password = requireNonNull(binding.editPassword.getText()).toString();

            if (isEmpty(userName)) {
                binding.editUserName.setError("Please enter username");
                return false;
            }

            if (isEmpty(password)) {
                binding.editPassword.setError("Please enter password");
                return false;
            }
        } else {
            mobileNumber = requireNonNull(binding.editMobileNumber.getText()).toString();

            if (isEmpty(mobileNumber) || mobileNumber.length() != MOBILE_NUMBER_LENGTH) {
                binding.editMobileNumber.setError("Please enter a valid 10 digit mobile number");
                return false;
            }
        }

        return true;
    }

    @Override
    public void coachingDetails(CoachingDetails coachingDetails) {
        if (coachingDetails.isDaily() == true){

        }
    }
}