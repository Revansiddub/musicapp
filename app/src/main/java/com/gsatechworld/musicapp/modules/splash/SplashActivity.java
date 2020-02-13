package com.gsatechworld.musicapp.modules.splash;

import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;

import static com.gsatechworld.musicapp.utilities.Constants.SPLASH_SCREEN_TIME;

public class SplashActivity extends BaseActivity {

    /* ------------------------------------------------------------- *
     * Overriding Base Activity
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
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
    }
}