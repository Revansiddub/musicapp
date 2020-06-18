package com.gsatechworld.musicapp.modules.student_home.student_payment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentStudentPaymentBinding;
import com.gsatechworld.musicapp.modules.student_home.student_payment.adapter.StudentPaymentAdapter;
import com.gsatechworld.musicapp.utilities.Constants;

import static android.widget.LinearLayout.VERTICAL;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentPaymentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentPaymentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentStudentPaymentBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public StudentPaymentViewModel paymentViewModel;

    private BaseActivity baseActivity;

    private OnFragmentInteractionListener mListener;

    public StudentPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentPaymentFragment newInstance(String param1, String param2) {
        StudentPaymentFragment fragment = new StudentPaymentFragment();
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
        binding=inflate(inflater, R.layout.fragment_student_payment, container, false);

        paymentViewModel= new ViewModelProvider(this).get(StudentPaymentViewModel.class);

        baseActivity = (BaseActivity) getActivity();

        fetchingPaymentDetals();

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

    public void fetchingPaymentDetals() {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();
            String studentId = "1";
            paymentViewModel.fetchStudentPayment(studentId).observe(getViewLifecycleOwner(), studentPaymentResponse -> {
                baseActivity.hideLoadingIndicator();
                if (studentPaymentResponse.getResponse().equals(Constants.SERVER_RESPONSE_SUCCESS)) {
                    binding.recyclerPayments.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.recyclerPayments.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                    binding.recyclerPayments.setAdapter(new StudentPaymentAdapter(getActivity(), studentPaymentResponse.getPaymentList()));

                } else {
                    baseActivity.showSnackBar(requireNonNull(getActivity()),
                            getString(R.string.no_internet_message));
                }
            });
        }
    }
}
