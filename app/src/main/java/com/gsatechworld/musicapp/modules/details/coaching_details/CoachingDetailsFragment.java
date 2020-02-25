package com.gsatechworld.musicapp.modules.details.coaching_details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentCoachingDetailsBinding;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.DaysAdapter;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.DaysAdapter.OnDaySelectedListener;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.BIWEEKLY;
import static com.gsatechworld.musicapp.utilities.Constants.WEEKLY;
import static java.util.Objects.requireNonNull;

public class CoachingDetailsFragment extends Fragment implements OnClickListener,
        OnDaySelectedListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentCoachingDetailsBinding binding;
    private BaseActivity baseActivity;
    private boolean isHomeSelected, isInstituteSelected, isDailySelected, isBiweeklySelected,
            isWeeklySelected;
    private String address, charges;
    private Dialog dialog;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_coaching_details, container, false);

        baseActivity = (BaseActivity) getActivity();

        /*Setting listeners to the views*/
        binding.textHome.setOnClickListener(this);
        binding.textInstitute.setOnClickListener(this);
        binding.textDaily.setOnClickListener(this);
        binding.textBiweekly.setOnClickListener(this);
        binding.textWeekly.setOnClickListener(this);
        binding.buttonNext.setOnClickListener(this);

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
                    binding.textHome.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isHomeSelected = true;

                    binding.textHome.setTextColor(getResources().getColor(R.color.colorAccent));
                    binding.textHome.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorAccent));
                    binding.textHome.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textInstitute:
                if (isInstituteSelected) {
                    isInstituteSelected = false;

                    binding.textInstitute.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.md_grey_500));
                    binding.textInstitute.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));

                    binding.editAddress.setVisibility(GONE);
                } else {
                    isInstituteSelected = true;

                    binding.textInstitute.setTextColor(getResources().getColor(R.color.colorAccent));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorAccent));
                    binding.textInstitute.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_selected));

                    binding.editAddress.setVisibility(VISIBLE);
                }
                break;
            case R.id.textDaily:
                if (isDailySelected) {
                    isDailySelected = false;

                    binding.textDaily.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textDaily.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isDailySelected = true;
                    isBiweeklySelected = false;
                    isWeeklySelected = false;

                    binding.textDaily.setTextColor(getResources().getColor(R.color.colorAccent));
                    binding.textDaily.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_selected));

                    binding.textBiweekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textBiweekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));

                    binding.textWeekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textWeekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                }
                break;
            case R.id.textBiweekly:
                if (isBiweeklySelected) {
                    isBiweeklySelected = false;

                    binding.textBiweekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textBiweekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else
                    openSelectDaysDialog(BIWEEKLY);
                break;
            case R.id.textWeekly:
                if (isWeeklySelected) {
                    isWeeklySelected = false;

                    binding.textWeekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textWeekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else
                    openSelectDaysDialog(WEEKLY);
                break;
            case R.id.buttonNext:
                if (validateFields())
                    returnCoachingDetails();
                break;
        }
    }

    /* ------------------------------------------------------------- *
     * Overriding OnDaySelectedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onDaySelected(String selectedDay, String recurrenceType) {

        if (dialog.isShowing())
            dialog.cancel();

        if (recurrenceType.equals(BIWEEKLY)) {
            isBiweeklySelected = true;
            isDailySelected = false;

            binding.textBiweekly.setTextColor(getResources().getColor(R.color.colorAccent));
            binding.textBiweekly.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_selected));

            binding.textDaily.setTextColor(getResources().getColor(R.color.md_grey_500));
            binding.textDaily.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_unselected));
        } else {
            isWeeklySelected = true;
            isDailySelected = false;

            binding.textWeekly.setTextColor(getResources().getColor(R.color.colorAccent));
            binding.textWeekly.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_selected));

            binding.textDaily.setTextColor(getResources().getColor(R.color.md_grey_500));
            binding.textDaily.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_unselected));
        }
    }

    /* ------------------------------------------------------------- *
     * Private Method
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to return user's coaching details to the details activity.
     */
    private void returnCoachingDetails() {
        CoachingDetailsListener coachingDetailsListener = (CoachingDetailsListener) getActivity();
        requireNonNull(coachingDetailsListener).coachingDetails(new CoachingDetails(isHomeSelected,
                isInstituteSelected, address, charges, isDailySelected, isBiweeklySelected,
                isWeeklySelected));
    }

    /**
     * This method is invoked to open a dialog box where user can select coaching days.
     *
     * @param recurrenceType recurrence type user has selected.
     */
    private void openSelectDaysDialog(String recurrenceType) {
        dialog = new Dialog(requireNonNull(getActivity()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_selected_days);

        /*Initialising Views*/
        ImageView imageClose = dialog.findViewById(R.id.imageClose);
        RecyclerView recyclerDays = dialog.findViewById(R.id.recyclerDays);

        /*Setting listeners to the views*/
        imageClose.setOnClickListener(v -> dialog.cancel());

        List<String> daysList = new ArrayList<>();

        if (recurrenceType.equals(BIWEEKLY)) {
            daysList.add("Monday, Thursday");
            daysList.add("Tuesday, Friday");
            daysList.add("Wednesday, Saturday");
        } else {
            daysList.add("Monday");
            daysList.add("Tuesday");
            daysList.add("Wednesday");
            daysList.add("Thursday");
            daysList.add("Friday");
            daysList.add("Saturday");
            daysList.add("Sunday");
        }

        recyclerDays.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL,
                false));
        recyclerDays.setAdapter(new DaysAdapter(getActivity(), daysList, recurrenceType,
                this));

        dialog.show();
    }

    /**
     * This method is invoked to validate all fields present in this screen.
     *
     * @return validation status of all edit fields.
     */
    private boolean validateFields() {
        charges = requireNonNull(binding.editCharges.getText()).toString().trim();
        address = requireNonNull(binding.editAddress.getText()).toString().trim();

        if (!isHomeSelected && !isInstituteSelected) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please select Coaching Type");
            return false;
        }

        if (isEmpty(charges)) {
            binding.editCharges.setError("Please enter charges");
            return false;
        }

        if (isEmpty(address) && isInstituteSelected) {
            binding.editAddress.setError("Please enter institute address");
            return false;
        }

        if (!isDailySelected && !isBiweeklySelected && !isWeeklySelected) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please select Recurrence Type");
            return false;
        }

        return true;
    }

    /* ------------------------------------------------------------- *
     * Public Interface
     * ------------------------------------------------------------- */

    public interface CoachingDetailsListener {
        void coachingDetails(CoachingDetails coachingDetails);
    }
}