package com.gsatechworld.musicapp.modules.home.earnings.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutEarningBinding;
import com.gsatechworld.musicapp.modules.home.earnings.pojo.Earning;

import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;

public class EarningAdapter extends Adapter<EarningAdapter.EarningHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<Earning> earningList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public EarningAdapter(Context mCtx, List<Earning> earningList) {
        this.mCtx = mCtx;
        this.earningList = earningList;
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public EarningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutEarningBinding binding = inflate(from(mCtx), R.layout.layout_earning, parent,
                false);
        return new EarningHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarningHolder holder, int position) {
        Earning earning = earningList.get(position);
        holder.binding.setEarning(earning);
    }

    @Override
    public int getItemCount() {
        return earningList.size();
    }

    /* ------------------------------------------------------------- *
     * Earning Holder Class
     * ------------------------------------------------------------- */

    class EarningHolder extends ViewHolder {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutEarningBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        EarningHolder(final LayoutEarningBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}