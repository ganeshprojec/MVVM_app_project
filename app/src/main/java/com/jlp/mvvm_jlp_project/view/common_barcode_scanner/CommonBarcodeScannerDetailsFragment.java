package com.jlp.mvvm_jlp_project.view.common_barcode_scanner;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.CommonAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonBarcodeScannerDetailsBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.ReprintLabelDetails;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestBodyRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestEnvelopeRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.ReprintLabelDetailsReq;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestBodyReprintLabel;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestDataReprintLabel;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestEnvelopeReprintLabel;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.LotDetails;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.RequestBodyAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.RequestDataAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.RequestEnvelopeAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.DeliveryItemDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.record_location_of_item.ResponseDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.response.reprint_label_detail.ResponseDataReprintLabel;
import com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response.ResponseDataAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.pref.AppPreferencesHelper;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;

import com.jlp.mvvm_jlp_project.view.reprint_labels.ReprintLabelItemListViewModel;
import com.jlp.mvvm_jlp_project.viewmodel.CommonBarcodeScannerViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CommonBarcodeScannerDetailsFragment extends BaseFragment{
    private static final String TAG = CommonBarcodeScannerDetailsFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private DeliveryItemProductDetails deliveryItemProductDetails = CommonBarcodeScannerFragment.deliveryItemProductDetails;
    private LocationDetails locationDetails = CommonBarcodeScannerFragment.locationDetails;
    private DeliveryItemDetails deliveryItemDetails = CommonBarcodeScannerFragment.deliveryItemDetails;
    private FragmentCommonBarcodeScannerDetailsBinding binding;
    private CommonBarcodeScannerViewModel viewModel;
    private ReprintLabelItemListViewModel reprintLabelItemListViewModel;
    private String callFor,totalLotNum;
    // Used for adapter and list to setup
    private CommonAdapter adapter;
    private List<TitleValueDataModel> detailsDataList =  new ArrayList<>();
    public  ReprintLabelDetails reprintLabelDetails;

    @Inject
    RequestEnvelopeRecordLocationOfItem requestEnvelopeRecordLocation;
    @Inject
    RequestBodyRecordLocationOfItem requestBodyRecordLocation;
    @Inject
    RequestDataRecordLocationOfItem requestDataRecordLocation;
    @Inject
    LocationItemDetails locationItemDetails;

// UpdateLot
    @Inject
    RequestEnvelopeAmendLotNumerUpdate requestEnvelopeAmendLotNumerUpdate;
    @Inject
    RequestBodyAmendLotNumerUpdate requestBodyAmendLotNumerUpdate;
    @Inject
    RequestDataAmendLotNumerUpdate requestDataAmendLotNumerUpdate;
    @Inject
    LotDetails lotDetails;

    // ReprintLabel
    @Inject
    RequestBodyReprintLabel requestBodyReprintLabel;
    @Inject
    RequestDataReprintLabel requestDataReprintLabel;
    @Inject
    RequestEnvelopeReprintLabel requestEnvelopeReprintLabel;
    @Inject
    PrinterDetails printerDetails;
    @Inject
    ReprintLabelDetailsReq reprintLabelDetailsReq;

    @Inject
    AppPreferencesHelper appPreferencesHelper;


    public CommonBarcodeScannerDetailsFragment(String callFor) {
        this.callFor = callFor;
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommonBarcodeScannerDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CommonBarcodeScannerViewModel.class);
        reprintLabelItemListViewModel = new ViewModelProvider(this).get(ReprintLabelItemListViewModel.class);
        updateActionbarTitleAndLocationLayout();
        initListener();
        initObserver();
        recordLocationOfItem(deliveryItemProductDetails, locationDetails);
        initListAndRecyclerView();
        setupAdapter();
    }

    /**
     * Prepare the data list for recycler view depending on fragment from which it calls
     */
    private void initListAndRecyclerView() {
        switch (callFor){
            case AppConstants.FRAGMENT_AMEND_LOTS:{       // to show input field on amend lots detail page
                binding.textTitle.setVisibility(View.VISIBLE);
                binding.inputLotsRequired.setVisibility(View.VISIBLE);
                viewModel.getAmendLotItemDetails(deliveryItemDetails);
              // viewModel.getComponentBarcodeData(deliveryItemProductDetails);
                binding.itemEnquiryHeader.imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Helper.clearBackStack(getActivity());
                    }
                });
                break;
            }
            case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE:
            case AppConstants.FRAGMENT_ITEM_ENQUIRY:{
                viewModel.getComponentBarcodeData(deliveryItemProductDetails);
                break;
            }
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:{
                viewModel.getItemDetailsComponentBarcodeData(deliveryItemDetails);
                break;
            }
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:
            case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE:{
                viewModel.getLocationBarcodeData(deliveryItemProductDetails, locationDetails);
                break;
            }
        }
    }

    /**
     * Set up the adapter and set the data for list items
     */
    private void setupAdapter(){
        if(detailsDataList!=null){
            adapter = new CommonAdapter(detailsDataList, getContext());
            binding.recyclerView.setAdapter(adapter);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            if(deliveryItemDetails!=null)totalLotNum = deliveryItemDetails.getTotalLotNumber(); else totalLotNum =  "";
            adapter.notifyDataSetChanged();
        }else{
            Utils.showErrorMessage(getActivity(), "Unable to get the data");
        }
    }

    /**
     * Initialization of next button click listener and handle as per the fragment from which it calling
     */
    private void initListener() {
        binding.scanNextItemBarcode.imgNextScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (callFor){
                    case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE:{
                        replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE));
                        break;
                    }
                    case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE:{
                        replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE));
                        break;
                    }
                    case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:
                    case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:{
                        replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE));
                        break;
                    }
                    default:{
                        replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_ITEM_ENQUIRY));
                        break;
                    }
                }
            }
        });

        //Amend Lot detail page printer button click event
        binding.btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Helper.hideKeyboard(getActivity(), view);
                viewModel.validateLotNumberRequired(binding.inputLotsRequired.getText().toString().trim(),deliveryItemDetails.totalLotNumber);

            }
        });

    }

    /**
     * Actual API call for record location of item  from view depending on the condition from which this fragment is calling
     * @param deliveryItemProductDetails to make the request for api call
     * @param locationDetails to make the request for api call
     */
    private void recordLocationOfItem(DeliveryItemProductDetails deliveryItemProductDetails,
                                      LocationDetails locationDetails){
        if(callFor.equals(AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE)
        || callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE)
        || callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE)){
            if (Utils.isInternetAvailable(getContext())){
                prepareRequestDataForRecordLocationOfItem(deliveryItemProductDetails, locationDetails);
                viewModel.recordLocationOfItem(requestEnvelopeRecordLocation);
            }else{
                Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
            }
        }
    }

    /**
     * Preparing the request envelope, body, data for api call for record location of item
     * @param deliveryItemProductDetails
     * @param locationDetails
     */
    private void prepareRequestDataForRecordLocationOfItem(DeliveryItemProductDetails deliveryItemProductDetails,
                                                           LocationDetails locationDetails) {
        if(deliveryItemProductDetails!=null && locationDetails!=null){
            locationItemDetails.setUserId(appPreferencesHelper.getUserId());
            locationItemDetails.setUserName(appPreferencesHelper.getUsername());
            locationItemDetails.setLocationId(locationDetails.getLocationId());
            locationItemDetails.setName15(locationDetails.getName15());
            locationItemDetails.setProductCode(deliveryItemProductDetails.getProductCode());
            locationItemDetails.setDeliveryId(deliveryItemProductDetails.getDeliveryId());
            locationItemDetails.setComponentId(deliveryItemProductDetails.getComponentId());
        }
        requestDataRecordLocation.setLocationItemDetails(locationItemDetails);
        requestBodyRecordLocation.setRequestDataRecordLocationOfItem(requestDataRecordLocation);
        requestEnvelopeRecordLocation.setRequestBodyRecordLocationOfItem(requestBodyRecordLocation);
    }

    /**
     * Set actionbar title and update location layout items if call is for multi movement feature
     */
    private void updateActionbarTitleAndLocationLayout() {
        binding.itemEnquiryHeader.txtToolbarTitle.setText(CommonBarcodeScannerFragment.actionBarTitle);
        if(CommonBarcodeScannerFragment.locationLayoutFlag){
            updateLocationLayout(locationDetails);
        }
        if(CommonBarcodeScannerFragment.locationAmendLotFlag){
            updateAmendLotLayout();
        }
    }

    private void updateAmendLotLayout()
    {
        binding.btnPrint.setVisibility(View.VISIBLE);
        binding.scanNextItemBarcode.scanItemBarcode.setVisibility(View.GONE);
    }

    /**
     * setup all the observer of app for api call and adapter list data
     */
    private void updateLotNumberRequire(String lotNumberRequire, DeliveryItemDetails deliveryItemDetails)
    {
        prepareRequestDataForUpdateLotNumRequired(lotNumberRequire,deliveryItemDetails);
        viewModel.findUpdateLotNumRequired(requestEnvelopeAmendLotNumerUpdate);
    }
    private void prepareRequestDataForUpdateLotNumRequired(String lotNumberRequire,DeliveryItemDetails deliveryItemDetails) {

        if(deliveryItemDetails!=null){

            lotDetails.setComponentId(deliveryItemDetails.getComponentId());
            lotDetails.setGoodId(deliveryItemDetails.getGoodId());
            lotDetails.setProductCode(deliveryItemDetails.getProductCode());
            lotDetails.setRequestedLot(lotNumberRequire);
            lotDetails.setUserId("UserId");
            lotDetails.setUserName("UserName");
            lotDetails.setDeliveryId(deliveryItemDetails.getDeliveryId());
          }
        requestDataAmendLotNumerUpdate.setLotDetails(lotDetails);
        requestBodyAmendLotNumerUpdate.setRequestDataAmendLotNumerUpdate(requestDataAmendLotNumerUpdate);
        requestEnvelopeAmendLotNumerUpdate.setRequestBodyAmendLotNumerUpdate(requestBodyAmendLotNumerUpdate);
    }


    private void initObserver() {

        viewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first)
                {
                    updateLotNumberRequire(binding.inputLotsRequired.getText().toString().trim(),deliveryItemDetails);
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });

        viewModel.responseDataRecordLocationOfItem.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataRecordLocationOfItem>>() {
            @Override
            public void onChanged(Resource<ResponseDataRecordLocationOfItem> response) {
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
                            viewModel.updateAdapterData(response.data.locationItemDetails, locationDetails);
                            break;
                        }
                    }
                }
            }
        });

        viewModel.itemEnquiry.observe(getViewLifecycleOwner(), new Observer<List<TitleValueDataModel>>() {
            @Override
            public void onChanged(List<TitleValueDataModel> titleValueDataModels) {
                detailsDataList.clear();
                detailsDataList.addAll(titleValueDataModels);
                adapter.notifyDataSetChanged();
            }
        });


        /*updateLots*/
         viewModel.responseDataAmendLotNumberUpdate.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataAmendLotNumerUpdate>>() {
            @Override
            public void onChanged(Resource<ResponseDataAmendLotNumerUpdate> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR:{
                            Utils.hideProgressDialog(progressDialog);
                            //response.message after update failed;
                            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.update_has_failed)+"\n"+getResources().getString(R.string.reduce_no_of_lots));
                            break;
                        }
                        case SUCCESS:{
                            Utils.hideProgressDialog(progressDialog);
                            viewModel.updateAmendLotNumAdapterData(response.data.getLotDetails(),deliveryItemDetails);
                            //ReprintLabel API and get response dialog.
                            callfindReprintLabelDetail(printerDetails,deliveryItemDetails);
                            break;
                        }
                    }
                }
            }
        });
         // response for reprint label after lot number update
        reprintLabelItemListViewModel.responseDataReprintLabel.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataReprintLabel>>() {
            @Override
            public void onChanged(Resource<ResponseDataReprintLabel> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING:{
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR:{
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS:{
                            Utils.hideProgressDialog(progressDialog);
                            binding.inputLotsRequired.setText("");
                            reprintLabelDetails=response.data.getReprintLabelDetails();
                            Utils.showAmendAlertDialog(getActivity(),deliveryItemDetails.deliveryId,reprintLabelDetails.getLabelsPrinted(),reprintLabelDetails.getPrinterId(),callFor);
                            break;
                        }
                    }
                }
            }
        });

    }


    //set ReprintLabel data for send request

    public void callfindReprintLabelDetail(PrinterDetails printerDetails,DeliveryItemDetails deliveryItemDetails )
    {
        prepareRequestDataForFindReprintLabelDetail(printerDetails,deliveryItemDetails);
        reprintLabelItemListViewModel.findReprintLabelDetail(requestEnvelopeReprintLabel);
    }
    public void prepareRequestDataForFindReprintLabelDetail(PrinterDetails printerDetails, DeliveryItemDetails deliveryItemDetails)
    {
        if(deliveryItemDetails!=null && printerDetails!=null )
        {
            reprintLabelDetailsReq.setPrinterId(printerDetails.getPrinterId());
            reprintLabelDetailsReq.setDeliveryGoodId(deliveryItemDetails.getGoodId());
            reprintLabelDetailsReq.setDeliveryId(deliveryItemDetails.getDeliveryId());

        }
        requestDataReprintLabel.setReprintLabelDetails(reprintLabelDetailsReq);
        requestBodyReprintLabel.setRequestDataReprintLabel(requestDataReprintLabel);
        requestEnvelopeReprintLabel.setRequestBodyReprintLabel(requestBodyReprintLabel);
    }


    /**
     * This function helps to deal with header which is bottom to actionbar
     * For Multi Movement feature location layout and location name shown
     * @param locationDetails shown location name from this object
     */
    private void updateLocationLayout(LocationDetails locationDetails) {
        binding.scanNextItemBarcode.tvSelectedLocation.setVisibility(View.VISIBLE);
        binding.scanNextItemBarcode.tvSelectedLocation.setText(getResources().getString(R.string.selected_location)+": "+locationDetails.name15);
    }

    // TODO: NavController we have to use for this
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}