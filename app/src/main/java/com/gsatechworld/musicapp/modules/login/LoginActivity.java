package com.gsatechworld.musicapp.modules.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.core.manager.SessionManager;
import com.gsatechworld.musicapp.databinding.ActivityLoginBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.home.HomeActivity;
import com.gsatechworld.musicapp.modules.login.pojo.StudentResponse;
import com.gsatechworld.musicapp.modules.login.pojo.TrainerLoginInfo;
import com.gsatechworld.musicapp.modules.student_home.StudentHomeActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.List;

import de.mrapp.android.dialog.MaterialDialog;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.gsatechworld.musicapp.utilities.Constants.MOBILE_NUMBER_LENGTH;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_ID;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_PINCODE;
import static com.gsatechworld.musicapp.utilities.Constants.STUDENT_PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class LoginActivity extends BaseActivity implements OnClickListener, CoachingDetailsFragment.CoachingDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    public String userType;
    public int trainerId;
    public SessionManager sessionManager;
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private String loginType, userName, password, mobileNumber, studentpass;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
            startActivity(new Intent(this, ForgotPassword.class));
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
                binding.studentPass.setVisibility(GONE);
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
                binding.studentPass.setVisibility(VISIBLE);
                binding.forgotPassword.setVisibility(GONE);
                loginType = STUDENT;
                break;
            case R.id.buttonLogin:
                if (validateFields())
                    if (loginType.equals(TRAINER)) {
                        authenticateTrainer();
                    } else
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
                        if (trainerResponse == null) {
                            showErrorSnackBar(this, "Login Failed Check Username and Password");
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

            viewModel.authenticateStudent(mobileNumber, studentpass, FirebaseInstanceId.getInstance().getId()).observe(this, studentResponse -> {
                hideLoadingIndicator();

                if (studentResponse.getStatus() != null && studentResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {


                    if (studentResponse.getUser().size() == 1) {

                        if (studentResponse.getUser().get(0).getTrainerStatus().equals("2")) {
                            String student_id = String.valueOf(studentResponse.getUser().get(0).getStudentId());
                            String pincode_id = String.valueOf(studentResponse.getUser().get(0).getPincodeId());
                            String pincode = studentResponse.getUser().get(0).getPincode();
                            SharedPreferences preferences = getSharedPreferences(Constants.MyPREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(STUDENT_ID, student_id);
                            editor.putString(STUDENT_PINCODE, pincode);
                            editor.putString(STUDENT_PINCODE_ID, pincode_id);
                            editor.putBoolean(Constants.IsStudentLogin, true);
                            editor.putBoolean(Constants.IsTrainerLogin, false);
                            editor.commit();
                            Intent intent = new Intent(this, StudentHomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(this, "Please Wait Approval needed from trainer", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        dailogUser(studentResponse.getUser());
                    }


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
            studentpass = requireNonNull(binding.studentPass.getText()).toString();

            if (isEmpty(mobileNumber) || mobileNumber.length() != MOBILE_NUMBER_LENGTH) {
                binding.editMobileNumber.setError("Please enter a valid 10 digit mobile number");
                return false;
            }
            if (isEmpty(studentpass)) {
                binding.studentPass.setError("Please enter password");
                return false;
            }
        }

        return true;
    }

    @Override
    public void coachingDetails(CoachingDetails coachingDetails) {
        if (coachingDetails.isDaily() == true) {

        }
    }

    private void dailogUser(List<StudentResponse.User> userList) {


        MaterialDialog.Builder dialogBuilder1 = new MaterialDialog.Builder(this);
        dialogBuilder1.setView(R.layout.dailog_select_user);
        MaterialDialog dialog1 = dialogBuilder1.create();
        dialog1.show();

        //  TextView title = dialog1.findViewById(R.id.tv_title);
        RecyclerView recyclerView = dialog1.findViewById(R.id.listRecycle);
        Button btnProceed = dialog1.findViewById(R.id.btnProceed);

        recyclerView.setLayoutManager(new LinearLayoutManager(dialog1.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(dialog1.getContext()));
        AdapterUserSelection adapterBrandList = new AdapterUserSelection(userList, this);
        recyclerView.setAdapter(adapterBrandList);


        adapterBrandList.setClickListener((view, position) -> {

            if (userList.get(position).getTrainerStatus().equals("2")) {
                String student_id = String.valueOf(userList.get(position).getStudentId());
                String pincode_id = String.valueOf(userList.get(position).getPincodeId());
                String pincode = userList.get(position).getPincode();
                SharedPreferences preferences = getSharedPreferences(Constants.MyPREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(STUDENT_ID, student_id);
                editor.putString(STUDENT_PINCODE, pincode);
                editor.putString(STUDENT_PINCODE_ID, pincode_id);
                editor.putBoolean(Constants.IsStudentLogin, true);
                editor.putBoolean(Constants.IsTrainerLogin, false);
                editor.commit();
                Intent intent = new Intent(this, StudentHomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {

                Toast.makeText(this, "Please Wait Approval needed from trainer", Toast.LENGTH_SHORT).show();
            }


        });


    }

}