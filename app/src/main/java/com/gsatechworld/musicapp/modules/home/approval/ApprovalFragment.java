package com.gsatechworld.musicapp.modules.home.approval;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.FragmentApprovalBinding;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter;
import com.gsatechworld.musicapp.modules.home.approval.adapter.ApproveStudentAdapter.OnActionPerformedListener;
import com.gsatechworld.musicapp.modules.home.approval.pojo.ActionInfo;

import static androidx.databinding.DataBindingUtil.inflate;
import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.gsatechworld.musicapp.utilities.Constants.SERVER_RESPONSE_SUCCESS;
import static com.gsatechworld.musicapp.utilities.NetworkUtilities.getNetworkInstance;
import static java.util.Objects.requireNonNull;

public class ApprovalFragment extends Fragment implements OnQueryTextListener,
        OnActionPerformedListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private FragmentApprovalBinding binding;
    private ApprovalViewModel viewModel;
    private BaseActivity baseActivity;
    private ApproveStudentAdapter adapter;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Method
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Binding layout file with JAVA class*/
        binding = inflate(inflater, R.layout.fragment_approval, container, false);

        /*Binding layout file with JAVA class*/
        viewModel = new ViewModelProvider(this).get(ApprovalViewModel.class);

        /*Setting Screen title*/
        binding.layoutBase.toolbar.setTitle("Approval");

        baseActivity = (BaseActivity) getActivity();

        fetchApprovalList();

        /*Setting listeners to the views*/
        binding.searchStudent.setOnQueryTextListener(this);

        return binding.getRoot();
    }

    /* ------------------------------------------------------------- *
     * Overriding OnQueryTextListener Methods
     * ------------------------------------------------------------- */

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (adapter != null)
            adapter.filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (adapter != null)
            adapter.filter(newText);
        return true;
    }

    /* ------------------------------------------------------------- *
     * Overriding OnActionPerformedListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onActionPerformed(String requestID, String action) {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            viewModel.storeAction(new ActionInfo(requestID, action)).observe(getViewLifecycleOwner(),
                    commonResponse -> {
                        baseActivity.hideLoadingIndicator();

                        if (commonResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS))
                            fetchApprovalList();

                        baseActivity.showSnackBar(requireNonNull(getActivity()),
                                commonResponse.getMessage());
                    });
        } else
            baseActivity.showSnackBar(requireNonNull(getActivity()),
                    getString(R.string.no_internet_message));
    }

    /* ------------------------------------------------------------- *
     * Private Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to fetch a list of all request send to the trainer by students to join
     * his/her class.
     */
    private void fetchApprovalList() {
        if (getNetworkInstance(getActivity()).isConnectedToInternet()) {
            baseActivity.showLoadingIndicator();

            String trainerID = "1";
            viewModel.fetchApprovalList(trainerID).observe(getViewLifecycleOwner(),
                    approvalResponse -> {
                        baseActivity.hideLoadingIndicator();

                        if (approvalResponse.getResponse().equals(SERVER_RESPONSE_SUCCESS)) {
                            binding.recyclerRequest.setLayoutManager(new
                                    LinearLayoutManager(getActivity(), VERTICAL, false));

                            adapter = new ApproveStudentAdapter(getActivity(), approvalResponse
                                    .getApprovalList(), this);
                            binding.recyclerRequest.setAdapter(adapter);
                        } else
                            baseActivity.showSnackBar(requireNonNull(getActivity()),
                                    approvalResponse.getMessage());
                    });
        } else
            baseActivity.showSnackBar(requireNonNull(getActivity()),
                    getString(R.string.no_internet_message));
    }
}