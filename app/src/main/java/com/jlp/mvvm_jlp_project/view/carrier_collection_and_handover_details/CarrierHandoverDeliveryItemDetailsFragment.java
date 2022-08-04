package com.jlp.mvvm_jlp_project.view.carrier_collection_and_handover_details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.jlp.mvvm_jlp_project.databinding.FragmentCarrierHandoverDeliveryItemDetailsBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.CreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.DeliveryGoodsDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestBodyCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.CreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.ComponentDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.pref.AppPreferencesHelper;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.StringUtils;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CarrierCollectionAndHandoverDetailsViewModel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CarrierHandoverDeliveryItemDetailsFragment extends BaseFragment{
    private static final String TAG = CarrierHandoverDeliveryItemDetailsFragment.class.getSimpleName();
    private CarrierCollectionAndHandoverDetailsViewModel viewModel;
    private FragmentCarrierHandoverDeliveryItemDetailsBinding binding;
    private ProgressDialog progressDialog;
    private ResponseDataFindDeliveriesAndDeliveryItems responseDataFindDeliveriesAndDeliveryItems  = CommonBarcodeScannerFragment.responseDataFindDeliveriesAndDeliveryItems;
    private CreateOrUpdateHandoverDetails createOrUpdateHandoverDetails = CarrierCollectionAndHandoverDetailsFragment.copyOfCreateOrUpdateHandoverDetails;
    private CommonAdapter adapter;
    private List<TitleValueDataModel> titleValueDataList =  new ArrayList<>();
    private Hashtable hsDeliveryGoodItemCompLength;

    @Inject AppPreferencesHelper appPreferencesHelper;

    @Inject RequestEnvelopeCreateComponentHandoverDetails requestEnvelopeCreateComponentHandoverDetails;
    @Inject RequestBodyCreateComponentHandoverDetails requestBodyCreateComponentHandoverDetails;
    @Inject RequestDataCreateComponentHandoverDetails requestDataCreateComponentHandoverDetails;
    @Inject CreateComponentHandoverDetails createComponentHandoverDetails;

    List<DeliveryGoodsDetails> deliveryGoodsDetails = new ArrayList<>();

    List<ComponentDetails> componentDetails = new ArrayList<>();

    public CarrierHandoverDeliveryItemDetailsFragment() {}

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarrierHandoverDeliveryItemDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CarrierCollectionAndHandoverDetailsViewModel.class);
        updateActionbar();
        initData();
        setupAdapter();
        initListener();
        initObserver();
    }

    private void initData() {
        if(responseDataFindDeliveriesAndDeliveryItems!=null){
            binding.tvDeliveryId.setText(responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryId());
            binding.tvTotalLots.setText("0 of "+responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getTotalLotNumber());
        }
    }

    private void initObserver() {
        viewModel.itemDelivery.observe(getViewLifecycleOwner(), new Observer<List<TitleValueDataModel>>() {
            @Override
            public void onChanged(List<TitleValueDataModel> titleValueDataModels) {
                titleValueDataList.add(titleValueDataModels.get(0));
                //Collections.reverse(titleValueDataList);
                adapter.notifyDataSetChanged();

                binding.recyclerViewDeliveryItemDetails.setVisibility(View.VISIBLE);
                binding.tvNoItemScanned.setVisibility(View.GONE);
            }
        });

        viewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first){
                    if (Utils.isInternetAvailable(getContext())){
                        prepareRequestDataForCreateComponentHandoverDetails();
                        viewModel.createComponentHandoverDetails(requestEnvelopeCreateComponentHandoverDetails);
                    }else{
                        Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
                    }
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });

        viewModel.responseDataCreateComponentHandoverDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataCreateComponentHandoverDetails>>() {
            @Override
            public void onChanged(Resource<ResponseDataCreateComponentHandoverDetails> response) {
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
                            if(response.data.getCreatedComponentHandoverDetails()!=null){
                                replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS));
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    private void clearViews() {
        binding.layoutItemEnquiryInputField.inputBarcode.setText("");
    }

    private void initListener() {
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                // TODO: need to change later
//                if(titleValueDataList.size()==0){
//                    //TODO: confirm the error message
//                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.no_item_added_yet));
//                }else {
//                    if(titleValueDataList.size()<4){
//                        viewModel.addItemDetailsInRecycler(titleValueDataList.size()+1, binding.layoutItemEnquiryInputField.inputBarcode.getText().toString().trim());
//                    }else{
//                        onScanEventTriggered(binding.layoutItemEnquiryInputField.inputBarcode.getText().toString().trim());
//                    }
//                }

                if(titleValueDataList.size()<4){
                    viewModel.addItemDetailsInRecycler(titleValueDataList.size()+1, binding.layoutItemEnquiryInputField.inputBarcode.getText().toString().trim());
                }else{
                    onScanEventTriggered(binding.layoutItemEnquiryInputField.inputBarcode.getText().toString().trim());
                }

            }
        });
    }

    private void prepareRequestDataForCreateComponentHandoverDetails() {
        createComponentHandoverDetails.setDeliveryId(createOrUpdateHandoverDetails.deliveryId);
        createComponentHandoverDetails.setHandoverTo(createOrUpdateHandoverDetails.getHandoverTo());
        createComponentHandoverDetails.setHandoverDate(StringUtils.getTodayDateInStringFormat());
        createComponentHandoverDetails.setAgreedDelDate(createOrUpdateHandoverDetails.getAgreedDelDate());
        createComponentHandoverDetails.setLatestDelTime(createOrUpdateHandoverDetails.getLatestDelTime());
        createComponentHandoverDetails.setHandoverRef(createOrUpdateHandoverDetails.getHandoverRef());
        createComponentHandoverDetails.setUserId(appPreferencesHelper.getUserId());
        createComponentHandoverDetails.setUserName(appPreferencesHelper.getUsername());
        requestDataCreateComponentHandoverDetails.setCreateComponentHandoverDetails(createComponentHandoverDetails);
        requestBodyCreateComponentHandoverDetails.setRequestDataCreateComponentHandoverDetails(requestDataCreateComponentHandoverDetails);
        requestEnvelopeCreateComponentHandoverDetails.setRequestBodyCreateComponentHandoverDetails(requestBodyCreateComponentHandoverDetails);

        int handoverGoodCount = 0;
        List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.DeliveryGoodsDetails> deliveryGoodsDetails = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails();
        for (int goodCount = 0; goodCount < deliveryGoodsDetails.size(); goodCount++)
        {
            if (hsDeliveryGoodItemCompLength.containsKey(deliveryGoodsDetails.get(goodCount).getDeliveryGoodId().trim())) {
                //Delivery Good Item Level
                DeliveryGoodsDetails deliveryGoodsDetail = new DeliveryGoodsDetails();
                deliveryGoodsDetail.setDeliveryGoodId(deliveryGoodsDetails.get(goodCount).getDeliveryGoodId().trim());
                deliveryGoodsDetail.setProductCode(deliveryGoodsDetails.get(goodCount).getProductCode().trim());
                deliveryGoodsDetail.setProductDescription(deliveryGoodsDetails.get(goodCount).getOrderDescriptionClean().trim());
                this.deliveryGoodsDetails.add(deliveryGoodsDetail);
                requestDataCreateComponentHandoverDetails.getCreateComponentHandoverDetails().getDeliveryGoodsDetails().add(handoverGoodCount, deliveryGoodsDetail);

                int handoverComponentCount = 0;
                for (int componentCount = 0; componentCount < deliveryGoodsDetails.get(goodCount).componentDetails.size(); componentCount++)
                {
                    List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ComponentDetails> componentDetails = deliveryGoodsDetails.get(goodCount).getComponentDetails();
                    String componentStatus = componentDetails.get(componentCount).getComponentStatus().trim().toUpperCase();

                    if (componentStatus.equalsIgnoreCase(AppConstants.IN_BAY) || componentStatus.equalsIgnoreCase(AppConstants.ACTIVE))
                    {
                        if (componentDetails.get(componentCount).getComponentStatusText().trim().equalsIgnoreCase("Scanned")) {
                            //delivery Good Item Component Level
                            ComponentDetails componentDetail = new ComponentDetails();
                            componentDetail.componentBarcode = componentDetails.get(componentCount).getComponentBarcode();
                            componentDetail.componentId = componentDetails.get(componentCount).getComponentId();
                            componentDetail.componentNum = componentDetails.get(componentCount).getComponentNum();
                            componentDetail.componentStatus = componentDetails.get(componentCount).getComponentStatus();
                            requestDataCreateComponentHandoverDetails.getCreateComponentHandoverDetails().getDeliveryGoodsDetails().get(handoverGoodCount).getComponentDetails().add(handoverComponentCount, componentDetail);
                        }
                        handoverComponentCount = handoverComponentCount + 1;
                    }
                }
                handoverGoodCount = handoverGoodCount + 1;
            }
        }
    }

    /**
     * Set up the adapter and set the data for list items
     */
    private void setupAdapter(){
        adapter = new CommonAdapter(titleValueDataList, getContext());
        binding.recyclerViewDeliveryItemDetails.setAdapter(adapter);
        binding.recyclerViewDeliveryItemDetails.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateActionbar() {
        binding.homeTopHeader.txtToolbarTitle.setText(CommonBarcodeScannerFragment.actionBarTitle);
    }

    public void VerifyComponentBarcodeAndPopulateDeliveryItemDetailsList(String scannedComponentBarcode) {
        int scannedDeliveryComponentCount = 0;
        int totalDeliveryComponentCount = 0;
        boolean atLeastOneComponentActive = false;
        boolean atLeastOneComponentInBay = false;
        boolean matchedScannedBarcode = false;
        String matchedScannedBarcodeStatus = "";

        List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.DeliveryGoodsDetails> deliveryGoodsDetails = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails();
        for (int goodCount = 0; goodCount < deliveryGoodsDetails.size(); goodCount++) {
            List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ComponentDetails> componentDetails = deliveryGoodsDetails.get(goodCount).getComponentDetails();
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

        if (!matchedScannedBarcode) {Utils.showErrorMessage(getActivity(), getResources().getString(R.string.package_for_another_delivery));}

        if (!TextUtils.isEmpty(matchedScannedBarcodeStatus) && matchedScannedBarcodeStatus != AppConstants.ACTIVE && matchedScannedBarcodeStatus != AppConstants.IN_BAY) {Utils.showErrorMessage(getActivity(), getResources().getString(R.string.incorrect_item_status));}

        if (atLeastOneComponentActive && !atLeastOneComponentInBay) {Utils.showErrorMessage(getActivity(), getResources().getString(R.string.delivery_has_a_part_lot_which_has_no_location));}

        if (atLeastOneComponentInBay)
        {
            for (int goodCount = 0; goodCount < deliveryGoodsDetails.size(); goodCount++)
            {
                List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ComponentDetails> componentDetails = deliveryGoodsDetails.get(goodCount).getComponentDetails();
                int totalComponentCount = 0;
                for (int componentCount = 0; componentCount < componentDetails.size(); componentCount++)
                {
                    String componentStatus = componentDetails.get(componentCount).getComponentStatus().trim().toUpperCase();
                    String componentStatusText = componentDetails.get(componentCount).getComponentStatusText().trim();
                    String componentBarcode = componentDetails.get(componentCount).getComponentBarcode().trim();
                    if (componentStatus.equalsIgnoreCase(AppConstants.IN_BAY) || componentStatus.equalsIgnoreCase(AppConstants.ACTIVE))
                    {
                        //row.ComponentDetails.Add(componentBarcode, "");
                        if (componentStatus.equalsIgnoreCase(scannedComponentBarcode)) {
                            if (componentStatusText.equals("Scanned")) {Utils.showErrorMessage(getActivity(), getResources().getString(R.string.item_has_already_be_scanned_for_handover));}
                            else {
                                responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails().get(goodCount).getComponentDetails().get(componentCount).setComponentStatusText("Scanned");
                                totalComponentCount = totalComponentCount + 1;
                            }
                        }
                    }
                }

                if (totalComponentCount > 0) {
                    String deliveryGoodId = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails().get(goodCount).deliveryGoodId;
                    String deliveryItemDescription = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails().get(goodCount).getOrderDescriptionClean();
                    String deliveryItemNumber = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails().get(goodCount).getProductCode();

                    viewModel.addItemDetailsInRecycler(goodCount + 1, deliveryItemDescription);
                    scannedDeliveryComponentCount = scannedDeliveryComponentCount + goodCount + 1;
                    totalDeliveryComponentCount = totalDeliveryComponentCount + totalComponentCount;
                }
            }
        }

        binding.tvTotalLots.setText(scannedDeliveryComponentCount+" of "+totalDeliveryComponentCount);

        if (totalDeliveryComponentCount == scannedDeliveryComponentCount)
        {
            //  All the items scanned stop further scanning
        }
    }

    public boolean checkIfItemIsPartiallyScanned() {
        boolean atLeastOneGoodPartiallyScanned = false;
        int deliveryGoodItemLength = 0;
        hsDeliveryGoodItemCompLength = new Hashtable();
        List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.DeliveryGoodsDetails> deliveryGoodsDetails = responseDataFindDeliveriesAndDeliveryItems.getDeliveryDetails().getDeliveryGoodsDetails();
        for (int goodCount = 0; goodCount < deliveryGoodsDetails.size(); goodCount++) {
            int scannedComponentCount = 0;
            int totalComponentCount = 0;
            List<com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ComponentDetails> componentDetails = deliveryGoodsDetails.get(goodCount).getComponentDetails();
            for (int componentCount = 0; componentCount < componentDetails.size(); componentCount++) {
                if (componentDetails.get(componentCount).getComponentStatus().trim().equalsIgnoreCase(AppConstants.IN_BAY) ||
                        componentDetails.get(componentCount).getComponentStatus().trim().equalsIgnoreCase(AppConstants.ACTIVE)) {
                    if (componentDetails.get(componentCount).getComponentStatusText().trim().equalsIgnoreCase("Scanned")) {
                        scannedComponentCount = scannedComponentCount + 1;
                    }
                    totalComponentCount = totalComponentCount + 1;
                }
            }

            if (totalComponentCount > 0) {
                if (scannedComponentCount > 0 && scannedComponentCount < totalComponentCount) {
                    atLeastOneGoodPartiallyScanned = true;
                }

                if (scannedComponentCount == totalComponentCount) {
                    deliveryGoodItemLength = deliveryGoodItemLength + 1;
                    hsDeliveryGoodItemCompLength.put(deliveryGoodsDetails.get(goodCount).getDeliveryGoodId(), totalComponentCount);
                }
            }
        }
        return atLeastOneGoodPartiallyScanned;
    }

    private void onScanEventTriggered(String barcode){
        VerifyComponentBarcodeAndPopulateDeliveryItemDetailsList(barcode);
        if (!checkIfItemIsPartiallyScanned()) {
            prepareRequestDataForCreateComponentHandoverDetails();
            viewModel.createComponentHandoverDetails(requestEnvelopeCreateComponentHandoverDetails);
        }else {
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.part_delivery_handover));
        }
    }

    // TODO: NavController we have to use for this
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}