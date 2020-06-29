package com.gsatechworld.musicapp.modules.student_home.student_payment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentStudentPaymentBinding;
import com.gsatechworld.musicapp.modules.home.payment.adapter.PaymentRequestAdapter;
import com.gsatechworld.musicapp.modules.student_home.student_payment.adapter.MyDialogFragment;
import com.gsatechworld.musicapp.modules.student_home.student_payment.adapter.StudentPaymentAdapter;
import com.gsatechworld.musicapp.modules.student_home.student_payment.pojo.StudentPaymentRequest;
import com.gsatechworld.musicapp.utilities.Constants;

import static android.content.Context.MODE_PRIVATE;
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
public class StudentPaymentFragment extends Fragment implements StudentPaymentAdapter.onStudentPaymentListener, MyDialogFragment.onStudentPaymentListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentStudentPaymentBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public StudentPaymentViewModel paymentViewModel;

    public PaymentRequestViewModel requestViewModel;

    private BaseActivity baseActivity;

    public StudentPaymentAdapter paymentAdapter;

    public Context context;

    private OnFragmentInteractionListener mListener;

    private MyDialogFragment.onStudentPaymentListener onStudentPaymentListener;

    private StudentPaymentAdapter.onStudentPaymentListener listener;

    public String paymentamount,entrollment_id,student_id;


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


        binding.layoutBase.toolbar.setTitle("Payments");
        binding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        binding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        baseActivity = (BaseActivity) getActivity();


        SharedPreferences preferences=getContext().getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        student_id=preferences.getString(Constants.STUDENT_ID,null);

        paymentViewModel= new ViewModelProvider(this).get(StudentPaymentViewModel.class);

        requestViewModel=new ViewModelProvider(this).get(PaymentRequestViewModel.class);





        if (listener != null){
            SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.MyPREFERENCES,Context.MODE_PRIVATE);
            entrollment_id=sharedPreferences.getString(Constants.ENTROLLMENT_ID,null);
        }




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



    @Override
    public void onActionPerformed(View v,String entrollment_id) {
        this.entrollment_id=entrollment_id;
        MyDialogFragment dialogFragment=new MyDialogFragment();
        dialogFragment.show(getFragmentManager(),"Payment");
        dialogFragment.setActionListener(this);
    }


    @Override
    public void onActionListener(String amount) {

        paymentamount=String.valueOf(amount);

        paymentRequest();
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
            paymentViewModel.fetchStudentPayment(student_id).observe(getViewLifecycleOwner(), studentPaymentResponse -> {
                baseActivity.hideLoadingIndicator();
                if (studentPaymentResponse.getResponse().equals(Constants.SERVER_RESPONSE_SUCCESS)) {
                    binding.recyclerPayments.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.recyclerPayments.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                    paymentAdapter=new StudentPaymentAdapter(getActivity(),studentPaymentResponse.getPending_payments(),this);
                    paymentAdapter.setActionListener(this);
                    binding.recyclerPayments.setAdapter(paymentAdapter);

                } else {
                    baseActivity.showSnackBar(requireNonNull(getActivity()),
                            getString(R.string.no_internet_message));
                }
            });
        }
    }

    public void paymentRequest(){

        Toast.makeText(getActivity(),"Payement Request Sent Successfully",Toast.LENGTH_SHORT).show();
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            requestViewModel.sendPaymentRequest(new StudentPaymentRequest(student_id,entrollment_id,paymentamount)).observe(getViewLifecycleOwner(),commonResponse -> {
                if (commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    baseActivity.openSuccessDialog("Payment Request Send Successfully");
                    fetchingPaymentDetals();
                }
            });

        }



    }
}
