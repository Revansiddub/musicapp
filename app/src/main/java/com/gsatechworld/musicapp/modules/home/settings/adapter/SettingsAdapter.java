package com.gsatechworld.musicapp.modules.home.settings.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutSettingsItemsBinding;
import com.gsatechworld.musicapp.modules.home.settings.pojo.SettingItem;
import com.gsatechworld.musicapp.modules.login.LoginActivity;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.core.manager.SessionManager.getSessionInstance;

public class SettingsAdapter extends Adapter<SettingsAdapter.SettingsHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<SettingItem> settingList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SettingsAdapter(Context mCtx, List<SettingItem> settingList) {
        this.mCtx = mCtx;
        this.settingList = settingList;
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public SettingsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutSettingsItemsBinding binding = inflate(LayoutInflater.from(mCtx),
                R.layout.layout_settings_items, viewGroup, false);
        return new SettingsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsHolder holder, int position) {
        SettingItem item = settingList.get(position);

        holder.binding.setSettings(item);

        holder.binding.imageItem.setImageResource(item.getSettingDrawableID());
    }

    @Override
    public int getItemCount() {
        return settingList.size();
    }

    /* ------------------------------------------------------------- *
     * Settings Holder Class
     * ------------------------------------------------------------- */

    class SettingsHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutSettingsItemsBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        SettingsHolder(final LayoutSettingsItemsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting listeners to the view*/
            binding.layoutItem.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClick Methods
         * ------------------------------------------------------------- */

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.layoutItem) {
                switch (getAdapterPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        openConfirmationDialog();
                        break;
                }
            }
        }

        /* ------------------------------------------------------------- *
         * Private Methods
         * ------------------------------------------------------------- */

        /**
         * This method is invoked to open a confirmation dialog box before logging out.
         */
        private void openConfirmationDialog() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mCtx);

            alertDialog.setTitle(mCtx.getString(R.string.logout_title));
            alertDialog.setMessage(mCtx.getString(R.string.logout_message));

            alertDialog.setPositiveButton(mCtx.getString(R.string.yes), (dialog, which) -> {
                getSessionInstance(mCtx).clearUserCredentials();
                Intent loginIntent = new Intent(mCtx, LoginActivity.class);
                loginIntent.setFlags((FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK));
                dialog.cancel();
                mCtx.startActivity(loginIntent);
            });
            alertDialog.setNegativeButton(mCtx.getString(R.string.no), (dialog, which) -> dialog.cancel());

            alertDialog.show();
        }
    }
}