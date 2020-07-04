package com.gsatechworld.musicapp.modules.details.coaching_details;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentCoachingDetailsBinding;
import com.gsatechworld.musicapp.modules.details.TimePickerFragment;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.DaysAdapter;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.DaysAdapter.OnDaySelectedListener;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.RecyclerData;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.TimeSlotsAdapter;
import com.gsatechworld.musicapp.modules.details.coaching_details.adapter.TimesListAdapter;
import com.gsatechworld.musicapp.modules.details.coaching_details.pojo.CoachingDetails;
import com.gsatechworld.musicapp.modules.details.pojo.Recurrence_types;
import com.gsatechworld.musicapp.modules.details.pojo.Slot_details;
import com.gsatechworld.musicapp.modules.home.trainer_home.adapter.TimesAdapter;
import com.gsatechworld.musicapp.modules.home.trainer_home.repository.TimeSlotRepository;
import com.gsatechworld.musicapp.modules.home.trainer_home.viewmodel.TimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.SelectTimeSlotViewModel;
import com.gsatechworld.musicapp.modules.select_time_slot.pojo.TimeSlot;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.BIWEEKLY;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.Constants.WEEKLY;
import static java.util.Objects.requireNonNull;

public class CoachingDetailsFragment extends Fragment implements OnClickListener,
        OnDaySelectedListener, TimePickerDialog.OnTimeSetListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentCoachingDetailsBinding binding;
    private BaseActivity baseActivity;
    private boolean isHomeSelected, isInstituteSelected, isDailySelected, isBiweeklySelected,
            isWeeklySelected;
    private String address, charges;
    private Dialog dialog;
    private SelectTimeSlotViewModel slotViewModel;
    private TimeSlotsAdapter adapter;
    private DaysAdapter daysAdapter;
    public String trainerID;
    public TimePicker simpleTimePicker;
    public String starttime,endtime;
    public ArrayList<String> timesList;
    public ArrayList<Recurrence_types> recurrence_types;
    private RecyclerView recyclerView;
    public int position;
    public String[] strings;
    public ArrayList<RecyclerData> modelData;
    public TimesListAdapter timesListAdapter;
    public TimeSlotsAdapter slotsAdapter;
    public String days;
    public ArrayList<String> coaching_days;
    public List<String> recurramnce_days;
    public ArrayList<String> rec_days;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Methods
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_coaching_details, container, false);

        modelData=new ArrayList<RecyclerData>();


        slotViewModel=new ViewModelProvider(this).get(SelectTimeSlotViewModel.class);

//        binding.timePickerStart.setIs24HourView(false);
//        binding.timePickerEnd.setIs24HourView(false);

        baseActivity = (BaseActivity) getActivity();

        // recyclerView=binding.recyclerTimeSlots;
        binding.listSelectedTimes.setLayoutManager(new GridLayoutManager(getActivity(),2));
        binding.listSelectedTimes.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.listSelectedTimes.getContext(),
                DividerItemDecoration.VERTICAL);
        //recyclerView.addItemDecoration(dividerItemDecoration);
        binding.listSelectedTimes.addItemDecoration(dividerItemDecoration);


        // fetchTimeSlot();


        /*Setting listeners to the views*/
        binding.textHome.setOnClickListener(this);
        binding.textInstitute.setOnClickListener(this);
        binding.textDaily.setOnClickListener(this);
        binding.textBiweekly.setOnClickListener(this);
        binding.textWeekly.setOnClickListener(this);
        binding.buttonNext.setOnClickListener(this);
        //binding.recyclerTimeSlots.setOnClickListener(this);



//        binding.timePickerStart.setOnTimeChangedListener((view, hourOfDay, minute) -> {
//            binding.textSelectedTime.setText(hourOfDay + ":" +minute);
//            starttime=hourOfDay+":"+minute;
//        });
//        binding.timePickerEnd.setOnTimeChangedListener((view, hourOfDay, minute) -> {
//            endtime=hourOfDay+ ":" +minute;
//        });
        binding.textStarttime.setOnClickListener(v -> {
            DialogFragment dFragment = new TimePickerFragment();
            dFragment.show(getFragmentManager(),"Time Picker");
        });

        binding.textEndtime.setOnClickListener(v -> {
            DialogFragment dFragment = new EndTimepickerFragment();
            dFragment.show(getFragmentManager(),"Time Picker");
        });




        timesList=new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_times,timesList);



        binding.buttonAdd.setOnClickListener(v -> {
            starttime=binding.edtStartTime.getText().toString();
            endtime=binding.edtEndtime.getText().toString();
            String time=starttime + "-" +endtime;

            insertMethod(time);


        });
        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textHome:
                if (isHomeSelected) {
                    isHomeSelected = false;


                    binding.textHome.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textHome.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.md_grey_500));
                    binding.textHome.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isHomeSelected = true;

                    binding.textHome.setTextColor(getResources().getColor(R.color.colorAccent));
                    binding.textHome.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorAccent));
                    binding.textHome.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_selected));
                }
                break;
            case R.id.textInstitute:
                if (isInstituteSelected) {
                    isInstituteSelected = false;

                    binding.textInstitute.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.md_grey_500));
                    binding.textInstitute.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));

                    binding.editAddress.setVisibility(GONE);
                } else {
                    isInstituteSelected = true;

                    binding.textInstitute.setTextColor(getResources().getColor(R.color.colorAccent));
                    binding.textInstitute.setCompoundDrawableTintList
                            (getResources().getColorStateList(R.color.colorAccent));
                    binding.textInstitute.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_selected));

                    binding.editAddress.setVisibility(VISIBLE);
                }
                break;
            case R.id.textDaily:
                if (isDailySelected) {
                    isDailySelected = false;
                    binding.textDaily.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textDaily.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else {
                    isDailySelected = true;
                    isBiweeklySelected = false;
                    isWeeklySelected = false;


                    binding.textDaily.setTextColor(getResources().getColor(R.color.colorAccent));
                    binding.textDaily.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_selected));

                    binding.textBiweekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textBiweekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));

                    binding.textWeekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textWeekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                }
                break;
            case R.id.textBiweekly:
                if (isBiweeklySelected) {
                    isBiweeklySelected = false;

                    binding.textBiweekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textBiweekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else
                    openSelectDaysDialog(BIWEEKLY);
                break;
            case R.id.textWeekly:
                if (isWeeklySelected) {
                    isWeeklySelected = false;

                    binding.textWeekly.setTextColor(getResources().getColor(R.color.md_grey_500));
                    binding.textWeekly.setBackground(requireNonNull(getActivity())
                            .getDrawable(R.drawable.button_rectangle_unselected));
                } else
                    openSelectDaysDialog(WEEKLY);
                break;
            case R.id.buttonNext:
                if (validateFields())
                    returnCoachingDetails();
                break;



        }
    }

    /* ------------------------------------------------------------- *
     * Overriding OnDaySelectedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onDaySelected(String selectedDay, String recurrenceType) {

        if (dialog.isShowing())
            dialog.cancel();

        if (recurrenceType.equals(BIWEEKLY)) {
            isBiweeklySelected = true;
            isDailySelected = false;

            days=selectedDay;
            String[] split_days=days.split("," + "");
            recurramnce_days= Arrays.asList(split_days);
            rec_days = new ArrayList<String>(recurramnce_days);





            binding.textBiweekly.setTextColor(getResources().getColor(R.color.colorAccent));
            binding.textBiweekly.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_selected));

            binding.textDaily.setTextColor(getResources().getColor(R.color.md_grey_500));
            binding.textDaily.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_unselected));
        } else {
            isWeeklySelected = true;
            isDailySelected = false;

            days=selectedDay;
            rec_days = new ArrayList<String>(Arrays.asList(days));

            binding.textWeekly.setTextColor(getResources().getColor(R.color.colorAccent));
            binding.textWeekly.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_selected));

            binding.textDaily.setTextColor(getResources().getColor(R.color.md_grey_500));
            binding.textDaily.setBackground(requireNonNull(getActivity())
                    .getDrawable(R.drawable.button_rectangle_unselected));
        }
    }

    /* ------------------------------------------------------------- *
     * Private Method
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to return user's coaching details to the details activity.
     */


    private String getFormatedTime(String time) {
        DateFormat readFormat = new SimpleDateFormat("hh:mm:a", Locale.getDefault());
        DateFormat writeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String output = null;
        Date date  = null;;

        try {
            date = readFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date != null){
            output = writeFormat.format(date);
        }
        return output;
    }

    /**
     * This method is invoked to open a dialog box where user can select coaching days.
     *
     * @param recurrenceType recurrence type user has selected.
     */
    private void openSelectDaysDialog(String recurrenceType) {
        dialog = new Dialog(requireNonNull(getActivity()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_selected_days);

        /*Initialising Views*/
        ImageView imageClose = dialog.findViewById(R.id.imageClose);
        RecyclerView recyclerDays = dialog.findViewById(R.id.recyclerDays);

        /*Setting listeners to the views*/
        imageClose.setOnClickListener(v -> dialog.cancel());

        coaching_days = new ArrayList<>();

        if (recurrenceType.equals(BIWEEKLY)) {
            coaching_days.add("Monday,Thursday");
            coaching_days.add("Tuesday,Friday");
            coaching_days.add("Wednesday,Saturday");
        } else {
            coaching_days.add("Monday");
            coaching_days.add("Tuesday");
            coaching_days.add("Wednesday");
            coaching_days.add("Thursday");
            coaching_days.add("Friday");
            coaching_days.add("Saturday");
            coaching_days.add("Sunday");
        }

        recyclerDays.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL,
                false));
        recyclerDays.setAdapter(new DaysAdapter(getActivity(), coaching_days, recurrenceType,
                this));

        dialog.show();
    }


    private void returnCoachingDetails() {
        CoachingDetailsListener coachingDetailsListener = (CoachingDetailsListener) getActivity();

        ArrayList<Slot_details> timeSlotes = new ArrayList<>();
        for(int i = 0; i < modelData.size(); i++){
            String [] times = modelData.get(i).starttime.split("-");
            String startTime = getFormatedTime(times[0].replaceAll("\\s+",""));
            String endTime = getFormatedTime(times[1].replaceAll("\\s+",""));
            timeSlotes.add(new Slot_details(startTime, endTime));
        }
        requireNonNull(coachingDetailsListener).coachingDetails(new CoachingDetails(isHomeSelected,
                isInstituteSelected, address, charges, isDailySelected,isBiweeklySelected,isWeeklySelected,rec_days, timeSlotes));
    }

    /**
     * This method is invoked to validate all fields present in this screen.
     *
     * @return validation status of all edit fields.
     */
    private boolean validateFields() {
        charges = requireNonNull(binding.editCharges.getText()).toString().trim();
        address = requireNonNull(binding.editAddress.getText()).toString().trim();

        if (!isHomeSelected && !isInstituteSelected) {
            baseActivity.showErrorSnackBar(requireNonNull(getActivity()), "Please select Coaching Type");
            return false;
        }

        if (isEmpty(charges)) {
            binding.editCharges.setError("Please enter charges");
            return false;
        }

        if (isEmpty(address) && isInstituteSelected) {
            binding.editAddress.setError("Please enter institute address");
            return false;
        }

        if (!isDailySelected && !isBiweeklySelected && !isWeeklySelected) {
            baseActivity.showErrorSnackBar(requireNonNull(getActivity()), "Please select Recurrence Type");
            return false;
        }

        return true;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String status = "AM";

        if(hourOfDay > 11)
        {
            status = "PM";
        }

        int hour_of_12_hour_format;

        if(hourOfDay > 11){

            hour_of_12_hour_format = hourOfDay - 12;
        }
        else {
            hour_of_12_hour_format = hourOfDay;
        }
    }

    /* ------------------------------------------------------------- *
     * Public Interface
     * ------------------------------------------------------------- */

    public interface CoachingDetailsListener {
        void coachingDetails(CoachingDetails coachingDetails);
    }

    public void fetchTimeSlot(){

        slotViewModel.allTimeSlots(trainerID).observe(getActivity(),timeSlotResponse -> {
            if (timeSlotResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)){
//                adapter=new TimeSlotsAdapter(getActivity(), (ArrayList<TimeSlot>) timeSlotResponse.getTimeSlotList());
//                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//                recyclerView.setAdapter(adapter);


            }
        });


    }
    private void insertMethod(String name) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            RecyclerData model = gson.fromJson(String.valueOf(jsonObject), RecyclerData.class);
            modelData.add(new RecyclerData(name));

            timesListAdapter=new TimesListAdapter(getActivity(), modelData, new TimesListAdapter.Onclick() {
                @Override
                public void onEvent(RecyclerData model, int pos) {
                    position = pos;
                }
            });
            binding.listSelectedTimes.setAdapter(timesListAdapter);
            timesListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}