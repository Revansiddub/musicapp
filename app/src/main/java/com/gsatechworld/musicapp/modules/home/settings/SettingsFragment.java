package com.gsatechworld.musicapp.modules.home.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import com.gsatechworld.musicapp.modules.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.core.manager.SessionManager.getSessionInstance;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;

public class SettingsFragment extends Fragment {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentSettingsBinding binding;
    private SettingsViewModel viewModel;
    public int position;
    public Context context;
    public String trainerID;
    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_settings, container, false);

        binding.layoutBase.toolbar.setTitle("Settings");

        binding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        binding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        trainerID = getArguments().getString(TRAINER_ID);


        binding.logout.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            alertDialog.setTitle(getActivity().getString(R.string.logout_title));
            alertDialog.setMessage(getActivity().getString(R.string.logout_message));

            alertDialog.setPositiveButton(getActivity().getString(R.string.yes), (dialog, which) -> {
                getSessionInstance(getActivity()).clearUserCredentials();
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.setFlags((FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK));
                dialog.cancel();
                startActivity(loginIntent);
            });
            alertDialog.setNegativeButton(getActivity().getString(R.string.no), (dialog, which) -> dialog.cancel());

            alertDialog.show();


        });
        binding.textChangePassword.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),ChangePasswordActivity.class);
            intent.putExtra(TRAINER_ID,trainerID);
            startActivity(intent);
        });

        //setAdapter();

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
        settingList.add(new SettingItem("Change Password", R.drawable.ic_lock_24));
        settingList.add(new SettingItem(getString(R.string.logout), R.drawable.icon_logout));
    }
}