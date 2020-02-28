package com.gsatechworld.musicapp.modules.home.earnings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentEarningsBinding;
import com.gsatechworld.musicapp.modules.home.earnings.adapter.EarningAdapter;
import com.gsatechworld.musicapp.modules.home.earnings.pending_payments.PendingPaymentActivity;

import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class EarningsFragment extends Fragment implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentEarningsBinding binding;
    private EarningsViewModel viewModel;
    private BaseActivity baseActivity;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_earnings, container, false);

        /*Initialising View model*/
        viewModel = new ViewModelProvider(this).get(EarningsViewModel.class);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Earnings");

        baseActivity = (BaseActivity) getActivity();

        fetchEarningDetails();

        /*Setting listeners to the view*/
        binding.cardPending.setOnClickListener(this);

        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cardPending)
            startActivity(new Intent(getActivity(), PendingPaymentActivity.class));
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to fetch a list of all earnings of that particular trainer
     */
    private void fetchEarningDetails() {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            String trainerID = "1";

            viewModel.getEarningDetailsList(trainerID).observe(getViewLifecycleOwner(),
                    earningResponse -> {
                        baseActivity.hideLoadingIndicator();

                        if (earningResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)) {
                            binding.recyclerEarnings.setLayoutManager(new
                                    LinearLayoutManager(getActivity(), VERTICAL, false));

                            binding.recyclerEarnings.setAdapter(new EarningAdapter(getActivity(),
                                    earningResponse.getEarningList()));
                        } else
                            baseActivity.showSnackBar(requireNonNull(getActivity()),
                                    earningResponse.getMessage());
                    });
        } else
            baseActivity.showSnackBar(requireNonNull(getActivity()),
                    getString(R.string.no_internet_message));
    }
}