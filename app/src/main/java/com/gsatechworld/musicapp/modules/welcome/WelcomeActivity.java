package com.gsatechworld.musicapp.modules.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.core.manager.SessionManager;
import com.gsatechworld.musicapp.databinding.ActivityWelcomeBinding;
import com.gsatechworld.musicapp.modules.login.LoginActivity;
import com.gsatechworld.musicapp.modules.welcome.pojo.PinCodeInfo;
import com.gsatechworld.musicapp.select_category.SelectCategoriesActivity;

import in.aabhasjindal.otptextview.OTPListener;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;
import static android.view.View.VISIBLE;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.USER_TYPE;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

public class WelcomeActivity extends BaseActivity implements OnItemSelectedListener,
        OTPListener, OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityWelcomeBinding binding;
    private WelcomeViewModel viewModel;
    private String[] usersArray;
    private String pinCode, userType;
    private int pincode_id;
    public SessionManager sessionManager;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        sessionManager=SessionManager.getSessionInstance(this);

        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);

        /*Setting Screen title*/


        initialiseSpinner();

        /*Setting listeners to the views*/
        binding.spinnerUserType.setOnItemSelectedListener(this);
        binding.viewPinCode.requestFocusOTP();
        binding.viewPinCode.setOtpListener(this);
        binding.textLogin.setOnClickListener(this);


    }

    /* ------------------------------------------------------------- *
     * Overriding OnItemSelectedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != -1) {
            userType = usersArray[position];

            /*Displaying a view where user can enter his/her area pin code*/
            binding.layoutPinCode.setVisibility(VISIBLE);
            showKeyboard();
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


        hideKeyboard(this);
        checkAvailability();
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textLogin) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to initialise and set an adapter to Spinner.
     */
    private void initialiseSpinner() {
        usersArray = getResources().getStringArray(R.array.usersArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, simple_spinner_item,
                usersArray);
        adapter.setDropDownViewResource(simple_spinner_dropdown_item);

        binding.spinnerUserType.setAdapter(adapter);
    }

    /**
     * This method is invoked to check availability of service in entered area
     * based on selected user type.
     */
    private void checkAvailability() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();



            viewModel.checkPinCodeAvailability(new PinCodeInfo(pinCode))
                    .observe(this, commonResponse -> {
                        hideLoadingIndicator();

                        if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                            pincode_id=commonResponse.getPincode_id();
                            Intent intent = new Intent(this, SelectCategoriesActivity.class);
                            intent.putExtra(USER_TYPE, userType);
                            intent.putExtra(PIN_CODE, pinCode);
                            intent.putExtra(PINCODE_ID,pincode_id);
                            sessionManager.storeRegistrationData(userType,pincode_id);
                            startActivity(intent);
                        } else
                            showSnackBar(this, commonResponse.getMessage());
                    });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }
}