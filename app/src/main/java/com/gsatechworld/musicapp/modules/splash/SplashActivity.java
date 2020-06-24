package com.gsatechworld.musicapp.modules.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.modules.home.HomeActivity;
import com.gsatechworld.musicapp.modules.student_home.StudentHomeActivity;
import com.gsatechworld.musicapp.modules.welcome.WelcomeActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import static com.gsatechworld.musicapp.utilities.Constants.SPLASH_SCREEN_TIME;

public class SplashActivity extends BaseActivity {

    /* ------------------------------------------------------------- *
     * Overriding Base Activity
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DataBindingUtil.setContentView(this, R.layout.activity_splash);

        new Handler().postDelayed(this::openScreens, SPLASH_SCREEN_TIME);
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to check whether user is logged in or not and open screen accordingly.
     */
    private void openScreens() {
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        boolean isLogdin = sharedpreferences.getBoolean(Constants.IsTrainerLogin, false);
        boolean isLogdinStudent=sharedpreferences.getBoolean(Constants.IsStudentLogin,false);
        if(isLogdin){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
        if (isLogdinStudent){
            startActivity(new Intent(this, StudentHomeActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
    }
}