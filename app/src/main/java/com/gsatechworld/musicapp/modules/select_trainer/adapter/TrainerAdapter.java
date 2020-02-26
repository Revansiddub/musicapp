package com.gsatechworld.musicapp.modules.select_trainer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTrainerBinding;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.Trainer;
import com.gsatechworld.musicapp.modules.student_details.StudentDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static java.util.Locale.getDefault;

public class TrainerAdapter extends Adapter<TrainerAdapter.TrainerHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<Trainer> trainerList;
    private List<Trainer> searchableTrainerList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerAdapter(Context mCtx, List<Trainer> trainerList) {
        this.mCtx = mCtx;
        this.trainerList = trainerList;
        searchableTrainerList = new ArrayList<>(trainerList);
    }

    /* ------------------------------------------------------------- *
     * Overriding RecyclerView.Adapter Methods
     * ------------------------------------------------------------- */

    @NonNull
    @Override
    public TrainerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTrainerBinding binding = inflate(from(mCtx), R.layout.layout_trainer, parent,
                false);
        return new TrainerHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerHolder holder, int position) {
        holder.binding.setTrainer(trainerList.get(position));
    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }

    /* ------------------------------------------------------------- *
     * Public Method
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to search item in the list based on the character or string.
     *
     * @param charText char sequence that need to be searched in the list.
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(getDefault());

        trainerList.clear();

        if (charText.length() == 0)
            trainerList.addAll(searchableTrainerList);
        else
            for (Trainer trainer : searchableTrainerList)
                if (trainer.getTrainerName().toLowerCase().contains(charText.toLowerCase()))
                    trainerList.add(trainer);

        notifyDataSetChanged();
    }

    /* ------------------------------------------------------------- *
     * Trainer Holder Class
     * ------------------------------------------------------------- */

    class TrainerHolder extends ViewHolder implements OnClickListener {

        /* ------------------------------------------------------------- *
         * Private Members
         * ------------------------------------------------------------- */

        private final LayoutTrainerBinding binding;

        /* ------------------------------------------------------------- *
         * Constructor
         * ------------------------------------------------------------- */

        TrainerHolder(final LayoutTrainerBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            /*Setting listeners to the views*/
            binding.layoutTrainer.setOnClickListener(this);
        }

        /* ------------------------------------------------------------- *
         * Overriding OnClickListener Method
         * ------------------------------------------------------------- */

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.layoutTrainer) {
                Intent intent = new Intent(mCtx, StudentDetailsActivity.class);
                mCtx.startActivity(intent);
            }
        }
    }
}
