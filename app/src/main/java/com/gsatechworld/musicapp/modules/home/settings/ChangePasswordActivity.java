package com.gsatechworld.musicapp.modules.home.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityChangePasswordBinding;
import com.gsatechworld.musicapp.modules.home.settings.pojo.ChangePasswordRequest;
import com.gsatechworld.musicapp.modules.login.LoginViewModel;
import com.gsatechworld.musicapp.utilities.Constants;

import static android.text.TextUtils.isEmpty;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class ChangePasswordActivity extends BaseActivity {
    ActivityChangePasswordBinding changePasswordBinding;
    ChangePasswordViewModel passwordViewModel;
    public String trainerId;
    public String old_Password;
    public String new_Password,confirm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_change_password);
        changePasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_change_password);

        trainerId=getIntent().getStringExtra(Constants.TRAINER_ID);


        passwordViewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);

        changePasswordBinding.layoutBase.toolbar.setTitle("Change Password");
        setSupportActionBar(changePasswordBinding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        changePasswordBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        changePasswordBinding.buttonSubmit.setOnClickListener(v -> {
            if (validateField()){
                changeTrainerPassword();
            }

        });



    }



    public void changeTrainerPassword(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();
            passwordViewModel.changePassword(new ChangePasswordRequest(trainerId, old_Password, new_Password)).observe(this, commonResponse -> {
              hideLoadingIndicator();

              if (commonResponse.getStatus().equals("success")){
               openSuccessDialog(commonResponse.getMessage());
               finish();
              }
              else
                  showSnackBar(this, commonResponse.getMessage());
            });
        }
    }


    public boolean validateField(){
        old_Password=changePasswordBinding.edittextOldPassword.getText().toString();
        new_Password=changePasswordBinding.edittextNewPassword.getText().toString();
        confirm_password=changePasswordBinding.edittextConfirmPassword.getText().toString();

        if (isEmpty(old_Password)) {
            changePasswordBinding.edittextOldPassword.setError("Please enter your old password");
            return false;
        }
        if (isEmpty(new_Password)) {
            changePasswordBinding.edittextNewPassword.setError("Please enter your new password");
            return false;
        }

        if (isEmpty(confirm_password)) {
            changePasswordBinding.edittextConfirmPassword.setError("Please confirm your new password");
            return false;
        }
        if (!new_Password.equals(confirm_password)){
            changePasswordBinding.edittextConfirmPassword.setError("Password is not matching");
            return false;
        }

        if (new_Password.length() < 6){
            changePasswordBinding.edittextNewPassword.setError("Password too short");
            return false;
        }
        return true;
    }
}
