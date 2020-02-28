package com.gsatechworld.musicapp.modules.home.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentSettingsBinding;
import com.gsatechworld.musicapp.modules.home.settings.adapter.SettingsAdapter;
import com.gsatechworld.musicapp.modules.home.settings.pojo.SettingItem;

import java.util.ArrayList;
import java.util.List;

import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

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

        setAdapter();

        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to setup and initialise recycler view to adapter.
     */
    private void setAdapter() {
        List<SettingItem> settingList = new ArrayList<>();
        settingList.add(new SettingItem("", R.drawable.icon_home));
        settingList.add(new SettingItem("", R.drawable.icon_home));
        settingList.add(new SettingItem("", R.drawable.icon_home));
        settingList.add(new SettingItem(getString(R.string.logout), R.drawable.icon_logout));

        SettingsAdapter adapter = new SettingsAdapter(getActivity(), settingList);

        binding.recyclerSettings.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL,
                false));
        binding.recyclerSettings.setAdapter(adapter);
    }
}