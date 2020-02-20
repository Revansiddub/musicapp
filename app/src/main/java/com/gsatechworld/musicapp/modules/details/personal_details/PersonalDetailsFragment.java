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
import static com.gsatechworld.musicapp.utilities.Constants.ADDRESS_PROOF_BACK;
import static com.gsatechworld.musicapp.utilities.Constants.ADDRESS_PROOF_FRONT;
import static com.gsatechworld.musicapp.utilities.Constants.EXPERTISE_DOCUMENT;
import static com.gsatechworld.musicapp.utilities.Constants.FEMALE;
import static com.gsatechworld.musicapp.utilities.Constants.GOVT_ID_BACK;
import static com.gsatechworld.musicapp.utilities.Constants.GOVT_ID_FRONT;
import static com.gsatechworld.musicapp.utilities.Constants.HIGHEST_DEGREE;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static java.util.Objects.requireNonNull;

public class PersonalDetailsFragment extends Fragment implements View.OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentPersonalDetailsBinding binding;
    private String gender, uploadType;

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
        binding.imageHighestDegree.setOnClickListener(this);
        binding.imageGovtIDFront.setOnClickListener(this);
        binding.imageGovtIDBack.setOnClickListener(this);
        binding.imageAddressProofFront.setOnClickListener(this);
        binding.imageAddressProofBack.setOnClickListener(this);
        binding.imageExpertiseDocument.setOnClickListener(this);

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
                binding.textMale.setTextColor(getResources().getColor(R.color.colorAccent));
                binding.textMale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorAccent));
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
                binding.textFemale.setTextColor(getResources().getColor(R.color.colorAccent));
                binding.textFemale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorAccent));
                binding.textFemale.setBackground(requireNonNull(getActivity())
                        .getDrawable(R.drawable.button_rectangle_selected));

                binding.textMale.setTextColor(getResources().getColor(R.color.md_grey_500));
                binding.textMale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.md_grey_500));
                binding.textMale.setBackground(requireNonNull(getActivity())
                        .getDrawable(R.drawable.button_rectangle_unselected));

                gender = FEMALE;
                break;
            case R.id.imageHighestDegree:
                uploadType = HIGHEST_DEGREE;
                break;
            case R.id.imageGovtIDFront:
                uploadType = GOVT_ID_FRONT;
                break;
            case R.id.imageGovtIDBack:
                uploadType = GOVT_ID_BACK;
                break;
            case R.id.imageAddressProofFront:
                uploadType = ADDRESS_PROOF_FRONT;
                break;
            case R.id.imageAddressProofBack:
                uploadType = ADDRESS_PROOF_BACK;
                break;
            case R.id.imageExpertiseDocument:
                uploadType = EXPERTISE_DOCUMENT;
                break;
        }
    }
}