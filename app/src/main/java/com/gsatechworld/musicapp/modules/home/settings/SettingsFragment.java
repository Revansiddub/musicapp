package com.gsatechworld.musicapp.modules.home.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentSettingsBinding;

import static androidx.databinding.DataBindingUtil.inflate;

public class SettingsFragment extends Fragment {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentSettingsBinding binding;
    private SettingsViewModel viewModel;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_settings, container, false);

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        return binding.getRoot();
    }
}