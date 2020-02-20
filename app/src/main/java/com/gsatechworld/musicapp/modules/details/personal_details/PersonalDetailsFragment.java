package com.gsatechworld.musicapp.modules.details.personal_details;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentPersonalDetailsBinding;
import com.gsatechworld.musicapp.modules.details.personal_details.pojo.PersonalDetails;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static android.content.Intent.ACTION_GET_CONTENT;
import static android.content.Intent.createChooser;
import static android.os.Build.VERSION_CODES.M;
import static android.provider.MediaStore.Images.Media.getBitmap;
import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.bumptech.glide.Glide.with;
import static com.gsatechworld.musicapp.utilities.Constants.ADDRESS_PROOF_BACK;
import static com.gsatechworld.musicapp.utilities.Constants.ADDRESS_PROOF_FRONT;
import static com.gsatechworld.musicapp.utilities.Constants.EXPERTISE_DOCUMENT;
import static com.gsatechworld.musicapp.utilities.Constants.FEMALE;
import static com.gsatechworld.musicapp.utilities.Constants.GOVT_ID_BACK;
import static com.gsatechworld.musicapp.utilities.Constants.GOVT_ID_FRONT;
import static com.gsatechworld.musicapp.utilities.Constants.HIGHEST_DEGREE;
import static com.gsatechworld.musicapp.utilities.Constants.MALE;
import static com.gsatechworld.musicapp.utilities.Constants.OPEN_GALLERY_REQUEST_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.STEP_ONE_COMPLETE;
import static com.gsatechworld.musicapp.utilities.Constants.STEP_TWO_COMPLETE;
import static com.karumi.dexter.Dexter.withActivity;
import static java.util.Objects.requireNonNull;

public class PersonalDetailsFragment extends Fragment implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentPersonalDetailsBinding binding;
    private BaseActivity baseActivity;
    private String fullName, gender, uploadType;
    private Bitmap highestDegreeBitmap, govtIDFrontBitmap, govtIDBackBitmap, addressProofFrontBitmap,
            addressProofBackBitmap, expertiseDocumentBitmap;
    private String highestDegreeBase, govtIDFrontBase, govtIDBackBase, addressProofFrontBase,
            addressProofBackBase, expertiseDocumentBase;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STEP_ONE_COMPLETE:
                    baseActivity.showLoadingIndicator();
                    break;
                case STEP_TWO_COMPLETE:
                    baseActivity.hideLoadingIndicator();
                    returnCoachingDetails();
                    break;
            }
        }
    };

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_personal_details, container, false);

        baseActivity = (BaseActivity) getActivity();

        /*Setting listeners to the views*/
        binding.textMale.setOnClickListener(this);
        binding.textFemale.setOnClickListener(this);
        binding.imageHighestDegree.setOnClickListener(this);
        binding.imageGovtIDFront.setOnClickListener(this);
        binding.imageGovtIDBack.setOnClickListener(this);
        binding.imageAddressProofFront.setOnClickListener(this);
        binding.imageAddressProofBack.setOnClickListener(this);
        binding.imageExpertiseDocument.setOnClickListener(this);
        binding.imageHighestDegreeClose.setOnClickListener(this);
        binding.imageGovtIDFrontClose.setOnClickListener(this);
        binding.imageGovtIDBackClose.setOnClickListener(this);
        binding.imageAddressProofFrontClose.setOnClickListener(this);
        binding.imageAddressProofBackClose.setOnClickListener(this);
        binding.imageExpertiseDocumentClose.setOnClickListener(this);
        binding.buttonSubmit.setOnClickListener(this);

        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Overriding onActivityResult Method
     * ------------------------------------------------------------- */

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == OPEN_GALLERY_REQUEST_CODE) {
                if (requireNonNull(data).getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < requireNonNull(mClipData).getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        try {
                            Bitmap images = getBitmap(requireNonNull(getActivity())
                                    .getContentResolver(), uri);
                            placeImage(images);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    Uri mImageUri = data.getData();
                    try {
                        Bitmap image = getBitmap(requireNonNull(getActivity())
                                .getContentResolver(), mImageUri);
                        placeImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
                withActivity(getActivity()).withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    uploadType = HIGHEST_DEGREE;
                                    openGallery();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                           PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.imageGovtIDFront:
                withActivity(getActivity()).withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    uploadType = GOVT_ID_FRONT;
                                    openGallery();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                           PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.imageGovtIDBack:
                withActivity(getActivity()).withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    uploadType = GOVT_ID_BACK;
                                    openGallery();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                           PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.imageAddressProofFront:
                withActivity(getActivity()).withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    uploadType = ADDRESS_PROOF_FRONT;
                                    openGallery();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                           PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.imageAddressProofBack:
                withActivity(getActivity()).withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    uploadType = ADDRESS_PROOF_BACK;
                                    openGallery();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                           PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.imageExpertiseDocument:
                withActivity(getActivity()).withPermissions(WRITE_EXTERNAL_STORAGE, CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    uploadType = EXPERTISE_DOCUMENT;
                                    openGallery();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                           PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.imageHighestDegreeClose:
                highestDegreeBitmap = null;
                with(this).load(R.drawable.icon_frame).into(binding.imageHighestDegree);
                binding.imageHighestDegreeClose.setVisibility(GONE);
                break;
            case R.id.imageGovtIDFrontClose:
                govtIDFrontBitmap = null;
                with(this).load(R.drawable.icon_frame).into(binding.imageGovtIDFront);
                binding.imageGovtIDFrontClose.setVisibility(GONE);
                break;
            case R.id.imageGovtIDBackClose:
                govtIDBackBitmap = null;
                with(this).load(R.drawable.icon_frame).into(binding.imageGovtIDBack);
                binding.imageGovtIDBackClose.setVisibility(GONE);
                break;
            case R.id.imageAddressProofFrontClose:
                addressProofFrontBitmap = null;
                with(this).load(R.drawable.icon_frame).into(binding.imageAddressProofFront);
                binding.imageAddressProofFrontClose.setVisibility(GONE);
                break;
            case R.id.imageAddressProofBackClose:
                addressProofBackBitmap = null;
                with(this).load(R.drawable.icon_frame).into(binding.imageAddressProofBack);
                binding.imageAddressProofBackClose.setVisibility(GONE);
                break;
            case R.id.imageExpertiseDocumentClose:
                expertiseDocumentBitmap = null;
                with(this).load(R.drawable.icon_frame).into(binding.imageExpertiseDocument);
                binding.imageExpertiseDocumentClose.setVisibility(GONE);
                break;
            case R.id.buttonSubmit:
                if (validateFields())
                    encodeDocuments();
                break;
        }
    }

    /* ------------------------------------------------------------- *
     * Private Method
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to open gallery.
     */
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(ACTION_GET_CONTENT);
        startActivityForResult(createChooser(intent, "Select Picture"), OPEN_GALLERY_REQUEST_CODE);
    }

    /**
     * This method is invoked to display image in the screen according to the selected uploadType.
     *
     * @param imageBitmap bitmap format of selected image.
     */
    private void placeImage(Bitmap imageBitmap) {
        switch (uploadType) {
            case HIGHEST_DEGREE:
                highestDegreeBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageHighestDegree);
                binding.imageHighestDegreeClose.setVisibility(VISIBLE);
                break;
            case GOVT_ID_FRONT:
                govtIDFrontBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageGovtIDFront);
                binding.imageGovtIDFrontClose.setVisibility(VISIBLE);
                break;
            case GOVT_ID_BACK:
                govtIDBackBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageGovtIDBack);
                binding.imageGovtIDBackClose.setVisibility(VISIBLE);
                break;
            case ADDRESS_PROOF_FRONT:
                addressProofFrontBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageAddressProofFront);
                binding.imageAddressProofFrontClose.setVisibility(VISIBLE);
                break;
            case ADDRESS_PROOF_BACK:
                addressProofBackBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageAddressProofBack);
                binding.imageAddressProofBackClose.setVisibility(VISIBLE);
                break;
            case EXPERTISE_DOCUMENT:
                expertiseDocumentBitmap = imageBitmap;
                with(this).load(imageBitmap).into(binding.imageExpertiseDocument);
                binding.imageExpertiseDocumentClose.setVisibility(VISIBLE);
                break;
        }
    }

    /**
     * This method is invoked to encode Document images into base64 format.
     */
    private void encodeDocuments() {
        Thread backgroundThread = new Thread() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = STEP_ONE_COMPLETE;
                handler.sendMessage(msg);

                highestDegreeBase = baseActivity.encodeToBase64(highestDegreeBitmap);
                govtIDFrontBase = baseActivity.encodeToBase64(govtIDFrontBitmap);
                govtIDBackBase = baseActivity.encodeToBase64(govtIDBackBitmap);
                addressProofFrontBase = baseActivity.encodeToBase64(addressProofFrontBitmap);
                if (addressProofBackBitmap != null)
                    addressProofBackBase = baseActivity.encodeToBase64(addressProofBackBitmap);
                expertiseDocumentBase = baseActivity.encodeToBase64(expertiseDocumentBitmap);

                Message msg2 = Message.obtain();
                msg2.what = STEP_TWO_COMPLETE;
                handler.sendMessage(msg2);
            }
        };
        backgroundThread.start();
    }

    /**
     * This method is invoked to return user's personal details to the details activity.
     */
    private void returnCoachingDetails() {
        PersonalDetailsListener personalDetailsListener = (PersonalDetailsListener) getActivity();

        requireNonNull(personalDetailsListener).personalDetails(new PersonalDetails(fullName,
                gender, highestDegreeBase, govtIDFrontBase, govtIDBackBase, addressProofFrontBase,
                addressProofBackBase, expertiseDocumentBase));
    }

    /**
     * This method is invoked to validate all fields present in this screen.
     *
     * @return validation status of all edit fields.
     */
    private boolean validateFields() {
        fullName = requireNonNull(binding.editFullName.getText()).toString();

        if (isEmpty(fullName)) {
            binding.editFullName.requestFocus();
            binding.editFullName.setError("Please enter your full name");
            return false;
        }

        if (gender == null) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please select gender");
            return false;
        }

        if (highestDegreeBitmap == null) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please Upload Highest Degree Image");
            return false;
        }

        if (govtIDFrontBitmap == null) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please Government ID Front Image");
            return false;
        }

        if (govtIDBackBitmap == null) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please Upload Government ID Back Image");
            return false;
        }

        if (addressProofFrontBitmap == null) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please Address Proof Front Image");
            return false;
        }

        if (expertiseDocumentBitmap == null) {
            baseActivity.showSnackBar(requireNonNull(getActivity()), "Please Upload Your Relevant Category Expertise Document Image");
            return false;
        }

        return true;
    }

    /* ------------------------------------------------------------- *
     * Public Interface
     * ------------------------------------------------------------- */

    public interface PersonalDetailsListener {
        void personalDetails(PersonalDetails personalDetails);
    }
}