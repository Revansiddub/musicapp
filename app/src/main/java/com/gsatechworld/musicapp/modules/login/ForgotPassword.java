package com.gsatechworld.musicapp.modules.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.WindowManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityForgotPasswordBinding;

import static android.text.TextUtils.isEmpty;
import static android.util.Patterns.EMAIL_ADDRESS;
import static com.gsatechworld.musicapp.utilities.Constants.MOBILE_NUMBER_LENGTH;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class ForgotPassword extends BaseActivity {
ActivityForgotPasswordBinding passwordBinding;
public ForgotPasswordViewModel passwordViewModel;
public String emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        passwordBinding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);

        passwordViewModel=new ViewModelProvider(this).get(ForgotPasswordViewModel.class);




        passwordBinding.buttonSubmit.setOnClickListener(v -> {
            emailAddress=passwordBinding.edittextEmail.getText().toString();
            if (validateFields())
               forgotPassword();

        });


    }

    private boolean validateFields() {
        if (isEmpty(emailAddress) || !EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            passwordBinding.edittextEmail.requestFocus();
            passwordBinding.edittextEmail.setError("Please enter a valid email address");
            return false;
        }

        return true;
    }



    public void forgotPassword(){
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            passwordViewModel.forgot_password(emailAddress).observe(this,commonResponse -> {
                hideLoadingIndicator();
                if (commonResponse != null && commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    openSuccessDialog(commonResponse.getMessage());
                }
                else {
                    showErrorSnackBar(this,commonResponse.getMessage());
                }
            });

        }
    }
}