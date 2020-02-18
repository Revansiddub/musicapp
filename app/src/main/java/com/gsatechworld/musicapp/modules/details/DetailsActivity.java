package com.gsatechworld.musicapp.modules.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityDetailsBinding;
import com.gsatechworld.musicapp.modules.details.adapter.ViewPagerAdapter;
import com.gsatechworld.musicapp.modules.details.coaching_details.CoachingDetailsFragment;
import com.gsatechworld.musicapp.modules.details.personal_details.PersonalDetailsFragment;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static java.util.Objects.requireNonNull;

public class DetailsActivity extends BaseActivity implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityDetailsBinding binding;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Enter Details");
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /*Setting Adapter to view pager*/
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CoachingDetailsFragment(), getString(R.string.coaching_details));
        adapter.addFrag(new PersonalDetailsFragment(), getString(R.string.personal_details));
        binding.viewPager.setAdapter(adapter);

        /*Setting listeners to the views*/
        binding.buttonSubmit.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.buttonSubmit:
                if (binding.viewPager.getCurrentItem() == 0) {
                    binding.imageBack.setVisibility(VISIBLE);
                    binding.viewPersonalSelected
                            .setBackground(getDrawable(R.drawable.rectangle_titled_selected));
                    binding.textTitle.setText(R.string.personal_details);
                    binding.buttonSubmit.setText(R.string.submit);
                    binding.viewPager.setCurrentItem(1);
                } else
                    openSuccessDialog("Your documents have been submitted successfully.");
                break;
            case R.id.imageBack:
                binding.imageBack.setVisibility(INVISIBLE);
                binding.viewCoachingSelected
                        .setBackground(getDrawable(R.drawable.rectangle_titled_selected));
                binding.viewPersonalSelected
                        .setBackground(getDrawable(R.drawable.rectangle_titled_unselected));
                binding.textTitle.setText(R.string.coaching_details);
                binding.buttonSubmit.setText(R.string.next);
                binding.viewPager.setCurrentItem(0);
                break;
        }
    }
}