package com.gsatechworld.musicapp.modules.details.personal_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentPersonalDetailsBinding;

import static android.os.Build.VERSION_CODES.M;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.FEMALE;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static java.util.Objects.requireNonNull;

public class PersonalDetailsFragment extends Fragment implements View.OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentPersonalDetailsBinding binding;
    private String gender;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_personal_details, container, false);

        /*Setting listeners to the views*/
        binding.textMale.setOnClickListener(this);
        binding.textFemale.setOnClickListener(this);

        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @RequiresApi(api = M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textMale:
                binding.textMale.setTextColor(getResources().getColor(R.color.colorPrimary));
                binding.textMale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorPrimary));
                binding.textMale.setBackground(requireNonNull(getActivity())
                        .getDrawable(R.drawable.button_rectangle_selected));

                binding.textFemale.setTextColor(getResources().getColor(R.color.md_grey_500));
                binding.textFemale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.md_grey_500));
                binding.textFemale.setBackground(requireNonNull(getActivity())
                        .getDrawable(R.drawable.button_rectangle_unselected));

                gender = MALE;
                break;
            case R.id.textFemale:
                binding.textFemale.setTextColor(getResources().getColor(R.color.colorPrimary));
                binding.textFemale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorPrimary));
                binding.textFemale.setBackground(requireNonNull(getActivity())
                        .getDrawable(R.drawable.button_rectangle_selected));

                binding.textMale.setTextColor(getResources().getColor(R.color.md_grey_500));
                binding.textMale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.md_grey_500));
                binding.textMale.setBackground(requireNonNull(getActivity())
                        .getDrawable(R.drawable.button_rectangle_unselected));

                gender = FEMALE;
                break;
        }
    }
}