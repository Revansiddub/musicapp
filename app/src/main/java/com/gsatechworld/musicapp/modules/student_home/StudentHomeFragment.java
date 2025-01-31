package com.gsatechworld.musicapp.modules.student_home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentStudentHomeBinding;
import com.gsatechworld.musicapp.modules.student_home.adapter.EntrollmentAdapter;
import com.gsatechworld.musicapp.select_category.SelectCategoriesActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EntrollmentsViewModel entrollmentsViewModel;

    private BaseActivity baseActivity;

    FragmentStudentHomeBinding studentHomeBinding;

    public StudentHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentHomeFragment newInstance(String param1, String param2) {
        StudentHomeFragment fragment = new StudentHomeFragment();
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
        studentHomeBinding=inflate(inflater, R.layout.fragment_student_home, container, false);

        entrollmentsViewModel=new ViewModelProvider(getActivity()).get(EntrollmentsViewModel.class);

        baseActivity = (BaseActivity) getActivity();

        fetchEntrollemntDetals();

        studentHomeBinding.textAddentrollment.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),SelectCategoriesActivity.class);
            intent.putExtra(Constants.PIN_CODE,"560078");
            intent.putExtra(Constants.USER_TYPE,"Student");
            startActivity(intent);

        });

        return studentHomeBinding.getRoot();
    }

    private void fetchEntrollemntDetals() {

        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();


            String student_Id="1";
            entrollmentsViewModel.fetchStudentEntrollments(student_Id).observe(getViewLifecycleOwner(),entrollmentResponse -> {
             baseActivity.hideLoadingIndicator();
             if (entrollmentResponse.getResponse().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                 studentHomeBinding.recyclerEntrollments.setLayoutManager(new GridLayoutManager(getActivity(),2));
                 studentHomeBinding.recyclerEntrollments.setAdapter(new EntrollmentAdapter(getActivity(),entrollmentResponse.getEntrollmentsList()));
             }
             else {
                 baseActivity.showSnackBar(requireNonNull(getActivity()),
                         getString(R.string.no_internet_message));
             }
            });

        }

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
}
