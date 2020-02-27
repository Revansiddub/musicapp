package com.gsatechworld.musicapp.modules.home.trainer_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentTrainerHomeBinding;

import static androidx.databinding.DataBindingUtil.inflate;

public class TrainerHomeFragment extends Fragment {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentTrainerHomeBinding binding;
    private TrainerHomeViewModel viewModel;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_trainer_home, container, false);

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(TrainerHomeViewModel.class);

        return binding.getRoot();
    }
}