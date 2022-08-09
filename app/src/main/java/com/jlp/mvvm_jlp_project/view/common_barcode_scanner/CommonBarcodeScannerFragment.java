package com.jlp.mvvm_jlp_project.view.common_barcode_scanner;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonBarcodeScannerBinding;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProductDetails;
import com.jlp.mvvm_jlp_project.model.RouteSummary;
import com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items.RequestBodyFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items.RequestDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items.RequestEnvelopeFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestBodyFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product.RequestBodyFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product.RequestDataFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product.RequestEnvelopeFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestBodyFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestDataFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_handover_details.RequestBodyFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.find_handover_details.RequestDataFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.find_handover_details.RequestEnvelopeFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestBodyFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseDataFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.DeliveryItemDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseDataFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.FoundHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.ResponseDataFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.carrier_collection_and_handover_details.CarrierCollectionAndHandoverDetailsFragment;
import com.jlp.mvvm_jlp_project.view.route_management.RouteSummaryFragment;
import com.jlp.mvvm_jlp_project.view.reprint_labels.ReprintLabelItemListFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CommonBarcodeScannerViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * This Fragment is common for Item enquiry, Item Movement, Multi Movement and Carrier Handover details
 * depends on callfor Attribute making decision that for which fragment is call
 */
@AndroidEntryPoint
public class CommonBarcodeScannerFragment extends BaseFragment  {

    private static final String TAG = CommonBarcodeScannerFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private FragmentCommonBarcodeScannerBinding binding;
    private CommonBarcodeScannerViewModel viewModel;
    private String callFor;
    private String printerName,printerID;

    public static DeliveryItemProductDetails deliveryItemProductDetails;
    public static LocationDetails locationDetails;
    public static DeliveryItemDetails deliveryItemDetails;
    //RerprintLabel
    public static DeliveryGoodProductDetails deliveryGoodProductDetails;
    public static DeliveryGoodProduct deliveryGoodProduct;
    String deliveryNumber="36674303";


    public static RouteSummary routeSummary;
    public static ResponseDataFindDeliveriesAndDeliveryItems responseDataFindDeliveriesAndDeliveryItems;
    public static String scannedBarcode;
    public static FoundHandoverDetails foundHandoverDetails;
    public static String errorCode;

    @Inject
    RequestEnvelopeFindDeliveryDetailsForComponentBarcode requestEnvelopeComponentBarcode;
    @Inject
    RequestBodyFindDeliveryDetailsForComponentBarcode requestBodyComponentBarcode;
    @Inject
    RequestDataFindDeliveryDetailsForComponentBarcode requestDataComponentBarcode;

    @Inject
    RequestEnvelopeFindLocationDetailsForBarcode requestEnvelopeLocationBarcode;
    @Inject
    RequestBodyFindLocationDetailsForBarcode requestBodyLocationBarcode;
    @Inject
    RequestDataFindLocationDetailsForBarcode requestDataLocationBarcode;

    @Inject
    RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode requestEnvelopeItemDetailsComponentBarcode;
    @Inject
    RequestBodyFindDeliveryItemDetailsForComponentBarcode requestBodyItemDetailsComponentBarcode;
    @Inject
    RequestDataFindDeliveryItemDetailsForComponentBarcode requestDataItemDetailsComponentBarcode;

//Reprint Label
    @Inject
     RequestEnvelopeFindDeliveryGoodProduct requestEnvelopeFindDeliveryGoodProduct;
    @Inject
    RequestBodyFindDeliveryGoodProduct requestBodyFindDeliveryGoodProduct;
    @Inject
    RequestDataFindDeliveryGoodProduct requestDataFindDeliveryGoodProduct;




    @Inject
    RequestEnvelopeFindHandoverDetails requestEnvelopeFindHandoverDetails;
    @Inject
    RequestBodyFindHandoverDetails requestBodyFindHandoverDetails;
    @Inject
    RequestDataFindHandoverDetails requestDataFindHandoverDetails;


    @Inject
    RequestEnvelopeFindDeliveriesAndDeliveryItems requestEnvelopeFindDeliveriesAndDeliveryItems;
    @Inject
    RequestBodyFindDeliveriesAndDeliveryItems requestBodyFindDeliveriesAndDeliveryItems;
    @Inject
    RequestDataFindDeliveriesAndDeliveryItems requestDataFindDeliveriesAndDeliveryItems;

    public static String actionBarTitle;
    public static boolean locationLayoutFlag;
    public static boolean locationAmendLotFlag;

    public CommonBarcodeScannerFragment(String callFor) {
        this.callFor = callFor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            printerName = getArguments().getString(AppConstants.PRINTER_NAME);
            printerID = getArguments().getString(AppConstants.PRINTER_ID);

        }
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommonBarcodeScannerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CommonBarcodeScannerViewModel.class);
        updateActionbarTitle();
        initObserver();
        initListener();
    }

    /**
     * This function helps to update the title of actionbar as it is common for multiple features
     */
    private void updateActionbarTitle() {
        locationLayoutFlag = false;
        locationAmendLotFlag = false;
            switch (callFor){
                case AppConstants.FRAGMENT_ITEM_ENQUIRY:{
                    actionBarTitle = getResources().getString(R.string.item_enquiry_title);
                    break;
                }
                case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE:{
                    actionBarTitle = getResources().getString(R.string.item_movement_title);
                    break;
                }
                case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE:{
                    actionBarTitle = getResources().getString(R.string.item_movement_title);
                    setLocationBarcodeFragmentTextTitles();
                    break;
                }
                case AppConstants.FRAGMENT_AMEND_LOTS:{
                    locationAmendLotFlag=true;
                  //  locationLayoutFlag = true;
                    actionBarTitle = getResources().getString(R.string.str_amend_lots);
                    setAmendLotsSelectedPrinterText();
                    binding.actionbar.imgClose.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {Helper.clearBackStack(getActivity());}});
                    break;
                }
                case AppConstants.FRAGMENT_REPRINT_LABELS:{
                    actionBarTitle = getResources().getString(R.string.str_reprint_labels);
                    binding.actionbar.imgClose.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) {
                        Helper.clearBackStack(getActivity());}});
                    binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.carrier_collection_details));
                    binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_delivery_number));
                    break;
                }
                case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:{
                    actionBarTitle = getResources().getString(R.string.multi_movement_title);
                    setComponentBarcodeFragmentTextTitles();
                    locationLayoutFlag = true;
                    break;
                }
                case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:{
                    actionBarTitle = getResources().getString(R.string.multi_movement_title);
                    setLocationBarcodeFragmentTextTitles();
                    break;
            }
            case AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS: {
                actionBarTitle = getResources().getString(R.string.handover_delivery_title);
                setDeliveryBarcodeFragmentTextTitles();
                break;
            }
            case AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS: {
                actionBarTitle = getResources().getString(R.string.carrier_collection_details_title);
                setDeliveryBarcodeFragmentTextTitles();
                break;
            }

            case AppConstants.FRAGMENT_ROUTE_MANAGEMENT: {
                actionBarTitle = getResources().getString(R.string.str_route_management);
                setRouteDeliveryBarcodeFragmentTextTitles();
                    break;
                }
            }
            if(locationLayoutFlag){
                binding.scanNextItemBarcode.scanItemBarcode.setVisibility(View.VISIBLE);
            }
            else if(locationAmendLotFlag){
                binding.scanNextItemBarcode.tvScanNextBarcode.setVisibility(View.VISIBLE);
            }
            else{
                binding.scanNextItemBarcode.scanItemBarcode.setVisibility(View.INVISIBLE);

            }
            binding.actionbar.txtToolbarTitle.setText(actionBarTitle);
    }



    /**
     * This methos initializes the click listener of next button once the barcode scans
     */
    private void initListener() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                viewModel.validateBarcode(binding.itemEnquiryInputField.inputBarcode.getText().toString().trim(), callFor);
            }
        });
    }

    //Set Seleted Printer Text label on Amend Lost
    private void setAmendLotsSelectedPrinterText()
    {

        binding.scanNextItemBarcode.scanItemBarcode.setVisibility(View.VISIBLE);
        binding.scanNextItemBarcode.imgNextScan.setVisibility(View.GONE);
        binding.scanNextItemBarcode.tvSelectedLocation.setVisibility(View.INVISIBLE);
       // binding.scanNextItemBarcode.tvScanNextBarcode.setVisibility(View.VISIBLE);
        binding.scanNextItemBarcode.tvScanNextBarcode.setText(getResources().getString(R.string.str_selected_printer)+"  :  "+printerName);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1.0f;
        params.topMargin=20;
        params.gravity= Gravity.CENTER_HORIZONTAL;
        binding.scanNextItemBarcode.tvScanNextBarcode.setLayoutParams(params);
    }
    /**
     * This function helps to deal with header which is bottom to actionbar
     * For Multi Movement feature we have to show the layout and location name
     *
     * @param locationDetails shown location name from this object
     */
    private void updateLocationLayout(LocationDetails locationDetails) {
        binding.scanNextItemBarcode.scanItemBarcode.setVisibility(View.VISIBLE);
        binding.scanNextItemBarcode.imgNextScan.setVisibility(View.INVISIBLE);
        binding.scanNextItemBarcode.tvScanNextBarcode.setVisibility(View.GONE);
        binding.scanNextItemBarcode.tvSelectedLocation.setVisibility(View.VISIBLE);
        binding.scanNextItemBarcode.tvSelectedLocation.setText(getResources().getString(R.string.selected_location) + ": " + locationDetails.name15);
    }

    /**
     * Identify the coll type and made the respective api call
     * for location or components
     *
     * @param barcode input param for api call
     */
    private void findDetailsOfScannedBarcode(String barcode) {
        if (Utils.isInternetAvailable(getContext())){
            scannedBarcode = barcode;
            switch (callFor) {
                case AppConstants.FRAGMENT_ITEM_ENQUIRY:
                case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE: {
                    findDeliveryDetailsForComponentBarcode(barcode);
                    break;
                }
                case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:
                case AppConstants.FRAGMENT_AMEND_LOTS:{
                    findDeliveryItemDetailsForComponentBarcode(barcode);
                    break;
                }
                case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE:
                case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_LOCATION_BARCODE: {
                    findLocationDetailsForBarcode(barcode);
                    break;
                }
                case AppConstants.FRAGMENT_ROUTE_MANAGEMENT: {
                    callRouteManagementSummary(barcode);
                    break;
                }
                case AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS:
                case AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS:{
                    // In This case barcode is deliveryId
                    findHandoverDetails(barcode);
                    break;
                }
                case AppConstants.FRAGMENT_REPRINT_LABELS:{
                    findDeliveryGoodProducts(barcode);
                    break;
                }
            }
        }else{
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
        }
    }

    private void findDeliveryGoodProducts(String barcode) {
        prepareRequestDataForFindDeliveryGoodProducts(barcode);
        viewModel.findDeliveryGoodProducts(requestEnvelopeFindDeliveryGoodProduct);
    }

    private void prepareRequestDataForFindDeliveryGoodProducts(String barcode)
    {
        requestDataFindDeliveryGoodProduct.setDeliveryId(deliveryNumber);
        requestBodyFindDeliveryGoodProduct.setRequestDataFindDeliveryGoodProduct(requestDataFindDeliveryGoodProduct);
        requestEnvelopeFindDeliveryGoodProduct.setRequestBodyFindDeliveryGoodProduct(requestBodyFindDeliveryGoodProduct);

    }

    /**
     * Updated the barcode title text and hint as per the location barcode
     */
    private void setLocationBarcodeFragmentTextTitles() {
        binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_location_barcode_number));
        binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.enter_location_barcode_number_or_scan));
    }

    /**
     * Updated the barcode title text and hint as per the component barcode
     */
    private void setComponentBarcodeFragmentTextTitles() {
        binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_barcode_number));
        binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.enter_item_barcode_number));
    }

    /**
     * Updated the barcode title text and hint as per the delivery barcode
     */
    private void setDeliveryBarcodeFragmentTextTitles() {
        binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_delivery_barcode_number_or_scan));
        binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.enter_delivery_barcode_number));
    }

    /**
     * Updated the barcode title text and hint as per the delivery barcode
     */
    private void setRouteDeliveryBarcodeFragmentTextTitles() {
        binding.itemEnquiryInputField.inputBarcode.setHint(getResources().getString(R.string.enter_route_number));
        binding.itemEnquiryInputField.textTitle.setText(getResources().getString(R.string.enter_route_number_or_scan));

        binding.itemEnquiryInputField.inputBarcode.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        binding.itemEnquiryInputField.inputBarcode.setAllCaps(true);

        int maxLength = 18;
        binding.itemEnquiryInputField.inputBarcode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    /**
     * Prepared Data set for api done actual call from view to view model
     * Api call for component barcode
     * @param barcode
     */
    private void findDeliveryDetailsForComponentBarcode(String barcode) {
        prepareRequestDataForFindDeliveryDetailsForComponentBarcode(barcode);
        viewModel.findDeliveryDetailsForComponentBarcode(requestEnvelopeComponentBarcode);
    }


    /**
     * Prepared Data set for api call component barcode
     *
     * @param barcode
     */
    private void prepareRequestDataForFindDeliveryDetailsForComponentBarcode(String barcode) {
        requestDataComponentBarcode.setBarcode(barcode);
        requestBodyComponentBarcode.setRequestDataFindDeliveryDetailsForComponentBarcode(requestDataComponentBarcode);
        requestEnvelopeComponentBarcode.setRequestBodyFindDeliveryDetailsForComponentBarcode(requestBodyComponentBarcode);
    }

    private void prepareRequestDataForFindHandoverDetails(String barcode) {
        requestDataFindHandoverDetails.setDeliveryId(barcode);
        requestBodyFindHandoverDetails.setRequestDataFindHandoverDetails(requestDataFindHandoverDetails);
        requestEnvelopeFindHandoverDetails.setRequestBodyFindHandoverDetails(requestBodyFindHandoverDetails);
    }

    private void prepareRequestDataForFindDeliveriesAndDeliveryItems(String barcode) {
        requestDataFindDeliveriesAndDeliveryItems.setDeliveryId(barcode);
        requestBodyFindDeliveriesAndDeliveryItems.setRequestDataFindDeliveriesAndDeliveryItems(requestDataFindDeliveriesAndDeliveryItems);
        requestEnvelopeFindDeliveriesAndDeliveryItems.setRequestBodyFindDeliveriesAndDeliveryItems(requestBodyFindDeliveriesAndDeliveryItems);
    }

    /**
     * Prepared Data set for api done actual call from view to view model
     * Api call for component barcode
     *
     * @param barcode
     */
    private void findDeliveryItemDetailsForComponentBarcode(String barcode) {
        prepareRequestDataForFindDeliveryItemDetailsForComponentBarcode(barcode);
        viewModel.findDeliveryItemDetailsForComponentBarcode(requestEnvelopeItemDetailsComponentBarcode);
    }

    /**
     * Prepared Data set for api call component barcode
     *
     * @param barcode
     */
    private void prepareRequestDataForFindDeliveryItemDetailsForComponentBarcode(String barcode) {
        requestDataItemDetailsComponentBarcode.setBarcode(barcode);
        requestBodyItemDetailsComponentBarcode.setRequestDataFindDeliveryItemDetailsForComponentBarcode(requestDataItemDetailsComponentBarcode);
        requestEnvelopeItemDetailsComponentBarcode.setRequestBodyFindDeliveryItemDetailsForComponentBarcode(requestBodyItemDetailsComponentBarcode);
    }

    /**
     * Prepared Data set for api done actual call from view to view model
     * Api call for location barcode
     *
     * @param barcode
     */
    private void findLocationDetailsForBarcode(String barcode) {

        prepareRequestDataForFindLocationDetailsForBarcode(barcode);
        viewModel.findLocationDetailsForBarcode(requestEnvelopeLocationBarcode);
    }

    /**
     * Prepare data for api call and make api call for findHandoverDetails
     * @param barcode is the deliveryId
     */
    private void findHandoverDetails(String barcode) {
        prepareRequestDataForFindHandoverDetails(barcode);
        viewModel.findHanoverDetails(requestEnvelopeFindHandoverDetails);
    }

    /**
     * Prepare data for api call and make api call for findDeliveriesAndDeliveryItems
     * @param barcode is the deliveryId
     */
    private void findDeliveriesAndDeliveryItems(String barcode) {
        prepareRequestDataForFindDeliveriesAndDeliveryItems(barcode);
        viewModel.findDeliveriesAndDeliveryItems(requestEnvelopeFindDeliveriesAndDeliveryItems);
    }

    /**
     * Prepared Data set for api done actual call from view to view model
     * Api call for Route Management Summary
     *
     * @param barcode
     */
    private void callRouteManagementSummary(String barcode) {
        //prepareRequestDataForFindLocationDetailsForBarcode(barcode);
        viewModel.callSummaryDetails(barcode);
    }

    /**
     * Prepared Data set for api call location barcode
     *
     * @param barcode
     */
    private void prepareRequestDataForFindLocationDetailsForBarcode(String barcode) {
        requestDataLocationBarcode.setBarcode(barcode);
        requestBodyLocationBarcode.setRequestDataFindLocationDetailsForBarcode(requestDataLocationBarcode);
        requestEnvelopeLocationBarcode.setRequestBodyFindLocationDetailsForBarcode(requestBodyLocationBarcode);
    }

    /**
     * setup all the observer of app for api call and validations
     */
    private void initObserver() {

        viewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if (validationResult.first) {
                    findDetailsOfScannedBarcode(binding.itemEnquiryInputField.inputBarcode.getText().toString().trim());
                } else {
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });

        viewModel.responseFindDeliveryDetailsForComponentBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindDeliveryDetailsForComponentBarcode> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            deliveryItemProductDetails = response.data.getDeliveryItemProductDetails();
                            replaceFragment(new CommonBarcodeScannerDetailsFragment(callFor));
                            break;
                        }
                    }
                }
            }
        });

        viewModel.responseFindLocationDetailsForBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindLocationDetailsForBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindLocationDetailsForBarcode> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            locationDetails = response.data.getLocationDetails();
                            if (callFor.equals(AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_LOCATION_BARCODE)) {
                                updateLocationLayout(locationDetails);
                                setComponentBarcodeFragmentTextTitles();
                                locationLayoutFlag = true;
                                callFor = AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE;
                            } else {
                                replaceFragment(new CommonBarcodeScannerDetailsFragment(callFor));
                            }
                            break;
                        }
                    }
                }
            }
        });

        viewModel.responseFindDeliveryItemDetailsForComponentBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindDeliveryItemDetailsForComponentBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindDeliveryItemDetailsForComponentBarcode> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            deliveryItemDetails = response.data.getDeliveryItemDetails();
                            replaceFragment(new CommonBarcodeScannerDetailsFragment(callFor));
                            break;
                        }
                    }
                }
            }
        });


        viewModel.responseSummaryDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataRouteManagementSummary>>() {
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
                            routeSummary = response.data.getRouteSummary();
                            // call route Summary Details fragment
                            Helper.addFragment(getActivity(), new RouteSummaryFragment());

                            break;
                        }
                    }
                }
            }
        });

        viewModel.responseFindHandoverDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindHandoverDetails>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindHandoverDetails> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            errorCode = response.code;
                            Utils.hideProgressDialog(progressDialog);
                            if(errorCode!=null && errorCode.equals(AppConstants.TWO_THOUSAND_AND_ONE)){
                                findDeliveriesAndDeliveryItems(scannedBarcode);
                            }else {
                                Utils.showErrorMessage(getActivity(), response.message);
                            }
                            break;
                        }
                        case SUCCESS: {
                            Utils.hideProgressDialog(progressDialog);
                            if(response.data!=null && response.data.getFoundHandoverDetails()!=null){
                                foundHandoverDetails = response.data.getFoundHandoverDetails();
                                findDeliveriesAndDeliveryItems(scannedBarcode);
                            }
                            break;
                        }
                    }
                }
            }
        });

        viewModel.responseFindDeliveriesAndDeliveryItems.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindDeliveriesAndDeliveryItems>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindDeliveriesAndDeliveryItems> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {
                            Utils.hideProgressDialog(progressDialog);
                            responseDataFindDeliveriesAndDeliveryItems = response.data;
                            Helper.addFragment(getActivity(), new CarrierCollectionAndHandoverDetailsFragment(callFor));
                            break;
                        }
                    }
                }
            }
        });


        //ReprintLabel
        viewModel.responseDataFindDeliveryGoodProduct.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindDeliveryGoodProduct>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindDeliveryGoodProduct> response) {
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
                            deliveryGoodProductDetails=response.data.getDeliveryGoodProductDetails();
                            replaceFragment(new ReprintLabelItemListFragment(callFor,deliveryNumber,printerID));
                            break;
                        }
                    }
                }
            }
        });
    }

    /**
     * Clear view on success api call
     */
    private void clearViews() {
        binding.itemEnquiryInputField.inputBarcode.setText("");
    }


    /*private void setBundleDataAndReplaceFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.COMPONENT_BARCODE_DETAILS_DATA, deliveryItemProductDetails);
        bundle.putParcelable(AppConstants.LOCATION_BARCODE_DETAILS_DATA, locationDetails);
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }*/

    // TODO: NavController we have to use for this
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }

}