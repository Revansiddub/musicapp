package com.gsatechworld.musicapp.modules.welcome;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityWelcomeBinding;

import in.aabhasjindal.otptextview.OTPListener;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

public class WelcomeActivity extends BaseActivity implements OnItemSelectedListener,
        OTPListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityWelcomeBinding binding;
    private String[] usersArray;
    private String pinCode, userType;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle(getString(R.string.app_name));

        initialiseSpinner();

        /*Setting listeners to the views*/
        binding.spinnerUserType.setOnItemSelectedListener(this);
        binding.otpTextView.requestFocusOTP();
        binding.otpTextView.setOtpListener(this);
    }

    /* ------------------------------------------------------------- *
     * Overriding OnItemSelectedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != -1) {
            userType = usersArray[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /* ------------------------------------------------------------- *
     * Overriding OTPListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onOTPComplete(String pinCode) {
        this.pinCode = pinCode;
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to initialise and set an adapter to Spinner.
     */
    private void initialiseSpinner() {
        usersArray = getResources().getStringArray(R.array.usersArray);

        ArrayAdapter<String> daysAdapter = new ArrayAdapter<>(this, simple_spinner_item,
                usersArray);
        daysAdapter.setDropDownViewResource(simple_spinner_dropdown_item);

        binding.spinnerUserType.setAdapter(daysAdapter);
    }
}