package com.jlp.mvvm_jlp_project.view.carrier_collection_and_handover_details;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

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
import com.jlp.mvvm_jlp_project.databinding.FragmentCarrierHandoverDetailsBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.CreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestBodyCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ComponentDetails;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.DeliveryGoodsDetails;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.FoundHandoverDetails;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.StringUtils;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CarrierCollectionAndHandoverDetailsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CarrierCollectionAndHandoverDetailsFragment extends BaseFragment{
    private static final String TAG = CarrierCollectionAndHandoverDetailsFragment.class.getSimpleName();
    private CarrierCollectionAndHandoverDetailsViewModel viewModel;
    private FragmentCarrierHandoverDetailsBinding binding;
    private ResponseDataFindDeliveriesAndDeliveryItems responseDataFindDeliveriesAndDeliveryItems  = CommonBarcodeScannerFragment.responseDataFindDeliveriesAndDeliveryItems;
    private String scannedComponentBarcode = CommonBarcodeScannerFragment.scannedBarcode;
    private FoundHandoverDetails foundHandoverDetails = CommonBarcodeScannerFragment.foundHandoverDetails;
    private String errorCode = CommonBarcodeScannerFragment.errorCode;
    private ProgressDialog progressDialog;

    private CommonAdapter adapter;
    private List<TitleValueDataModel> titleValueDataList =  new ArrayList<>();

    private String callFor;
    private int mYear, mMonth, mDay, mHour, mMinute;

    boolean isDatesAreSame = false;

    @Inject RequestEnvelopeCreateOrUpdateHandoverDetails requestEnvelopeCreateOrUpdateHandoverDetails;
    @Inject RequestBodyCreateOrUpdateHandoverDetails requestBodyCreateOrUpdateHandoverDetails;
    @Inject RequestDataCreateOrUpdateHandoverDetails requestDataCreateOrUpdateHandoverDetails;
    @Inject CreateOrUpdateHandoverDetails createOrUpdateHandoverDetails;
    public static CreateOrUpdateHandoverDetails copyOfCreateOrUpdateHandoverDetails;

    public CarrierCollectionAndHandoverDetailsFragment(String callFor) {
        this.callFor = callFor;
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarrierHandoverDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CarrierCollectionAndHandoverDetailsViewModel.class);
        updateActionbarAndButtonText();
        initData();
        setupAdapter();
        initListener();
        initObserver();
    }

    private void initData() {
        boolean isServiceIncluded = false;
        if(foundHandoverDetails!=null){
            binding.etToBe.setText(foundHandoverDetails.handoverTo);
            binding.etOn.setText(foundHandoverDetails.getHandoverDate());
            binding.etDeliveryTo.setText(foundHandoverDetails.getAgreedDelDate());
            // TODO : Following snippet is from the existing code to calculate Latest Del Time
            /*handoverDeliveryDetailsCollectionSetupView.deliveryToCustomerOn = findHandoverDetailsResponse.FoundHandoverDetails.agreedDelDate;
            DateTime dtTime = findHandoverDetailsResponse.FoundHandoverDetails.latestDelTime;
            string latestDeltime = DateTime.Now.ToString("dd/MM/yyyy") + " " + dtTime.Subtract(dtTime.Date);
            handoverDeliveryDetailsCollectionSetupView.latestDeliveryTime = Convert.ToDateTime(latestDeltime);*/
            binding.etLatestDelivery.setText(foundHandoverDetails.getLatestDelTime());
            binding.etOnwardTracking.setText(foundHandoverDetails.handoverRef);
        }

        if(responseDataFindDeliveriesAndDeliveryItems!=null){

            if(!TextUtils.isEmpty(errorCode) && errorCode.equals(AppConstants.TWO_THOUSAND_AND_ONE)){
                binding.etOn.setText(StringUtils.getTodayDateInStringFormat());
                binding.etDeliveryTo.setText(StringUtils.getTodayDateInStringFormat());
                isServiceIncluded = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().serviceIncluded;
            }else {
                isServiceIncluded = foundHandoverDetails.getServiceIncluded();
            }

            boolean isNumberOfDeliveryItemAndPartLots = calculateNumberOfDeliveryItemsAndTotalNumberOfPartLots(responseDataFindDeliveriesAndDeliveryItems);
            if (isNumberOfDeliveryItemAndPartLots)
            {
                if (isServiceIncluded)
                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.this_delivery_includes_services));
                if(foundHandoverDetails!=null)
                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_already_has_handover_details_captured));
            }
        }
    }

    private void initObserver() {
        viewModel.itemDelivery.observe(getViewLifecycleOwner(), new Observer<List<TitleValueDataModel>>() {
            @Override
            public void onChanged(List<TitleValueDataModel> titleValueDataModels) {
                titleValueDataList.addAll(titleValueDataModels);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first){
                    if (Utils.isInternetAvailable(getContext())){
                        prepareRequestDataForCreateOrUpdateHandoverDetails();
                        viewModel.createOrUpdateHandoverDetails(requestEnvelopeCreateOrUpdateHandoverDetails);
                    }else{
                        Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
                    }
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });

        viewModel.responseDataCreateOrUpdateHandoverDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataCreateOrUpdateHandoverDetails>>() {
            @Override
            public void onChanged(Resource<ResponseDataCreateOrUpdateHandoverDetails> response) {
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
                            if(response.data.getCreatedOrUpdatedHandoverDetails()!=null){
                                switch (callFor){
                                    case AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS:{
                                        replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS));
                                        break;
                                    }
                                    case AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS:{
                                        replaceFragment(new CarrierHandoverDeliveryItemDetailsFragment());
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    private void clearViews() {
        binding.etToBe.setText("");
        binding.etOn.setText("");
        binding.etEarliestDelivery.setText("");
        binding.etDeliveryTo.setText("");
        binding.etLatestDelivery.setText("");
        binding.etOnwardTracking.setText("");
    }

    private void initListener() {
        binding.dropDownOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etOn);
            }
        });
        
        binding.etOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etOn);
            }
        });

        binding.dropDownDeliveryTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etDeliveryTo);
            }
        });

        binding.etDeliveryTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etDeliveryTo);
            }
        });

        binding.etLatestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.etDeliveryTo.getText().toString().trim())){
                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_select_delivery_date));
                }else{
                    pickTimeAndSetToView(binding.etLatestDelivery);
                }
            }
        });

        binding.dropDownLatestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseTimeByOneHrs(binding.etLatestDelivery);
            }
        });

        binding.dropUpLatestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseTimeByOneHrs(binding.etLatestDelivery);
            }
        });

        binding.etEarliestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.etDeliveryTo.getText().toString().trim())){
                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_select_delivery_date));
                }else{
                    pickTimeAndSetToView(binding.etEarliestDelivery);
                }
            }
        });

        binding.dropDownEarliestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseTimeByOneHrs(binding.etEarliestDelivery);
            }
        });

        binding.dropUpEarliestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseTimeByOneHrs(binding.etEarliestDelivery);
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                viewModel.ValidateCreateOrUpdateHandoverDetails(binding.etToBe.getText().toString().trim(),
                        binding.etOn.getText().toString().trim(),
                        binding.etEarliestDelivery.getText().toString().trim(),
                        binding.etDeliveryTo.getText().toString().trim(),
                        binding.etLatestDelivery.getText().toString().trim(),
                        binding.etOnwardTracking.getText().toString().trim(), callFor);
            }
        });
    }

    private void prepareRequestDataForCreateOrUpdateHandoverDetails() {
        createOrUpdateHandoverDetails.setDeliveryId(responseDataFindDeliveriesAndDeliveryItems.deliveryDetails.getDeliveryId());
        createOrUpdateHandoverDetails.setHandoverTo(binding.etToBe.getText().toString().trim());
        createOrUpdateHandoverDetails.setHandoverRef(binding.etOnwardTracking.getText().toString().trim());
        if(callFor.equals( AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS)){
            createOrUpdateHandoverDetails.setHandoverDate(binding.etOn.getText().toString().trim());
        }else {
            createOrUpdateHandoverDetails.setHandoverDate(StringUtils.getTodayDateInStringFormat());
        }
        createOrUpdateHandoverDetails.setAgreedDelDate(binding.tvDeliveryTo.getText().toString().trim());
        createOrUpdateHandoverDetails.setLatestDelTime(binding.etLatestDelivery.getText().toString().trim());
        createOrUpdateHandoverDetails.setUserId(AppConstants.USER_ID);
        createOrUpdateHandoverDetails.setUserName(AppConstants.USER_NAME);
        requestDataCreateOrUpdateHandoverDetails.setCreateOrUpdateHandoverDetails(createOrUpdateHandoverDetails);
        copyOfCreateOrUpdateHandoverDetails = createOrUpdateHandoverDetails;
        requestBodyCreateOrUpdateHandoverDetails.setRequestDataCreateOrUpdateHandoverDetails(requestDataCreateOrUpdateHandoverDetails);
        requestEnvelopeCreateOrUpdateHandoverDetails.setRequestBodyCreateOrUpdateHandoverDetails(requestBodyCreateOrUpdateHandoverDetails);
    }

    private void increaseTimeByOneHrs(EditText et){
        try {
            if(et.getText().toString().trim().equals("")) et.setText("00:00");
            int hrs = Integer.parseInt(et.getText().toString().trim().split(":")[0]);
            if(hrs < 24){
                hrs++;
            }
            if(hrs == 24) hrs = 1;
            String hour = String.valueOf(hrs);
            if(hrs<=9) hour = "0"+hrs;
            et.setText(hour+":00");
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
        }
    }

    private void decreaseTimeByOneHrs(EditText et){
        try {
            if(et.getText().toString().trim().equals("")) et.setText("00:00");
            int hrs = Integer.parseInt(et.getText().toString().trim().split(":")[0]);
            if(hrs > 0){
                hrs--;
            }
            if(hrs == 0) hrs = 23;
            String hour = String.valueOf(hrs);
            if(hrs<=9) hour = "0"+hrs;
            et.setText(hour+":00");
        }catch (Exception ex){
            Log.e(TAG, "Exception : "+ex);
        }
    }

    private void pickTimeAndSetToView(EditText etLatestDelivery) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String hrs = String.valueOf(hourOfDay);
                        String min = String.valueOf(minute);
                        if(hourOfDay<=9) hrs = "0"+hrs;
                        if(minute<=9) min = "0"+min;
                        String selectedTime = hrs + ":" + min;
                        int timeDiff = StringUtils.getDifferenceOfTimes(selectedTime, StringUtils.getCurrentTimeIn24HoursFormat());
                        if(timeDiff<60 && isDatesAreSame){
                            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_time_if_delivery_is_for_today));
                            etLatestDelivery.setText("");
                            etLatestDelivery.setHint("00:00");
                        }else{
                            etLatestDelivery.setText(selectedTime);
                        }
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void pickDateAndSetToView(EditText etDateView) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String selectedDate = StringUtils.getFormattedDate(dayOfMonth, monthOfYear, year);
                        Date selectedDateInDateObject = StringUtils.getStringToDateObject(selectedDate);
                        isDatesAreSame = StringUtils.isSameDay(selectedDateInDateObject, StringUtils.getTodayDateInDateObject());
                        etDateView.setText(selectedDate);
                    }
                }, mYear, mMonth, mDay);
        // Select future date only
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    /**
     * Set up the adapter and set the data for list items
     */
    private void setupAdapter(){
        viewModel.getItemDetailsDeliveryBarcodeData(responseDataFindDeliveriesAndDeliveryItems.deliveryDetails);
        adapter = new CommonAdapter(titleValueDataList, getContext());
        binding.recyclerViewSubHeader.setAdapter(adapter);
        binding.recyclerViewSubHeader.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateActionbarAndButtonText() {
        binding.homeTopHeader.txtToolbarTitle.setText(CommonBarcodeScannerFragment.actionBarTitle);
        switch (callFor){
            case AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS:{
                updateOnLayoutForCarrieCollectionDetails();
                break;
            }
            case AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS:{
                updateOnLayoutForCarrieHandoverDetails();
                break;
            }
        }
    }

    private void updateOnLayoutForCarrieCollectionDetails() {
        binding.tvToCollected.setText(getResources().getString(R.string.to_be_collected_and_delivered_by));

        binding.layoutOnWithTvAndEt.setVisibility(View.VISIBLE);
        binding.divider2.divider.setVisibility(View.VISIBLE);

        binding.layoutEarliestDelivery.setVisibility(View.GONE);
        binding.divider4.divider.setVisibility(View.GONE);

        binding.btnConfirm.setText(getResources().getText(R.string.confirm));
    }

    private void updateOnLayoutForCarrieHandoverDetails() {
        binding.tvToCollected.setText(getResources().getString(R.string.to_be_delivered_by));
        binding.tvOn.setText(getResources().getString(R.string.on));

        binding.layoutOnWithTvAndEt.setVisibility(View.GONE);
        binding.divider2.divider.setVisibility(View.GONE);

        binding.layoutEarliestDelivery.setVisibility(View.VISIBLE);
        binding.divider4.divider.setVisibility(View.VISIBLE);

        binding.btnConfirm.setText(getResources().getText(R.string.next));
    }

    public boolean calculateNumberOfDeliveryItemsAndTotalNumberOfPartLots(ResponseDataFindDeliveriesAndDeliveryItems responseDataFindDeliveriesAndDeliveryItems)
    {
        int deliveryGoodItemsCount = 0;
        int partLots = 0;

        boolean atLeastOneComponentActive = false;
        boolean atLeastOneComponentInBay = false;
        boolean matchedScannedBarcode = false;
        String matchedScannedBarcodeStatus = "";

        List<DeliveryGoodsDetails> deliveryGoodsDetails = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails();

        for (int goodCount = 0; goodCount < deliveryGoodsDetails.size(); goodCount++) {
            List<ComponentDetails> componentDetails = deliveryGoodsDetails.get(goodCount).getComponentDetails();
            for (int componentCount = 0; componentCount < componentDetails.size(); componentCount++) {
                String componentStatus = componentDetails.get(componentCount).getComponentStatus().trim().toUpperCase();
                String componentBarcode = componentDetails.get(componentCount).getComponentBarcode();
                if (componentStatus.equals(AppConstants.ACTIVE))  atLeastOneComponentActive = true;

                if (componentStatus.equals(AppConstants.IN_BAY))  atLeastOneComponentInBay = true;

                if (componentBarcode.equals(scannedComponentBarcode)) {
                    matchedScannedBarcode = true;
                    matchedScannedBarcodeStatus = componentStatus;
                }
            }
        }

        if (TextUtils.isEmpty(matchedScannedBarcodeStatus) && !atLeastOneComponentActive && !atLeastOneComponentInBay) {
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_has_a_part_lot_which_has_no_location));
            return false;
        }

        if (!TextUtils.isEmpty(matchedScannedBarcodeStatus) && !matchedScannedBarcodeStatus.equals(AppConstants.ACTIVE) && !matchedScannedBarcodeStatus.equals(AppConstants.IN_BAY)) {
            if (!atLeastOneComponentActive && !atLeastOneComponentInBay) {
                Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_has_a_part_lot_which_has_no_location));
            }
            else {
                Utils.showErrorMessage(getActivity(), getResources().getString(R.string.incorrect_item_status));
            }
            return false;
        }

        if (atLeastOneComponentActive && !atLeastOneComponentInBay) {
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_has_a_part_lot_which_has_no_location));
            return false;
        }

        if (atLeastOneComponentInBay) {
            for (int goodCount = 0; goodCount < deliveryGoodsDetails.size(); goodCount++) {
                boolean isGoodItemActive = false;
                List<ComponentDetails> componentDetails = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails().get(goodCount).getComponentDetails();
                for (int componentCount = 0; componentCount < componentDetails.size(); componentCount++) {
                    String componentStatus = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails().get(goodCount).getComponentDetails().get(componentCount).getComponentStatus().trim().toUpperCase();
                    if (componentStatus.equals(AppConstants.IN_BAY) ||
                            componentStatus.equals(AppConstants.ACTIVE)) {
                        isGoodItemActive = true;
                        partLots = partLots + 1;
                    }
                }
                if (isGoodItemActive) {
                    deliveryGoodItemsCount = deliveryGoodItemsCount + 1;
                }
            }

            if (deliveryGoodItemsCount > 0) {
                this.responseDataFindDeliveriesAndDeliveryItems.deliveryDetails.setNumberOfDeliveryItems(String.valueOf(deliveryGoodItemsCount));
                this.responseDataFindDeliveriesAndDeliveryItems.deliveryDetails.setTotalLotNumber(String.valueOf(partLots));
                return true;
            }
            else {
                Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_has_no_items_or_package_to_handover));
                return false;
            }
        }
        return false;
    }

    // TODO: NavController we have to use for this
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}