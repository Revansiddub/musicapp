package com.gsatechworld.musicapp.modules.home.earnings.pending_payments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityPendingPaymentBinding;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter.OnActionPerformedListener;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PaymentActionInfo;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PendingPaymentsResp;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter.grandTotal;
import static com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter.pendingPaymentList;
import static com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter.position;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TrainerId;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class PendingPaymentActivity extends BaseActivity implements ApproveStudentAdapter.OnActionPerformedListener
         {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityPendingPaymentBinding binding;
    private PendingPaymentViewModel viewModel;
    private PendingPaymentAdapter adapter;
    public String trainerId;
    public int pendingPayments;
    public ArrayList<PendingPaymentsResp.PendingPayments> pendingPaymentsArrayList;

    public  int total=0;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pending_payment);



        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(PendingPaymentViewModel.class);


        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle(getString(R.string.pending_payments));
        setSupportActionBar(binding.layoutBase.toolbar);
        requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        trainerId = String.valueOf(sharedpreferences.getInt(TrainerId, 0));
        //trainerId=getIntent().getStringExtra(TRAINER_ID);

        fetchPendingPaymentList();

       //calculate_total();

        /*Setting listeners to the views*/
       // binding.searchStudent.setOnQueryTextListener(this);
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
     * Overriding OnQueryTextListener Methods
     * ------------------------------------------------------------- */

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        if (adapter != null)
//        //    adapter.filter(query);
//        return true;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        if (adapter != null)
//            adapter.filter(newText);
//        return true;
//    }

    /* ------------------------------------------------------------- *
     * Overriding OnActionPerformedListener Method
     * ------------------------------------------------------------- */

//    @Override
//    public void onActionPerformed(String requestID, String action) {
//        if (getNetworkInstance(this).isConnectedToInternet()) {
//            showLoadingIndicator();
//
//            viewModel.storeAction(new PaymentActionInfo(requestID, action)).observe(this,
//                    commonResponse -> {
//                        hideLoadingIndicator();
//
//                        if (commonResponse.getStatus().equals(SERVER_RESPONSE_SUCCESS))
//                            fetchPendingPaymentList();
//
//                        showSnackBar(this, commonResponse.getMessage());
//                    });
//        } else
//            showSnackBar(this, getString(R.string.no_internet_message));
//    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to fetch all list of all payment request done by student to that
     * trainer.
     */
    private void fetchPendingPaymentList() {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            binding.recyclerPending.setAdapter(adapter);
            viewModel.fetchPendingPaymentList(trainerId).observe(this, paymentResponse -> {
                hideLoadingIndicator();

                if (paymentResponse.getPending_payments() != null) {

                  //  pendingPayments= paymentResponse.getPending_payments().get(position).getAmount();
                    pendingPaymentsArrayList=paymentResponse.getPending_payments();
                    for (int i =0;i<pendingPaymentsArrayList.size();i++){
                        total=total+paymentResponse.getPending_payments().get(i).getAmount();
                    }

                    binding.textTotal.setText(String.valueOf(total));

                    adapter = new PendingPaymentAdapter(this,
                            paymentResponse.getPending_payments());

                    binding.recyclerPending.setLayoutManager(new LinearLayoutManager(this,
                            VERTICAL, false));

                    binding.recyclerPending.setAdapter(adapter);
                }
                if (adapter.getItemCount() == 0) {
                    showSnackBar(this,"No Pending Payments");
                }


            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }



             @Override
             public void onActionPerformed(String entrollmentID, String studentID, String action) {
                 if (action.equals(action.equals("PAID"))) {

                 }
             }
         }