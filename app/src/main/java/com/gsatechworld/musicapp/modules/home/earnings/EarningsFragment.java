package com.gsatechworld.musicapp.modules.home.earnings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentEarningsBinding;
import com.gsatechworld.musicapp.modules.home.approval.ApprovalViewModel;

import static androidx.databinding.DataBindingUtil.inflate;

public class EarningsFragment extends Fragment {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentEarningsBinding binding;
    private EarningsViewModel viewModel;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_earnings, container, false);

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(EarningsViewModel.class);

        return binding.getRoot();
    }
}