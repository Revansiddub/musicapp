package com.gsatechworld.musicapp.modules.details;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.gsatechworld.musicapp.modules.details.pojo.OnBoadingTrainer;
import com.gsatechworld.musicapp.modules.details.pojo.Recurrence_types;
import com.gsatechworld.musicapp.modules.otp.TrainerOtpVerification;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.SUBCATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class DetailsActivity extends BaseActivity implements OnClickListener,
        CoachingDetailsListener, PersonalDetailsListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityDetailsBinding binding;
    private DetailsViewModel viewModel;
    private String categoryID, pinCode,subCategoryID;
    private CoachingDetails coachingDetails;
    private Recurrence_types recurrence_types;
    private ArrayList<String> coachingType;
    public int position,pincode_Id;
    public String pinCode_ID;


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
            subCategoryID=getIntent().getStringExtra(SUBCATEGORY_ID);
            pincode_Id=getIntent().getIntExtra(PINCODE_ID,0);


        }
        pinCode_ID=String.valueOf(pincode_Id);
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
        coachingType=new ArrayList<>();
        if (coachingDetails.isHome()==true){
            coachingType.add("Home");
        } else {
            coachingType.add("Institute");
        }

        recurrence_types = new Recurrence_types();
        if (coachingDetails.isDaily() == true){
           recurrence_types.setRecurrence_type("Daily");
           String [] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
           recurrence_types.setCoaching_days(days);
        }else if (coachingDetails.isWeekly() == true){
            String [] days = {"monday"};
            recurrence_types.setCoaching_days(days);
            recurrence_types.setRecurrence_type("Weekly");
        }
        else {
            String [] days = {"monday, tuesday"};
            recurrence_types.setCoaching_days(days);
            recurrence_types.setRecurrence_type("BiWeekly");
        }

         recurrence_types.setSlot_details(coachingDetails.getSlot_details());



    }

    /* ------------------------------------------------------------- *
     * Overriding PersonalDetailsListener Method
     * ------------------------------------------------------------- */

    @Override
    public void personalDetails(PersonalDetails personalDetails) {
        if (personalDetails != null) {
            if (getNetworkInstance(this).isConnectedToInternet()) {
                showLoadingIndicator();



                viewModel.submitTrainerDetails(new OnBoadingTrainer(personalDetails.getProfile_Image()
                        ,coachingDetails.getAddress(),personalDetails.getGender()
                        ,recurrence_types,personalDetails.getHighestDegreeBase()
                        ,personalDetails.getAddressProofBackBase()
                        ,personalDetails.getExpertiseDocumentBase()
                        ,pinCode_ID,personalDetails.getGovtIDFrontBase()
                        ,personalDetails.getAddressProofFrontBase()
                        ,categoryID,coachingType,subCategoryID,
                        personalDetails.getFullName(),
                        personalDetails.getGovtIDBackBase(),personalDetails.getMobileNumber()
                        ,personalDetails.getEmailAddress(),coachingDetails.getCharge()))
                        .observe(this, commonResponse -> {
                            hideLoadingIndicator();

                            if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)) {
                                openSuccessDialog("Your documents have been submitted successfully.");
                                Intent intent = new Intent(this, TrainerOtpVerification.class);
                                intent.putExtra("mobile_number", personalDetails.getMobileNumber());
                                startActivity(intent);

                            }
                            else
                                showSnackBar(this, commonResponse.getMessage());
                        });
            } else
                showSnackBar(this, getString(R.string.no_internet_message));
        }
    }
}