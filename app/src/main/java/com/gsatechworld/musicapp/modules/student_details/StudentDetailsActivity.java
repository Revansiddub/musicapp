package com.gsatechworld.musicapp.modules.student_details;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityStudentDetailsBinding;
import com.gsatechworld.musicapp.modules.details.pojo.Slot_details;
import com.gsatechworld.musicapp.modules.otp.StudentOTPVerificationActivity;
import com.gsatechworld.musicapp.modules.student_details.pojo.OnboardingRequest;
import com.gsatechworld.musicapp.modules.student_details.pojo.StudentDetailsInfo;
import com.gsatechworld.musicapp.modules.student_home.StudentHomeActivity;
import com.gsatechworld.musicapp.modules.welcome.WelcomeActivity;
import com.gsatechworld.musicapp.utilities.Constants;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Intent.ACTION_GET_CONTENT;
import static android.content.Intent.createChooser;
import static android.os.Build.VERSION_CODES.M;
import static android.provider.MediaStore.Images.Media.getBitmap;
import static android.text.TextUtils.isEmpty;
import static android.view.View.VISIBLE;
import static com.bumptech.glide.Glide.with;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.END_TIME;
import static com.gsatechworld.musicapp.utilities.Constants.FEMALE;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static com.gsatechworld.musicapp.utilities.Constants.MOBILE_NUMBER_LENGTH;
import static com.gsatechworld.musicapp.utilities.Constants.OPEN_GALLERY_REQUEST_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.PINCODE_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.PROFILE_IMAGE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.START_TIME;
import static com.gsatechworld.musicapp.utilities.Constants.STEP_ONE_COMPLETE;
import static com.gsatechworld.musicapp.utilities.Constants.STEP_TWO_COMPLETE;
import static com.gsatechworld.musicapp.utilities.Constants.SUBCATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static com.karumi.dexter.Dexter.withActivity;
import static java.util.Objects.requireNonNull;

public class StudentDetailsActivity extends BaseActivity implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityStudentDetailsBinding binding;
    private StudentDetailsViewModel viewModel;
    private String gender, fullName, age, standard, schoolName, mobileNumber, address;
    private Bitmap profileImageBitmap;
    private String uploadType;
    private String profileImage;
    public String start_time,end_time;
    public String pincode_id,category_id,sub_category_id,trainerID;
    public ArrayList<OnboardingRequest.Slots_Details> timeSlotes;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_details);

        trainerID=getIntent().getStringExtra(TRAINER_ID);
        start_time=getIntent().getStringExtra(START_TIME);
        end_time=getIntent().getStringExtra(END_TIME);
        timeSlotes = new ArrayList<>();
        timeSlotes.add(new OnboardingRequest.Slots_Details(start_time,end_time));



        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        pincode_id=String.valueOf(sharedPreferences.getInt(PINCODE_ID,0));
        category_id=sharedPreferences.getString(CATEGORY_ID,null);
        sub_category_id=sharedPreferences.getString(SUBCATEGORY_ID,null);

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
        binding.imageProfiles.setOnClickListener(this);
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

            case R.id.image_profiles:
              //  Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
                withActivity(this).withPermissions(WRITE_EXTERNAL_STORAGE,CAMERA).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()){
                            uploadType=PROFILE_IMAGE;
                            openGallery();

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

                break;
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

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(ACTION_GET_CONTENT);
        startActivityForResult(createChooser(intent, "Select Picture"), OPEN_GALLERY_REQUEST_CODE);
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to store student details in server.
     */


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


        if(profileImageBitmap == null){
            showErrorSnackBar(this,"Please Upload Your Profile Picture");
            return false;
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == OPEN_GALLERY_REQUEST_CODE) {
                if (requireNonNull(data).getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < requireNonNull(mClipData).getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        try {
                            Bitmap images = getBitmap(requireNonNull(this)
                                    .getContentResolver(), uri);
                            placeImage(images);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    Uri mImageUri = data.getData();
                    try {
                        Bitmap image = getBitmap(requireNonNull(this)
                                .getContentResolver(), mImageUri);
                        placeImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void placeImage(Bitmap imageBitmap) {
        switch (uploadType) {
            case PROFILE_IMAGE:
                profileImageBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageProfiles);
                binding.imageProfiles.setVisibility(VISIBLE);
                break;


        }
    }

    private void encodeDocuments() {
        Thread backgroundThread = new Thread() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = STEP_ONE_COMPLETE;
              //  handler.sendMessage(msg);

                profileImage
                        =encodeToBase64(profileImageBitmap);

                Message msg2 = Message.obtain();
                msg2.what = STEP_TWO_COMPLETE;
               // handler.sendMessage(msg2);


            }
        };
        backgroundThread.start();
    }

    private void onBoardStudent() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            profileImage
                    =encodeToBase64(profileImageBitmap);
            showLoadingIndicator();
            viewModel.onBoardStudent(new OnboardingRequest(pincode_id,category_id,sub_category_id,timeSlotes,fullName, age, gender, standard,
                    schoolName,address, mobileNumber,trainerID,profileImage)).observe(this, commonResponse -> {
                hideLoadingIndicator();
                if (commonResponse != null && commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    openSuccessDialog("Your details have been submitted successfully.");
                    startActivity(new Intent(this, WelcomeActivity.class));
                }

            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case STEP_ONE_COMPLETE:
//                    showLoadingIndicator();
//                    break;
//                case STEP_TWO_COMPLETE:
//                    hideLoadingIndicator();
//                    break;
//            }
//        }
//    };

}