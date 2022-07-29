package com.jlp.mvvm_jlp_project.view.common_printer_list;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlp.mvvm_jlp_project.R;

import com.jlp.mvvm_jlp_project.adapters.CommonBarcodeScannerAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonPrinterListBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.PrinterListModel;
import com.jlp.mvvm_jlp_project.model.RouteSummary;
import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestBodyPrinterList;
import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestDataPrinterList;
import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestEnveloperPrinterList;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestBodyRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestEnvelopRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;
import com.jlp.mvvm_jlp_project.model.response.record_location_of_item.ResponseDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.utils.AppConstants;

import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CommonPrinterViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CommonPrinterListFragment extends BaseFragment {

    FragmentCommonPrinterListBinding binding;

    private CommonPrinterViewModel commonPrinterViewModel;
    private ProgressDialog progressDialog;
    private String callForPrint;
    public static String actionBarTitle;
    public static String screenCall;
    private ArrayList<PrinterDetails> printerDataList = new ArrayList<>();
    CommonPrinterListAdapter adapter;
    private PrinterDetails printerData;



    public CommonPrinterListFragment(String callForPrint) {
        this.callForPrint = callForPrint;
    }


    @Inject
    RequestEnveloperPrinterList requestEnvelop;

    @Inject
    RequestBodyPrinterList requestBody;

    @Inject
    RequestDataPrinterList requestData;

    @Inject
    PrinterDetails printerDetails;


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommonPrinterListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commonPrinterViewModel = new ViewModelProvider(this).get(CommonPrinterViewModel.class);
        binding.amaendLotsTopHeader.txtToolbarTitle.setText(R.string.str_amend_lots);

        updateActionbarTitle();
        initObserver();
        initListener();

    }


    private void updateActionbarTitle() {
        switch (callForPrint) {
            case AppConstants.FRAGMENT_AMEND_LOTS: {

                actionBarTitle = getResources().getString(R.string.str_amend_lots);

                binding.amaendLotsTopHeader.txtToolbarTitle.setText(actionBarTitle);
                screenCall = AppConstants.FRAGMENT_AMEND_LOTS;
                getPrinterListData("3822");

                setupAdapter();
                break;
            }
            case AppConstants.FRAGMENT_REPRINT_LABELS: {

                actionBarTitle = getResources().getString(R.string.str_reprint_labels);

                binding.amaendLotsTopHeader.txtToolbarTitle.setText(actionBarTitle);
                screenCall = AppConstants.FRAGMENT_REPRINT_LABELS;
                getPrinterListData("3822");
                setupAdapter();
                break;
            }
        }
    }

    private void getPrinterListData(String deliveryCenterNumebr) {
        prepareEnvelopPrinterDetails(deliveryCenterNumebr);
        commonPrinterViewModel.getPrinterList(requestEnvelop);
    }

    private void prepareEnvelopPrinterDetails(String deliveryCenterNumebr)
    {
        requestData.setDeliveryCentreNumber(deliveryCenterNumebr);
        requestBody.setRequestDataPrinterList(requestData);
        requestEnvelop.setRequestBodyPrinterList(requestBody);
    }


    private void initListener(){}

    private void initObserver(){

        commonPrinterViewModel.responseDataPrinterList.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataPrinterList>>() {
            @Override
            public void onChanged(Resource<ResponseDataPrinterList> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR:{
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.update_has_failed)+"\n"+response.message);
                            break;
                        }
                        case SUCCESS:{
                            Utils.hideProgressDialog(progressDialog);
                            printerDataList.add(response.data.printerDetails);
                            adapter.notifyDataSetChanged();
                             break;
                        }
                    }
                }
            }
        });
    }




    private void setupAdapter()
            {
                if (printerDataList != null) {

                    adapter =  new CommonPrinterListAdapter(printerDataList, getContext(), CommonPrinterListFragment.this,screenCall);
                    binding.recyclerViewCommonPrinterList.setAdapter(adapter);
                    binding.recyclerViewCommonPrinterList.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Utils.showErrorMessage(getActivity(), "Unable to get the data");
                }
            }

}


