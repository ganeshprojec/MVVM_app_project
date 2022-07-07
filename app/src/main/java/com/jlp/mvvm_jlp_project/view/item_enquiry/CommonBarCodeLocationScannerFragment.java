package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonBarcodeLocationScannerBinding;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestBodyFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestBodyFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.carrier_handover_details.CarrierHandoverDetailsFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CommonBarCodeLocationScannerViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

// This fragment is common for Item enquiry, Item movement and for Location barcode and others in progress
@AndroidEntryPoint
public class CommonBarCodeLocationScannerFragment extends BaseFragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private FragmentCommonBarcodeLocationScannerBinding binding;
    private CommonBarCodeLocationScannerViewModel itemEnquiryViewModel;
    private final String callFor;

    @Inject
    RequestEnvelopeFindDeliveryDetailsForComponentBarcode requestEnvelopeFindDeliveryDetailsForComponentBarcode;
    @Inject
    RequestBodyFindDeliveryDetailsForComponentBarcode requestBodyFindDeliveryDetailsForComponentBarcode;
    @Inject
    RequestDataFindDeliveryDetailsForComponentBarcode requestDataFindDeliveryDetailsForComponentBarcode;

    @Inject
    RequestEnvelopeFindLocationDetailsForBarcode requestEnvelopeFindLocationDetailsForBarcode;
    @Inject
    RequestBodyFindLocationDetailsForBarcode requestBodyFindLocationDetailsForBarcode;
    @Inject
    RequestDataFindLocationDetailsForBarcode requestDataFindLocationDetailsForBarcode;

    public CommonBarCodeLocationScannerFragment(String callFor) {
        this.callFor = callFor;
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommonBarcodeLocationScannerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemEnquiryViewModel = new ViewModelProvider(this).get(CommonBarCodeLocationScannerViewModel.class);
        updateActionbarTitle();
        initObserver();
        initListener();
    }

    private void updateActionbarTitle() {
            switch (callFor){
                case AppConstants.FRAGMENT_ITEM_ENQUIRY:{
                    binding.actionbar.txtToolbarTitle.setText(getResources().getString(R.string.item_enquiry_title));
                    break;
                }
                case AppConstants.FRAGMENT_ITEM_MOVEMENT:{
                    binding.actionbar.txtToolbarTitle.setText(getResources().getString(R.string.item_movement_title));
                    break;
                }
                case AppConstants.FRAGMENT_LOCATION_SCAN:{
                    binding.actionbar.txtToolbarTitle.setText(getResources().getString(R.string.item_movement_title));
                    setLocationBarcodeFragmentTextTitles();
                    break;
                }
                case AppConstants.FRAGMENT_HAND_OVER_DELIVERY_DETAILS:{
                    binding.actionbar.txtToolbarTitle.setText(getResources().getString(R.string.handover_delivery_title));
                    break;
                }case AppConstants.FRAGMENT_MULTI_MOVEMENT:{
                    binding.actionbar.txtToolbarTitle.setText(getResources().getString(R.string.multi_movement_title));
                    setLocationBarcodeFragmentTextTitles();
                    break;
                }
            }
    }

    private void setLocationBarcodeFragmentTextTitles() {
        binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_location_barcode_number));
        binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.enter_location_barcode_number_or_scan));
    }

    private void setComponentBarcodeFragmentTextTitles() {
        binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_location_barcode_number));
        binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.enter_location_barcode_number_or_scan));
    }

    private void initListener() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                validate();
            }
        });
    }

    private void initObserver() {
        itemEnquiryViewModel.responseFindDeliveryDetailsForComponentBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindDeliveryDetailsForComponentBarcode> response) {
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
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            setBundleDataForFindDeliveryDetailsForComponentBarcode(new CommonBarCodeLocationScannerDetailsFragment(callFor), response.data);
                            break;
                        }
                    }
                }
            }
        });

        itemEnquiryViewModel.responseFindLocationDetailsForBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindLocationDetailsForBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindLocationDetailsForBarcode> response) {
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
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            if(callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT)){
                                setComponentBarcodeFragmentTextTitles();
                            }else{
                                setBundleDataForFindLocationDetailsForBarcode(new CommonBarCodeLocationScannerDetailsFragment(callFor), response.data);
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    //TODO it should be from ViewModel
    private void validate() {
        String barcode = binding.itemEnquiryInputField.inputBarcode.getText().toString().trim();
        if(TextUtils.isEmpty(barcode)){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.enter_barcode));
        }else if(barcode.length()<6){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.invalid_barcode));
        }else{
            findDetailsOfScannedBarcode(barcode);
        }
    }

    private void findDetailsOfScannedBarcode(String barcode) {
        switch (callFor){
            case AppConstants.FRAGMENT_ITEM_ENQUIRY:
            case AppConstants.FRAGMENT_ITEM_MOVEMENT: {
                findDeliveryDetailsForComponentBarcode(barcode);
                break;
            }
            case AppConstants.FRAGMENT_MULTI_MOVEMENT:
            case AppConstants.FRAGMENT_LOCATION_SCAN:{
                findLocationDetailsForBarcode(barcode);
                break;
            }
            case AppConstants.FRAGMENT_HAND_OVER_DELIVERY_DETAILS:{
                replaceFragment(new CarrierHandoverDetailsFragment());
                break;
            }
        }
    }

    private void findDeliveryDetailsForComponentBarcode(String barcode) {
        prepareRequestDataForFindDeliveryDetailsForComponentBarcode(barcode);
        itemEnquiryViewModel.findDeliveryDetailsForComponentBarcode(requestEnvelopeFindDeliveryDetailsForComponentBarcode);
    }

    private void prepareRequestDataForFindDeliveryDetailsForComponentBarcode(String barcode) {
        requestDataFindDeliveryDetailsForComponentBarcode.setBarcode(barcode);
        requestBodyFindDeliveryDetailsForComponentBarcode.setRequestDataFindDeliveryDetailsForComponentBarcode(requestDataFindDeliveryDetailsForComponentBarcode);
        requestEnvelopeFindDeliveryDetailsForComponentBarcode.setRequestBodyFindDeliveryDetailsForComponentBarcode(requestBodyFindDeliveryDetailsForComponentBarcode);
    }

    private void findLocationDetailsForBarcode(String barcode) {
        prepareRequestDataForFindLocationDetailsForBarcode(barcode);
        itemEnquiryViewModel.findLocationDetailsForBarcode(requestEnvelopeFindLocationDetailsForBarcode);
    }

    private void prepareRequestDataForFindLocationDetailsForBarcode(String barcode) {
        requestDataFindLocationDetailsForBarcode.setBarcode(barcode);
        requestBodyFindLocationDetailsForBarcode.setRequestDataFindLocationDetailsForBarcode(requestDataFindLocationDetailsForBarcode);
        requestEnvelopeFindLocationDetailsForBarcode.setRequestBodyFindLocationDetailsForBarcode(requestBodyFindLocationDetailsForBarcode);
    }

    private void clearViews() {
        binding.itemEnquiryInputField.inputBarcode.setText("");
    }


    private void setBundleDataForFindDeliveryDetailsForComponentBarcode(Fragment fragment,
                                                                        ResponseDataFindDeliveryDetailsForComponentBarcode responseData){
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.COMPONENT_BARCODE_DETAILS_DATA, responseData);
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }

    private void setBundleDataForFindLocationDetailsForBarcode(Fragment fragment,
                                                               ResponseDataFindLocationDetailsForBarcode responseData){
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.LOCATION_BARCODE_DETAILS_DATA, responseData);
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }

    // TODO: NavController we have to use for this
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }


}