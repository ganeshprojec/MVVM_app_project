package com.jlp.mvvm_jlp_project.view.route_management;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentSummaryBinding;
import com.jlp.mvvm_jlp_project.model.RouteSummary;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.SummaryViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SummaryFragment extends BaseFragment implements View.OnClickListener {

    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;*/
    private @NonNull
    FragmentSummaryBinding binding;
    private SummaryViewModel summaryViewModel;
    private ProgressDialog progressDialog;
    private RouteSummary summary;


    public SummaryFragment() {

    }

    /*public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*initPageParam();*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        summaryViewModel = new ViewModelProvider(this).get(SummaryViewModel.class);

        initObserver(view);
        initListener();

        setToolTitle(getString(R.string.str_route_management));
        callSummaryDetails("R38AM20080913T2");
    }


   /* private void initPageParam() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/


    /**
     * @param routeId routeID came from scanner screen
     * @Author Ganesh
     * <p>
     * For call service summary of Deliveries
     */
    private void callSummaryDetails(String routeId) {

        if (Utils.isInternetAvailable(getActivity())) {
            //prepareEnvelopSummaryDetails(routeId);
            summaryViewModel.callSummaryDetails(routeId);
        } else {
            Utils.hideProgressDialog(progressDialog);
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
        }

    }

    /**
     * @param title title name
     * @Author Ganesh
     * <p>
     * For setting title for the toolbar
     */
    private void setToolTitle(String title) {
        binding.homeTopHeader.txtToolbarTitle.setText("" + title);
    }


    /**
     * @param view
     * @Author Ganesh
     * <p>
     * For setting data observer for the webservice response
     */
    private void initObserver(View view) {

        summaryViewModel.responseSummaryDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataRouteManagementSummary>>() {
            @Override
            public void onChanged(Resource<ResponseDataRouteManagementSummary> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            //Log.e("errorSummary",""+response.message);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {

                            Utils.hideProgressDialog(progressDialog);
                            summary = response.data.getRouteSummary();
                            updateSummaryOnView();
                            break;
                        }
                    }
                }
            }
        });


    }

    /**
     * @param
     * @Author Ganesh
     * <p>
     * For updating summary on UI
     */
    public void updateSummaryOnView() {
        // Header
        binding.layoutSummaryInclHeader.txtRouteNumberValue.setText("" + summary.getRouteNumber());
        binding.layoutSummaryInclHeader.txtDeliveryDateValue.setText("" + summary.getFormattedDeliveryDate());

        // Summary
        binding.layoutSummaryIncl.txTotalDeliveriesValue.setText("" + summary.getTotalNumberOfDeliveriesCount());//summary.getTotalDeliveries()
        binding.layoutSummaryIncl.txTotalLotsValue.setText("" + summary.getTotalLots());
        binding.layoutSummaryIncl.txTotalLotsLoadedValue.setText("" + summary.getDeliveryLotsLoaded());
    }


    /**
     * @param index position of list
     * @param model Data over menu item
     * @Author Ganesh
     * <p>
     * For click listenr, initialization of objects.
     */
    private void initListener() {
        //binding.homeTopheader.imgClose.setOnClickListener(this);

    }


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSummaryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    /**
     * @param view
     * @Author Ganesh
     * <p>
     * For click listeners of the page view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }
}
