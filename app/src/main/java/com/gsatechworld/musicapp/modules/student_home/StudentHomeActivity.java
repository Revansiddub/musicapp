package com.gsatechworld.musicapp.modules.student_home;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityStudentHomeBinding;
import com.gsatechworld.musicapp.modules.student_home.student_payment.StudentPaymentFragment;
import com.gsatechworld.musicapp.modules.student_home.student_profile.StudentProfileFragment;
import com.gsatechworld.musicapp.modules.student_home.student_settings.SettingsFragment;
import com.gsatechworld.musicapp.utilities.Constants;

public class StudentHomeActivity extends BaseActivity  {
    public String student_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ActivityStudentHomeBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_student_home);

        SharedPreferences preferences = getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(preferences.getBoolean(Constants.IsTrainerLogin, false)){
            editor.putBoolean(Constants.IsStudentLogin, false);
        } else if (preferences.getBoolean(Constants.IsStudentLogin, false)){
            editor.putBoolean(Constants.IsTrainerLogin, false);
        }
        student_id = preferences.getString(Constants.STUDENT_ID,null);

        openFragment(new StudentHomeFragment(), Constants.STUDENT_HOME_FRAGMENT_TAG);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigationHome:
                        openFragment(new StudentHomeFragment(), Constants.STUDENT_HOME_FRAGMENT_TAG);
                        break;
                    case R.id.navigationPayment:
                        openFragment(new StudentPaymentFragment(), Constants.STUDENT_PAYMENT_FRAGMENT_TAG);
                        break;
                    case R.id.navigationProfile:
                        openFragment(new StudentProfileFragment(), Constants.STUDENT_PROFILE_FRAGMENT_TAG);
                        break;
                    case R.id.navigationSettings:
                        openFragment(new SettingsFragment(),Constants.STUDENT_PROFILE_SETTINGS_TAG);
                        break;
                }
                return false;
            }
        });




    }

    private void openFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layoutStudentFragment, fragment, tag);
        transaction.commit();
    }



}
