package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.jlp.mvvm_jlp_project.adapters.CommonBarcodeScannerAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonBarcodeScannerDetailsBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestBodyRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestEnvelopeRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.record_location_of_item.ResponseDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CommonBarCodeLocationScannerViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

//TODO:Use viewModel for data manipulation, Manage conditions to handle the data
@AndroidEntryPoint
public class CommonBarcodeScannerDetailsFragment extends BaseFragment{
    private static final String TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private DeliveryItemProductDetails deliveryItemProductDetails = CommonBarcodeScannerFragment.deliveryItemProductDetails;
    private LocationDetails locationDetails = CommonBarcodeScannerFragment.locationDetails;
    private FragmentCommonBarcodeScannerDetailsBinding binding;
    private CommonBarCodeLocationScannerViewModel commonBarCodeLocationScannerViewModel;
    private String callFor;
    private CommonBarcodeScannerAdapter adapter;
    private List<ItemEnquiryModel> detailsDataList =  new ArrayList<>();

    @Inject
    RequestEnvelopeRecordLocationOfItem requestEnvelopeRecordLocationOfItem;
    @Inject
    RequestBodyRecordLocationOfItem requestBodyRecordLocationOfItem;
    @Inject
    RequestDataRecordLocationOfItem requestDataRecordLocationOfItem;
    @Inject
    LocationItemDetails locationItemDetails;

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
        commonBarCodeLocationScannerViewModel = new ViewModelProvider(this).get(CommonBarCodeLocationScannerViewModel.class);
        updateActionbarTitleAndLocationLayout();
        initListener();
        initObserver();
        recordLocationOfItem(deliveryItemProductDetails, locationDetails);
        initListAndRecyclerView();
    }

    private void initListAndRecyclerView() {
        switch (callFor){
            case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE:
            case AppConstants.FRAGMENT_ITEM_ENQUIRY:{
                detailsDataList.addAll(getComponentBarcodeData(deliveryItemProductDetails));
                break;
            }
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:
            case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE:{
                detailsDataList.addAll(getLocationBarcodeData(deliveryItemProductDetails, locationDetails));
                break;
            }
        }
        setupAdapter();
    }

    private void setupAdapter(){
        if(detailsDataList!=null){
            adapter = new CommonBarcodeScannerAdapter(detailsDataList, getContext());
            binding.recyclerView.setAdapter(adapter);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            Utils.showErrorMessage(getActivity(), "Unable to get the data");
        }
    }

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
    }

    private void recordLocationOfItem(DeliveryItemProductDetails deliveryItemProductDetails,
                                      LocationDetails locationDetails){
        if(callFor.equals(AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE)
        || callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE)
        || callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE)){
            prepareRequestDataForRecordLocationOfItem(deliveryItemProductDetails, locationDetails);
            commonBarCodeLocationScannerViewModel.recordLocationOfItem(requestEnvelopeRecordLocationOfItem);
        }
    }

    private void prepareRequestDataForRecordLocationOfItem(DeliveryItemProductDetails deliveryItemProductDetails,
                                                           LocationDetails locationDetails) {
        if(deliveryItemProductDetails!=null && locationDetails!=null){
            //TODO: get user id and username from SP
            locationItemDetails.setUserId("UserId");
            locationItemDetails.setUserName("UserName");
            locationItemDetails.setLocationId(locationDetails.getLocationId());
            locationItemDetails.setName15(locationDetails.getName15());
            locationItemDetails.setProductCode(deliveryItemProductDetails.getProductCode());
            locationItemDetails.setDeliveryId(deliveryItemProductDetails.getDeliveryId());
            locationItemDetails.setComponentId(deliveryItemProductDetails.getComponentId());
        }
        requestDataRecordLocationOfItem.setLocationItemDetails(locationItemDetails);
        requestBodyRecordLocationOfItem.setRequestDataRecordLocationOfItem(requestDataRecordLocationOfItem);
        requestEnvelopeRecordLocationOfItem.setRequestBodyRecordLocationOfItem(requestBodyRecordLocationOfItem);
    }

    private void updateActionbarTitleAndLocationLayout() {
        binding.itemEnquiryHeader.txtToolbarTitle.setText(CommonBarcodeScannerFragment.actionBarTitle);
        if(CommonBarcodeScannerFragment.locationLayoutFlag){
            updateLocationLayout(locationDetails);
        }
    }

    //TODO it should be from ViewModel
    public List<ItemEnquiryModel> getComponentBarcodeData(DeliveryItemProductDetails deliveryItemProductDetails)
    {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel(getResources().getString(R.string.delivery_number),
                deliveryItemProductDetails.getDeliveryId()
        ));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.route_number),
                deliveryItemProductDetails.getRouteResourceKey()
        ));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.delivery_date),
                deliveryItemProductDetails.getDeliveryDate()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.last_recorded_location),
                deliveryItemProductDetails.getDeliveryAddressPremise()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.time_of_last_move),
                deliveryItemProductDetails.getLastUpdatedTimeStamp()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.last_user_id),
                deliveryItemProductDetails.getLastUpdatedUserId()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.product_code),
                deliveryItemProductDetails.getProductCode()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.product_description),
                deliveryItemProductDetails.getOrderDescriptionClean()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.lot_number),
                deliveryItemProductDetails.getCurrentLotNumber()));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.address),
                deliveryItemProductDetails.getDeliveryAddressLocality()));
        return list;
    }

    //TODO it should be from ViewModel
    private List<ItemEnquiryModel> getLocationBarcodeData(DeliveryItemProductDetails deliveryItemProductDetails, LocationDetails locationDetails) {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel(getResources().getString(R.string.delivery_number),
                deliveryItemProductDetails.getDeliveryId()
        ));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.route_number),
                deliveryItemProductDetails.getRouteResourceKey()
        ));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.item),
                "08 Aug 2022"));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.product_description),
                deliveryItemProductDetails.getOrderDescriptionClean()));
        return list;
    }

    /**
     * setup all the observer of app for api call
     */
    private void initObserver() {
        commonBarCodeLocationScannerViewModel.responseDataRecordLocationOfItem.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataRecordLocationOfItem>>() {
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
                            updateAdapter(response.data.locationItemDetails, locationDetails);
                            break;
                        }
                    }
                }
            }
        });
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

    /**
     * Update lot number and stored location once api call is success
     * @param locationItemDetails
     * @param locationDetails
     */
    private void updateAdapter(LocationItemDetails locationItemDetails, LocationDetails locationDetails) {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel(getResources().getString(R.string.lot_number),
                locationItemDetails.getCurrentLotNumber()+" of "+locationItemDetails.getTotalLotNumber()
        ));
        list.add(new ItemEnquiryModel(getResources().getString(R.string.stored_in_location),
                locationDetails.getName15()
        ));
        detailsDataList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    // TODO: NavController we have to use for this
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}