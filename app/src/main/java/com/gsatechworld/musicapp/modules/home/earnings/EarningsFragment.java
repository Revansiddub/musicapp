package com.gsatechworld.musicapp.modules.home.earnings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.gsatechworld.musicapp.utilities.Constants;

import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static com.gsatechworld.musicapp.utilities.Constants.TrainerId;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class EarningsFragment extends Fragment  {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentEarningsBinding binding;
    private EarningsViewModel viewModel;
    private BaseActivity baseActivity;
    public String trainerId;
    public  int position;

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

        SharedPreferences sharedpreferences = getContext().getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        trainerId = String.valueOf(sharedpreferences.getInt(TrainerId, 0));
        //trainerId=getArguments().getString(TRAINER_ID);

        fetchEarningDetails();

        /*Setting listeners to the view*/

        binding.cardPending.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), PendingPaymentActivity.class);
            intent.putExtra(TRAINER_ID, trainerId);
            startActivity(intent);
        });

        return binding.getRoot();
    }

    private void fetchEarningDetails() {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            viewModel.getEarningDetailsList(trainerId).observe(getViewLifecycleOwner(),
                    earningResponse -> {
                       baseActivity.hideLoadingIndicator();

                        if (earningResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)) {

                            binding.recyclerEarnings.setLayoutManager(new
                                    LinearLayoutManager(getActivity(), VERTICAL, false));

                            binding.recyclerEarnings.setAdapter(new EarningAdapter(getActivity(),
                                    earningResponse.getResult().getStudent_list()));
                        } else
                            baseActivity.showSnackBar(requireNonNull(getActivity()),
                                    earningResponse.getResponse());
                    });
        } else
            baseActivity.showSnackBar(requireNonNull(getActivity()),
                    getString(R.string.no_internet_message));
    }
}