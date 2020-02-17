package com.gsatechworld.musicapp.modules.details.coaching_details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentCoachingDetailsBinding;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.databinding.DataBindingUtil.inflate;
import static java.util.Objects.requireNonNull;

public class CoachingDetailsFragment extends Fragment implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentCoachingDetailsBinding binding;
    private boolean isHomeSelected, isInstituteSelected, isDailySelected, isBiweeklySelected,
            isWeeklySelected;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_coaching_details, container, false);

        /*Setting listeners to the views*/
        binding.textHome.setOnClickListener(this);
        binding.textInstitute.setOnClickListener(this);
        binding.textDaily.setOnClickListener(this);
        binding.textBiweekly.setOnClickListener(this);
        binding.textWeekly.setOnClickListener(this);

        return binding.getRoot();
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
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isHomeSelected = true;

                    binding.textHome.setTextColor(getResources().getColor(R.color.colorPrimary));
                    binding.textHome.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorPrimary));
                    binding.textHome.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_selected));
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
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_unselected));

                    binding.editAddress.setVisibility(GONE);
                    binding.editCharges.setVisibility(GONE);
                } else {
                    isInstituteSelected = true;

                    binding.textInstitute.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorPrimary));
                    binding.textInstitute.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_selected));

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
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isDailySelected = true;

                    binding.textDaily.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textDaily.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textBiweekly:
                if (isBiweeklySelected) {
                    isBiweeklySelected = false;

                    binding.textBiweekly.setTextColor
                            (getResources().getColor(R.color.md_grey_500));
                    binding.textBiweekly.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isBiweeklySelected = true;

                    binding.textBiweekly.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textBiweekly.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textWeekly:
                if (isWeeklySelected) {
                    isWeeklySelected = false;

                    binding.textWeekly.setTextColor
                            (getResources().getColor(R.color.md_grey_500));
                    binding.textWeekly.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isWeeklySelected = true;

                    binding.textWeekly.setTextColor
                            (getResources().getColor(R.color.colorPrimary));
                    binding.textWeekly.setBackground
                            (requireNonNull(getActivity()).getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
        }
    }
}