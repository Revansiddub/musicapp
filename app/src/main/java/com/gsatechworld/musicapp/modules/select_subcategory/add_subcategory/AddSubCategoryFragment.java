package com.gsatechworld.musicapp.modules.select_subcategory.add_subcategory;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentAddSubCategoryBinding;

import static android.text.TextUtils.isEmpty;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.CATEGORY_ID;
import static com.gsatechworld.musicapp.utilities.Constants.PIN_CODE;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSubCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSubCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubCategoryFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String category_id,pinCode,subcategory;
    public FragmentAddSubCategoryBinding binding;
    AddSubCategoryViewModel subCategoryViewModel;
    public BaseActivity baseActivity;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddSubCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSubCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSubCategoryFragment newInstance(String param1, String param2) {
        AddSubCategoryFragment fragment = new AddSubCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=inflate(inflater, R.layout.fragment_add_sub_category, container, false);

        baseActivity = (BaseActivity) getActivity();

        pinCode=getArguments().getString(PIN_CODE);
        category_id=getArguments().getString(CATEGORY_ID);


        subCategoryViewModel=new ViewModelProvider(this).get(AddSubCategoryViewModel.class);
        binding.buttonAdd.setOnClickListener(this);
        return binding.getRoot();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:
                subcategory = requireNonNull(binding.editCategory.getText()).toString().trim();

                if (isEmpty(subcategory))
                    binding.editCategory.setError(getString(R.string.category_validation));
                else
                    addSubCategories();
                break;
            case R.id.image_close:
                dismiss();
                break;


        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void addSubCategories(){

        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {

            baseActivity.showLoadingIndicator();

            subCategoryViewModel.addSubCategories(new AddSubCategory(pinCode,category_id,subcategory)).observe(this,commonResponse -> {
                baseActivity.hideLoadingIndicator();
                if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    baseActivity.openSuccessDialog("Sub Category Added Successfully");
                    dismiss();

                }else {
                    baseActivity.showSnackBar(requireNonNull(getActivity()),commonResponse.getMessage());
                }




            });
        }
        else {
            baseActivity.showSnackBar(requireNonNull(getActivity()),"No Internet Connection");
        }

    }
}
