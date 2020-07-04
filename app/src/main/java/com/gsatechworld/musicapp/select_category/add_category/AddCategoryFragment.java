package com.gsatechworld.musicapp.select_category.add_category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentAddCategoryBinding;

import static android.text.TextUtils.isEmpty;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class AddCategoryFragment extends BottomSheetDialogFragment implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentAddCategoryBinding binding;
    private AddCategoryViewModel viewModel;
    private BaseActivity baseActivity;
    public String category,pinCode;

    /* ------------------------------------------------------------- *
     * Overriding BottomSheetDialogFragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_add_category, container, false);
        pinCode=getArguments().getString(PIN_CODE);

        viewModel = new ViewModelProvider(this).get(AddCategoryViewModel.class);

        baseActivity = (BaseActivity) getActivity();

        /*Setting listeners to the views*/
        binding.imageClose.setOnClickListener(this);
        binding.buttonAdd.setOnClickListener(this);



        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageClose:
                dismiss();
                break;
            case R.id.buttonAdd:
                 category = requireNonNull(binding.editCategory.getText()).toString().trim();

                if (isEmpty(category))
                    binding.editCategory.setError(getString(R.string.category_validation));
                else
               addCategory();
                break;
        }
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */


    private void addCategory() {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            viewModel.addCategory(new AddCategory(pinCode,category)).observe(this, commonResponse -> {
                baseActivity.hideLoadingIndicator();

                if (commonResponse.getStatus().equals("success")) {
                    baseActivity.openSuccessDialog("Your skill is Updated." +
                            " Please wait for Admin Approval");
                    dismiss();
                } else
                    baseActivity.showSnackBar(requireNonNull(getActivity()),
                            commonResponse.getMessage());
            });

        } else
            baseActivity.showSnackBar(requireNonNull(getActivity()),
                    getString(R.string.no_internet_message));
    }
}
