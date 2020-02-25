package com.gsatechworld.musicapp.modules.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityDetailsBinding;
import com.gsatechworld.musicapp.modules.details.adapter.ViewPagerAdapter;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment.CoachingDetailsListener;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.details.personal_details.PersonalDetailsFragment;
import com.gsatechworld.musicapp.modules.details.personal_details.PersonalDetailsFragment.PersonalDetailsListener;
import com.gsatechworld.musicapp.modules.details.personal_details.pojo.PersonalDetails;
import com.gsatechworld.musicapp.modules.details.pojo.TrainerDetails;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class DetailsActivity extends BaseActivity implements OnClickListener,
        CoachingDetailsListener, PersonalDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityDetailsBinding binding;
    private DetailsViewModel viewModel;
    private String categoryID, pinCode;
    private CoachingDetails coachingDetails;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Enter Details");
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (getIntent().getStringExtra(PIN_CODE) != null) {
            pinCode = getIntent().getStringExtra(PIN_CODE);
            categoryID = getIntent().getStringExtra(CATEGORY_ID);
        }

        /*Setting Adapter to view pager*/
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CoachingDetailsFragment(), getString(R.string.coaching_details));
        adapter.addFrag(new PersonalDetailsFragment(), getString(R.string.personal_details));
        binding.viewPager.setAdapter(adapter);

        /*Setting listeners to the views*/
        binding.imageBack.setOnClickListener(this);
        binding.viewPager.setOnTouchListener((v, event) -> true);
    }

    /* ------------------------------------------------------------- *
     * Overriding onOptionsItemSelected Method
     * ------------------------------------------------------------- */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageBack) {
            binding.imageBack.setVisibility(INVISIBLE);
            binding.viewCoachingSelected
                    .setBackground(getDrawable(R.drawable.rectangle_titled_selected));
            binding.viewPersonalSelected
                    .setBackground(getDrawable(R.drawable.rectangle_titled_unselected));
            binding.textTitle.setText(R.string.coaching_details);
            binding.viewPager.setCurrentItem(0);
        }
    }

    /* ------------------------------------------------------------- *
     * Overriding OnCoachingDetailsListener Method
     * ------------------------------------------------------------- */

    @Override
    public void coachingDetails(CoachingDetails coachingDetails) {
        this.coachingDetails = coachingDetails;
        if (coachingDetails != null) {
            binding.imageBack.setVisibility(VISIBLE);
            binding.viewPersonalSelected
                    .setBackground(getDrawable(R.drawable.rectangle_titled_selected));
            binding.textTitle.setText(R.string.personal_details);
            binding.viewPager.setCurrentItem(1);
        }
    }

    /* ------------------------------------------------------------- *
     * Overriding PersonalDetailsListener Method
     * ------------------------------------------------------------- */

    @Override
    public void personalDetails(PersonalDetails personalDetails) {
        if (personalDetails != null) {
            if (getNetworkInstance(this).isConnectedToInternet()) {
                showLoadingIndicator();

                viewModel.submitTrainerDetails(new TrainerDetails(coachingDetails, personalDetails))
                        .observe(this, commonResponse -> {
                            hideLoadingIndicator();

                            if (commonResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS))
                                openSuccessDialog("Your documents have been submitted successfully.");
                            else
                                showSnackBar(this, commonResponse.getMessage());
                        });
            } else
                showSnackBar(this, getString(R.string.no_internet_message));
        }
    }
}