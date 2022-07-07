package com.jlp.mvvm_jlp_project.view.item_enquiry;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.ItemEnquiryAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonBarcodeLocationScannerDetailsBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CommonBarCodeLocationScannerViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

//TODO:Use viewModel for data manipulation, Manage conditions to handle the data
@AndroidEntryPoint
public class CommonBarCodeLocationScannerDetailsFragment extends BaseFragment{
    private static final String TAG = LoginFragment.class.getSimpleName();
    ResponseDataFindDeliveryDetailsForComponentBarcode componentBarcodeResponse;
    ResponseDataFindLocationDetailsForBarcode locationBarcodeResponse;
    private FragmentCommonBarcodeLocationScannerDetailsBinding binding;
    private CommonBarCodeLocationScannerViewModel itemEnquiryViewModel;
    private String callFor;

    public CommonBarCodeLocationScannerDetailsFragment(String callFor) {
        this.callFor = callFor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemEnquiryViewModel = new ViewModelProvider(this).get(CommonBarCodeLocationScannerViewModel.class);
        updateActionbarTitle();
        getParcelableData();
        initListener();
        initListAndRecyclerView();
    }

    private void initListAndRecyclerView() {
        List<ItemEnquiryModel> dataList = null;
        switch (callFor){
            case AppConstants.FRAGMENT_ITEM_MOVEMENT:
            case AppConstants.FRAGMENT_ITEM_ENQUIRY:{
                dataList = getComponentBarcodeData(componentBarcodeResponse.getDeliveryItemProductDetails());
                break;
            }
            case AppConstants.FRAGMENT_LOCATION_SCAN:{
                dataList = getLocationBarcodeData(locationBarcodeResponse.getLocationDetails());
                break;
            }
        }

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
                    componentBarcodeResponse = bundle.getParcelable(AppConstants.COMPONENT_BARCODE_DETAILS_DATA);
                    locationBarcodeResponse = bundle.getParcelable(AppConstants.LOCATION_BARCODE_DETAILS_DATA);
                }
            }
        }catch (Exception ex){
            Log.e(TAG, "Exception: "+ex.getMessage());
        }
    }

    private void updateActionbarTitle() {
        switch (callFor){
            case AppConstants.FRAGMENT_LOCATION_SCAN:
            case AppConstants.FRAGMENT_ITEM_MOVEMENT:{
                binding.itemEnquiryHeader.txtToolbarTitle.setText(getResources().getString(R.string.item_movement_title));
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
        list.add(new ItemEnquiryModel("Delivery Date",
                "08 Aug 2022"));
        list.add(new ItemEnquiryModel("Last Recorded Location",
                "Pune"));
        list.add(new ItemEnquiryModel("Time of last move",
                "\"08 Aug 2022\""));
        list.add(new ItemEnquiryModel("Last UserId",
                "213"));
        list.add(new ItemEnquiryModel("Product Code",
                "412334"));
        list.add(new ItemEnquiryModel("Product description",
                "New product added in the list for shopping. User can shop by using application"));
        list.add(new ItemEnquiryModel("Lot Number",
                "123"));
        list.add(new ItemEnquiryModel("Address",
                "North UK, Sector 9, House 2, UK"));
        return list;
    }

    // TODO: NavController we have to use for this
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}