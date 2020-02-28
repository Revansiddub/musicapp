package com.gsatechworld.musicapp.modules.home.earnings.pending_payments;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityPendingPaymentBinding;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.adapter.PendingPaymentAdapter.OnActionPerformedListener;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.pojo.PaymentActionInfo;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class PendingPaymentActivity extends BaseActivity implements OnQueryTextListener,
        OnActionPerformedListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityPendingPaymentBinding binding;
    private PendingPaymentViewModel viewModel;
    private PendingPaymentAdapter adapter;

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

        fetchPendingPaymentList();

        /*Setting listeners to the views*/
        binding.searchStudent.setOnQueryTextListener(this);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (adapter != null)
            adapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (adapter != null)
            adapter.filter(newText);
        return true;
    }

    /* ------------------------------------------------------------- *
     * Overriding OnActionPerformedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onActionPerformed(String requestID, String action) {
        if (getNetworkInstance(this).isConnectedToInternet()) {
            showLoadingIndicator();

            viewModel.storeAction(new PaymentActionInfo(requestID, action)).observe(this,
                    commonResponse -> {
                        hideLoadingIndicator();

                        if (commonResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS))
                            fetchPendingPaymentList();

                        showSnackBar(this, commonResponse.getMessage());
                    });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }

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

            String trainerID = "1";

            viewModel.fetchPendingPaymentList(trainerID).observe(this, paymentResponse -> {
                hideLoadingIndicator();

                if (paymentResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)) {
                    binding.recyclerPending.setLayoutManager(new LinearLayoutManager(this,
                            VERTICAL, false));
                    adapter = new PendingPaymentAdapter(this,
                            paymentResponse.getPendingPaymentList());
                    binding.recyclerPending.setAdapter(adapter);
                } else
                    showSnackBar(this, paymentResponse.getMessage());
            });
        } else
            showSnackBar(this, getString(R.string.no_internet_message));
    }
}