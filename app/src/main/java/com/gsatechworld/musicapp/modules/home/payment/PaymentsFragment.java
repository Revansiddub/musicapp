package com.gsatechworld.musicapp.modules.home.payment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentPaymentsBinding;
import com.gsatechworld.musicapp.modules.home.earnings.EarningsViewModel;
import com.gsatechworld.musicapp.modules.home.payment.adapter.AcceptPayment;
import com.gsatechworld.musicapp.modules.home.payment.adapter.PaymentRequestAdapter;
import com.gsatechworld.musicapp.utilities.Constants;

import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TrainerId;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentsFragment extends Fragment implements PaymentRequestAdapter.OnActionPaymentPerformedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public FragmentPaymentsBinding paymentsBinding;
    public PaymentRequestViewModel viewModel;
    public AcceptPaymentViewModel paymentViewModel;

    public String trainerId;
    public  int position;


    private BaseActivity baseActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PaymentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentsFragment newInstance(String param1, String param2) {
        PaymentsFragment fragment = new PaymentsFragment();
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
        paymentsBinding=inflate(inflater, R.layout.fragment_payments, container, false);

        paymentsBinding.layoutBase.toolbar.setTitle("Payments");

        viewModel=new ViewModelProvider(this).get(PaymentRequestViewModel.class);

        paymentViewModel=new ViewModelProvider(this).get(AcceptPaymentViewModel.class);



        baseActivity = (BaseActivity) getActivity();

        trainerId=getArguments().getString(TRAINER_ID);

        fetchPaymentRequests();

        return paymentsBinding.getRoot();
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
    public void onActionPerformed(String trainerID, String payment_request_id) {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            paymentViewModel.acceptPayments(new AcceptPayment(trainerID,payment_request_id)).observe(getViewLifecycleOwner(),commonResponse -> {
                baseActivity.hideLoadingIndicator();
                if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS)){
                    baseActivity.showSnackBar(requireNonNull(getActivity()),
                            commonResponse.getMessage());
                } else {
                    baseActivity.showSnackBar(requireNonNull(getActivity()),
                            "Failed");
                }
            });



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
    public void fetchPaymentRequests(){
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            //baseActivity.showLoadingIndicator();

            //trainerId="1";
            SharedPreferences sharedpreferences = getContext().getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
            trainerId = String.valueOf(sharedpreferences.getInt(TrainerId, 0));
            viewModel.getPaymentRequests(trainerId).observe(getViewLifecycleOwner(),paymentRequestResponse -> {

                if (paymentRequestResponse.getPayment_requests() != null){
                    //baseActivity.hideLoadingIndicator();
                    paymentsBinding.recyclerPaymentRequest.setLayoutManager(new LinearLayoutManager(getActivity()));
                    paymentsBinding.recyclerPaymentRequest.setHasFixedSize(true);
                    PaymentRequestAdapter adapter = new PaymentRequestAdapter(getActivity()
                            ,paymentRequestResponse.getPayment_requests(),trainerId);
                    adapter.setClickListner(this);
                    paymentsBinding.recyclerPaymentRequest.setAdapter(adapter);

                }else {
                    baseActivity.showSnackBar(requireNonNull(getActivity()),
                            "No Payment Requests");
                }


            });


        }
            else
            baseActivity.showSnackBar(requireNonNull(getActivity()),
                    getString(R.string.no_internet_message));

    }

    public interface OnActionPerformedListener {
        void onActionPerformed(String studentID, String action);
    }
}
