package com.gsatechworld.musicapp.modules.student_home.student_settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.databinding.FragmentSettings2Binding;
import com.gsatechworld.musicapp.modules.login.LoginActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.gsatechworld.musicapp.core.manager.SessionManager.getSessionInstance;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public FragmentSettings2Binding settings2Binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settings2Binding = inflate(inflater, R.layout.fragment_settings2, container, false);

        settings2Binding.layoutBase.toolbar.setTitle("Settings");
        settings2Binding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        settings2Binding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
                    getActivity().onBackPressed();

        });
        settings2Binding.logout.setOnClickListener(v -> {
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

        return settings2Binding.getRoot();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
