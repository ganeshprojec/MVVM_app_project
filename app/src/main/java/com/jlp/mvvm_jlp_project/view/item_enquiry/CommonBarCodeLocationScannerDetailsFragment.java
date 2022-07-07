package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.jlp.mvvm_jlp_project.adapters.ItemEnquiryAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonBarcodeLocationScannerDetailsBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestBodyRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestEnvelopeRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
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
public class CommonBarCodeLocationScannerDetailsFragment extends BaseFragment{
    private static final String TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private DeliveryItemProductDetails deliveryItemProductDetails;
    private LocationDetails locationDetails;
    private FragmentCommonBarcodeLocationScannerDetailsBinding binding;
    private CommonBarCodeLocationScannerViewModel commonBarCodeLocationScannerViewModel;
    private String callFor;

    @Inject
    RequestEnvelopeRecordLocationOfItem requestEnvelopeRecordLocationOfItem;
    @Inject
    RequestBodyRecordLocationOfItem requestBodyRecordLocationOfItem;
    @Inject
    RequestDataRecordLocationOfItem requestDataRecordLocationOfItem;
    @Inject
    LocationItemDetails locationItemDetails;

    public CommonBarCodeLocationScannerDetailsFragment(String callFor) {
        this.callFor = callFor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commonBarCodeLocationScannerViewModel = new ViewModelProvider(this).get(CommonBarCodeLocationScannerViewModel.class);
        updateActionbarTitle();
        getParcelableData();
        initListener();
        initObserver();
        recordLocationOfItem();
        initListAndRecyclerView();
    }

    private void initListAndRecyclerView() {
        List<ItemEnquiryModel> dataList = null;
        switch (callFor){
            case AppConstants.FRAGMENT_ITEM_MOVEMENT:
            case AppConstants.FRAGMENT_ITEM_ENQUIRY:{
                dataList = getComponentBarcodeData(deliveryItemProductDetails);
                break;
            }
            case AppConstants.FRAGMENT_LOCATION_SCAN:{
                dataList = getLocationBarcodeData(locationDetails);
                break;
            }
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:{
                //dataList = getRecordLocationOfItemData();
                break;
            }
        }
        setUpAdapter(dataList);
    }

    private void setUpAdapter(List<ItemEnquiryModel> dataList){
        if(dataList!=null){
            ItemEnquiryAdapter adapter;
            adapter = new ItemEnquiryAdapter(dataList, getContext());
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
                    case AppConstants.FRAGMENT_ITEM_MOVEMENT:{
                        replaceFragment(new CommonBarCodeLocationScannerFragment(AppConstants.FRAGMENT_LOCATION_SCAN));
                        break;
                    }
                    default:{
                        replaceFragment(new CommonBarCodeLocationScannerFragment(AppConstants.FRAGMENT_ITEM_ENQUIRY));
                        break;
                    }
                }
            }
        });
    }

    private void getParcelableData() {
        try {
            if (getArguments() != null) {
                Bundle bundle = this.getArguments();
                if (bundle != null) {
                    deliveryItemProductDetails = bundle.getParcelable(AppConstants.COMPONENT_BARCODE_DETAILS_DATA);
                    locationDetails = bundle.getParcelable(AppConstants.LOCATION_BARCODE_DETAILS_DATA);
                }
            }
        }catch (Exception ex){
            Log.e(TAG, "Exception: "+ex.getMessage());
        }
    }

    private void recordLocationOfItem(){
        if(callFor.equals(AppConstants.FRAGMENT_LOCATION_SCAN)
        || callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE)
        || callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE)){
            prepareRequestDataForRecordLocationOfItem();
            commonBarCodeLocationScannerViewModel.recordLocationOfItem(requestEnvelopeRecordLocationOfItem);
        }
    }

    private void prepareRequestDataForRecordLocationOfItem() {
        locationItemDetails.setUserId("UserId");
        locationItemDetails.setUserName("UserName");

        requestDataRecordLocationOfItem.setLocationItemDetails(locationItemDetails);
        requestBodyRecordLocationOfItem.setRequestDataRecordLocationOfItem(requestDataRecordLocationOfItem);
        requestEnvelopeRecordLocationOfItem.setRequestBodyRecordLocationOfItem(requestBodyRecordLocationOfItem);
    }

    private void updateActionbarTitle() {
        switch (callFor){
            case AppConstants.FRAGMENT_LOCATION_SCAN:
            case AppConstants.FRAGMENT_ITEM_MOVEMENT:{
                binding.itemEnquiryHeader.txtToolbarTitle.setText(getResources().getString(R.string.item_movement_title));
                break;
            }
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:{
                binding.itemEnquiryHeader.txtToolbarTitle.setText(getResources().getString(R.string.multi_movement_title));
                break;
            }
            default:{
                binding.itemEnquiryHeader.txtToolbarTitle.setText(getResources().getString(R.string.item_enquiry_title));
                break;
            }
        }
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommonBarcodeLocationScannerDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //TODO it should be from ViewModel
    public List<ItemEnquiryModel> getComponentBarcodeData(DeliveryItemProductDetails itemEnquiryDetailsData)
    {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel("Delivery Number",
                itemEnquiryDetailsData.getDeliveryId()
        ));
        list.add(new ItemEnquiryModel("Route Number",
                itemEnquiryDetailsData.getRouteResourceKey()
        ));
        list.add(new ItemEnquiryModel("Delivery Date",
                itemEnquiryDetailsData.getDeliveryDate()));
        list.add(new ItemEnquiryModel("Last Recorded Location",
                itemEnquiryDetailsData.getDeliveryAddressPremise()));
        list.add(new ItemEnquiryModel("Time of last move",
                itemEnquiryDetailsData.getLastUpdatedTimeStamp()));
        list.add(new ItemEnquiryModel("Last UserId",
                itemEnquiryDetailsData.getLastUpdatedUserId()));
        list.add(new ItemEnquiryModel("Product Code",
                itemEnquiryDetailsData.getProductCode()));
        list.add(new ItemEnquiryModel("Product description",
                itemEnquiryDetailsData.getOrderDescriptionClean()));
        list.add(new ItemEnquiryModel("Lot Number",
                itemEnquiryDetailsData.getCurrentLotNumber()));
        list.add(new ItemEnquiryModel("Address",
                itemEnquiryDetailsData.getDeliveryAddressLocality()));
        return list;
    }

    //TODO it should be from ViewModel
    private List<ItemEnquiryModel> getLocationBarcodeData(LocationDetails locationDetails) {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel("Delivery Number",
                locationDetails.locationId
        ));
        list.add(new ItemEnquiryModel("Route Number",
                locationDetails.getName15()
        ));
        list.add(new ItemEnquiryModel("Item",
                "08 Aug 2022"));
        list.add(new ItemEnquiryModel("Product description",
                "New product added in the list for shopping. User can shop by using application"));
        list.add(new ItemEnquiryModel("Lot Number",
                "123"));
        list.add(new ItemEnquiryModel("Stored in location",
                "Bay 4"));
        return list;
    }

    private List<ItemEnquiryModel> getRecordLocationOfItemData(LocationItemDetails locationItemDetails) {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel("Delivery Number",
                locationItemDetails.deliveryId
        ));
        list.add(new ItemEnquiryModel("Route Number",
                locationItemDetails.getLocationId()
        ));
        list.add(new ItemEnquiryModel("Item",
                locationItemDetails.getProductCode()));
        list.add(new ItemEnquiryModel("Product description",
                locationItemDetails.getProductCode()));

        list.add(new ItemEnquiryModel("Lot Number",
                locationItemDetails.getCurrentLotNumber()+" "+ locationItemDetails.getTotalLotNumber()));
        list.add(new ItemEnquiryModel("Stored in location",
                locationItemDetails.name15));
        return list;
    }

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
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS:{
                            Utils.hideProgressDialog(progressDialog);
                            setUpAdapter(getRecordLocationOfItemData(response.data.locationItemDetails));
                            break;
                        }
                    }
                }
            }
        });
    }

    // TODO: NavController we have to use for this
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}