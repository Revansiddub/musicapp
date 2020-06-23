package com.gsatechworld.musicapp.modules.student_home.student_profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentStudentProfileBinding;
import com.gsatechworld.musicapp.modules.home.approval.ApprovalViewModel;
import com.gsatechworld.musicapp.utilities.Constants;

import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.databinding.DataBindingUtil.setDefaultComponent;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public ProfileViewModel profileViewModel;
    public FragmentStudentProfileBinding profileBinding;

    public StudentProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentProfileFragment newInstance(String param1, String param2) {
        StudentProfileFragment fragment = new StudentProfileFragment();
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
        profileBinding = inflate(inflater, R.layout.fragment_student_profile, container, false);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);


        fetchStudentProfile();



        return profileBinding.getRoot();
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

    public void fetchStudentProfile(){
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
         String student_id="1";
         profileViewModel.fetchStudentProfile(student_id).observe(getViewLifecycleOwner(),studentProfileResponse -> {
             if (studentProfileResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                 profileBinding.setStudentprofile(studentProfileResponse);

             }
         });
        }
    }
}
