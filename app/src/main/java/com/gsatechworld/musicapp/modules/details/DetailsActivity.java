package com.gsatechworld.musicapp.modules.details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityDetailsBinding;

import static android.graphics.Color.TRANSPARENT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.Window.FEATURE_NO_TITLE;
import static java.util.Objects.requireNonNull;

public class DetailsActivity extends BaseActivity implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityDetailsBinding binding;
    private boolean isHomeSelected, isInstituteSelected, isDailySelected, isBiweeklySelected,
            isWeeklySelected;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Enter Details");
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /*Setting listeners to the views*/
        binding.textHome.setOnClickListener(this);
        binding.textInstitute.setOnClickListener(this);
        binding.textDaily.setOnClickListener(this);
        binding.textBiweekly.setOnClickListener(this);
        binding.textWeekly.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);
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

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textHome:
                if (isHomeSelected) {
                    isHomeSelected = false;

                    binding.textHome.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textHome.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.md_grey_500));
                    binding.textHome.setBackground
                            (getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isHomeSelected = true;

                    binding.textHome.setTextColor(getResources().getColor(R.color.colorPrimary));
                    binding.textHome.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorPrimary));
                    binding.textHome.setBackground
                            (getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textInstitute:
                if (isInstituteSelected) {
                    isInstituteSelected = false;

                    binding.textInstitute.setTextColor
                            (getResources().getColor(R.color.md_grey_500));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.md_grey_500));
                    binding.textInstitute.setBackground
                            (getDrawable(R.drawable.button_rectangle_unselected));

                    binding.editAddress.setVisibility(GONE);
                    binding.editCharges.setVisibility(GONE);
                } else {
                    isInstituteSelected = true;

                    binding.textInstitute.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorPrimary));
                    binding.textInstitute.setBackground
                            (getDrawable(R.drawable.button_rectangle_selected));

                    binding.editAddress.setVisibility(VISIBLE);
                    binding.editCharges.setVisibility(VISIBLE);
                }
                break;
            case R.id.textDaily:
                if (isDailySelected) {
                    isDailySelected = false;

                    binding.textDaily.setTextColor
                            (getResources().getColor(R.color.md_grey_500));
                    binding.textDaily.setBackground
                            (getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isDailySelected = true;

                    binding.textDaily.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textDaily.setBackground
                            (getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textBiweekly:
                if (isBiweeklySelected) {
                    isBiweeklySelected = false;

                    binding.textBiweekly.setTextColor
                            (getResources().getColor(R.color.md_grey_500));
                    binding.textBiweekly.setBackground
                            (getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isBiweeklySelected = true;

                    binding.textBiweekly.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textBiweekly.setBackground
                            (getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textWeekly:
                if (isWeeklySelected) {
                    isWeeklySelected = false;

                    binding.textWeekly.setTextColor
                            (getResources().getColor(R.color.md_grey_500));
                    binding.textWeekly.setBackground
                            (getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isWeeklySelected = true;

                    binding.textWeekly.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textWeekly.setBackground
                            (getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.buttonSubmit:
                openDetailsSubmittedDialog();
                break;
        }
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked when user details is successfully submitted to server.
     */
    private void openDetailsSubmittedDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(FEATURE_NO_TITLE);
        requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.setContentView(R.layout.layout_success_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        TextView textMessage = dialog.findViewById(R.id.textMessage);
        Button buttonOk = dialog.findViewById(R.id.buttonOk);

        textMessage.setText("Your documents have been submitted successfully.");

        buttonOk.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}