package com.gsatechworld.musicapp.modules.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityHomeBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.home.approval.ApprovalFragment;
import com.gsatechworld.musicapp.modules.home.earnings.EarningsFragment;
import com.gsatechworld.musicapp.modules.home.settings.SettingsFragment;
import com.gsatechworld.musicapp.modules.home.trainer_home.TrainerHomeFragment;

import static com.gsatechworld.musicapp.utilities.Constants.APPROVAL_FRAGMENT_TAG;
import static com.gsatechworld.musicapp.utilities.Constants.EARNINGS_FRAGMENT_TAG;
import static com.gsatechworld.musicapp.utilities.Constants.SETTINGS_FRAGMENT_TAG;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_HOME_FRAGMENT_TAG;

public class HomeActivity extends BaseActivity implements OnNavigationItemSelectedListener, CoachingDetailsFragment.CoachingDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityHomeBinding binding;
    CoachingDetails coachingDetails;
    String userType;
    int trainerID;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        Bundle bundle=getIntent().getExtras();
        userType=bundle.getString("type");
        trainerID=bundle.getInt("trainerId");

        /*Opening trainer's home fragment by default*/

        openFragment(new TrainerHomeFragment(), TRAINER_HOME_FRAGMENT_TAG);

        /*Setting Listeners to the views*/
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    /* ------------------------------------------------------------- *
     * Overriding OnNavigationItemSelectedListener Method
     * ------------------------------------------------------------- */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigationHome:
                openFragment(new TrainerHomeFragment(), TRAINER_HOME_FRAGMENT_TAG);
                break;
            case R.id.navigationApproval:
                openFragment(new ApprovalFragment(), APPROVAL_FRAGMENT_TAG);
                break;
            case R.id.navigationEarnings:
                openFragment(new EarningsFragment(), EARNINGS_FRAGMENT_TAG);
                break;
            case R.id.navigationSettings:
                openFragment(new SettingsFragment(), SETTINGS_FRAGMENT_TAG);
                break;
        }
        return true;
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to display a fragment in the home screen.
     *
     * @param fragment fragment that need to display.
     * @param tag      tag/name of the fragment.
     */
    private void openFragment(Fragment fragment, String tag) {
        Bundle bundle1=new Bundle();
        bundle1.putString("coaching_type",userType);
        bundle1.putInt("trainerID",trainerID);
        fragment.setArguments(bundle1);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layoutFragment, fragment, tag);
        transaction.commit();
    }

    @Override
    public void coachingDetails(CoachingDetails coachingDetails) {
        this.coachingDetails=coachingDetails;

    }
}