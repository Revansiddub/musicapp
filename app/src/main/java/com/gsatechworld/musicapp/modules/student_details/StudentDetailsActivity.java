package com.gsatechworld.musicapp.modules.student_details;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityStudentDetailsBinding;
import com.gsatechworld.musicapp.modules.student_details.pojo.StudentDetailsInfo;

import static android.os.Build.VERSION_CODES.M;
import static android.text.TextUtils.isEmpty;
import static com.gsatechworld.musicapp.utilities.Constants.FEMALE;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static com.gsatechworld.musicapp.utilities.Constants.MOBILE_NUMBER_LENGTH;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class StudentDetailsActivity extends BaseActivity implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityStudentDetailsBinding binding;
    private StudentDetailsViewModel viewModel;
    private String gender, fullName, age, standard, schoolName, mobileNumber, address;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_details);

        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(StudentDetailsViewModel.class);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Enter Details");
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /*Setting listeners to the views*/
        binding.textMale.setOnClickListener(this);
        binding.textFemale.setOnClickListener(this);
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

    @RequiresApi(api = M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textMale:
                binding.textMale.setTextColor(getResources().getColor(R.color.colorAccent));
                binding.textMale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorAccent));
                binding.textMale.setBackground(getDrawable(R.drawable.button_rectangle_selected));

                binding.textFemale.setTextColor(getResources().getColor(R.color.md_grey_500));
                binding.textFemale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.md_grey_500));
                binding.textFemale.setBackground(getDrawable(R.drawable.button_rectangle_unselected));

                gender = MALE;
                break;
            case R.id.textFemale:
                binding.textFemale.setTextColor(getResources().getColor(R.color.colorAccent));
                binding.textFemale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.colorAccent));
                binding.textFemale.setBackground(getDrawable(R.drawable.button_rectangle_selected));

                binding.textMale.setTextColor(getResources().getColor(R.color.md_grey_500));
                binding.textMale.setCompoundDrawableTintList
                        (getResources().getColorStateList(R.color.md_grey_500));
                binding.textMale.setBackground(getDrawable(R.drawable.button_rectangle_unselected));

                gender = FEMALE;
                break;
            case R.id.buttonSubmit:
                if (validateFields())
                    onBoardStudent();
                break;
        }
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to store student details in server.
     */
    private void onBoardStudent() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.onBoardStudent(new StudentDetailsInfo(fullName, age, gender, standard,
                    schoolName, mobileNumber, address)).observe(this, commonResponse -> {
                hideLoadingIndicator();

                if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS))
                    openSuccessDialog("Your details have been submitted successfully.");
            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }

    /**
     * This method is invoked to validate all fields present in this screen.
     *
     * @return validation status of all edit fields.
     */
    private boolean validateFields() {
        fullName = requireNonNull(binding.editFullName.getText()).toString();
        age = requireNonNull(binding.editAge.getText()).toString();
        standard = requireNonNull(binding.editStandard.getText()).toString();
        schoolName = requireNonNull(binding.editSchoolName.getText()).toString();
        mobileNumber = requireNonNull(binding.editMobileNumber.getText()).toString();
        address = requireNonNull(binding.editAddress.getText()).toString();

        if (isEmpty(fullName)) {
            binding.editFullName.requestFocus();
            binding.editFullName.setError("Please enter your full name");
            return false;
        }

        if (isEmpty(age)) {
            binding.editAge.requestFocus();
            binding.editAge.setError("Please enter your age");
            return false;
        }

        if (gender == null) {
            showSnackBar(this, "Please select gender");
            return false;
        }

        if (isEmpty(standard)) {
            binding.editStandard.requestFocus();
            binding.editStandard.setError("Please enter your standard");
            return false;
        }

        if (isEmpty(schoolName)) {
            binding.editSchoolName.requestFocus();
            binding.editSchoolName.setError("Please enter your school name");
            return false;
        }

        if (isEmpty(mobileNumber) || mobileNumber.length() != MOBILE_NUMBER_LENGTH) {
            binding.editMobileNumber.requestFocus();
            binding.editMobileNumber.setError("Please enter a valid 10 digit mobile number");
            return false;
        }

        if (isEmpty(address)) {
            binding.editAddress.requestFocus();
            binding.editAddress.setError("Please enter your postal address");
            return false;
        }

        return true;
    }
}