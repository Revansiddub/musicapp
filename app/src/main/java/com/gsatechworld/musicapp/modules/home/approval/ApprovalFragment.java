package com.gsatechworld.musicapp.modules.home.approval;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentApprovalBinding;

import static androidx.databinding.DataBindingUtil.inflate;

public class ApprovalFragment extends Fragment {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentApprovalBinding binding;
    private ApprovalViewModel viewModel;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_approval, container, false);

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(ApprovalViewModel.class);

        return binding.getRoot();
    }
}