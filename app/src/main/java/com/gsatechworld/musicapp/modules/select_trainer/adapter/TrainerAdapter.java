package com.gsatechworld.musicapp.modules.select_trainer.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.LayoutTrainerBinding;
import com.gsatechworld.musicapp.modules.select_time_slot.SelectTimeSlotActivity;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.Trainer;
import com.gsatechworld.musicapp.modules.select_trainer.pojo.TrainersResponse;
import com.gsatechworld.musicapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.utilities.Constants.TRAINER_ID;
import static java.util.Locale.getDefault;

public class TrainerAdapter extends Adapter<TrainerAdapter.TrainerHolder> {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Context mCtx;
    private List<TrainersResponse.Trainers_list> trainerList;
    private List<TrainersResponse.Trainers_list> searchableTrainerList;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public TrainerAdapter(Context mCtx, ArrayList<TrainersResponse.Trainers_list> trainerList) {
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
        TrainersResponse.Trainers_list trainer = trainerList.get(position);
        holder.binding.setTrainer(trainer);

        StringBuilder coachingType = new StringBuilder();
        StringBuilder recurrenceType = new StringBuilder();
        StringBuilder days = new StringBuilder();
        for (String type : trainer.getCoaching_types()) {
            coachingType.append(type);
            coachingType.append(", ");
        }

//        for (String type : trainer.getRecurrenceType()) {
//            recurrenceType.append(type);
//            recurrenceType.append(", ");
//        }
//
//        for (String day : trainer.getRecurrenceDays()) {
//            days.append(day);
//            days.append(", ");
//        }

        holder.binding.textCoachingTypeValue.setText(coachingType);
        holder.binding.textRecurrenceTypeValue.setText(recurrenceType);
        holder.binding.textDaysTypeValue.setText(days);
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
            for (TrainersResponse.Trainers_list trainer : searchableTrainerList)
                if (trainer.getTrainer_name().toLowerCase().contains(charText.toLowerCase()))
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
                TrainersResponse.Trainers_list trainer = trainerList.get(getAdapterPosition());
                SharedPreferences sharedPreferences=mCtx.getSharedPreferences(Constants.MyPREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(TRAINER_ID,String.valueOf(trainer.getTrainer_id()));
                editor.commit();
                Intent intent = new Intent(mCtx.getApplicationContext(), SelectTimeSlotActivity.class);
                intent.putExtra(TRAINER_ID, trainer.getTrainer_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.getApplicationContext().startActivity(intent);
            }
        }
    }
}
