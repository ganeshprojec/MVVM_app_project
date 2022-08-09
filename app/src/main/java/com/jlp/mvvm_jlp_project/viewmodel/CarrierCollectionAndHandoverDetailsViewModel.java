package com.jlp.mvvm_jlp_project.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.DeliveryDetails;
import com.jlp.mvvm_jlp_project.repository.CarrierCollectionAndHandoverDetailsRepository;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class CarrierCollectionAndHandoverDetailsViewModel extends BaseViewModel {
    CarrierCollectionAndHandoverDetailsRepository repository;
    public MutableLiveData<List<TitleValueDataModel>> itemDelivery = new MutableLiveData<>();
    public MutableLiveData<Pair<Boolean, Integer>> validationResult = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataCreateOrUpdateHandoverDetails>> responseDataCreateOrUpdateHandoverDetails = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataCreateComponentHandoverDetails>> responseDataCreateComponentHandoverDetails = new MutableLiveData<>();

    @Inject
    public CarrierCollectionAndHandoverDetailsViewModel(@NonNull Application application, CarrierCollectionAndHandoverDetailsRepository repository) {
        super(application);
        this.repository = repository;
        this.responseDataCreateOrUpdateHandoverDetails = repository._responseDataCreateOrUpdateHandoverDetails;
        this.responseDataCreateComponentHandoverDetails = repository._responseDataCreateComponentHandoverDetails;
    }

    public void createOrUpdateHandoverDetails(RequestEnvelopeCreateOrUpdateHandoverDetails envelope){
        repository.createOrUpdateHandoverDetails(envelope);
    }

    public void createComponentHandoverDetails(RequestEnvelopeCreateComponentHandoverDetails envelope){
        repository.createComponentHandoverDetails(envelope);
    }


    public void getItemDetailsDeliveryBarcodeData(DeliveryDetails deliveryDetails) {
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(R.string.delivery_number,
                deliveryDetails.getDeliveryId()
        ));
        titleValueDataModels.add(new TitleValueDataModel(R.string.customer_name,
                deliveryDetails.getRecipientName().trim()
        ));
        titleValueDataModels.add(new TitleValueDataModel(R.string.no_of_delivery_items,
                deliveryDetails.getNumberOfDeliveryItems())); // TODO: Need to confirm mapping
        titleValueDataModels.add(new TitleValueDataModel(R.string.total_number_of_parts_lots,
                deliveryDetails.totalLotNumber));
        itemDelivery.setValue(titleValueDataModels);
    }

    public void addItemDetailsInRecycler(int itemCount, String  itemName) {
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(itemCount, 0,
                itemName
        ));
        itemDelivery.setValue(titleValueDataModels);
    }

    public void ValidateCreateOrUpdateHandoverDetails(String deliveryBy,
                                                      String deliveryOn,
                                                      String earliestDelivery,
                                                      String deliveryToCustomerOn,
                                                      String latestDeliveryTime,
                                                      String onwardTrackingRef,
                                                      String callFor) {
        if(TextUtils.isEmpty(deliveryBy)){
            validationResult.setValue(new Pair(false, R.string.external_carrier_name_must_be_a_minimum_and_maximum));
            return;
        }if(deliveryBy.length()<2){
            validationResult.setValue(new Pair(false, R.string.external_carrier_name_must_be_a_minimum_and_maximum));
            return;
        }if(deliveryBy.length()>30){
            validationResult.setValue(new Pair(false, R.string.external_carrier_name_must_be_a_minimum_and_maximum));
            return;
        }else if(TextUtils.isEmpty(deliveryToCustomerOn)){
            validationResult.setValue(new Pair(false, R.string.delivery_date_must_be_today_date_or_future_date));
            return;
        }else if(TextUtils.isEmpty(latestDeliveryTime)){
            validationResult.setValue(new Pair(false, R.string.please_enter_latest_delivery_time));
            return;
        }else if(TextUtils.isEmpty(onwardTrackingRef)){
            validationResult.setValue(new Pair(false, R.string.please_onward_tracking_reference));
            return;
        }else if(onwardTrackingRef.length()<2){
            validationResult.setValue(new Pair(false, R.string.please_external_carrier_reference_must_bea_minimum_and_maximum));
            return;
        }else if(TextUtils.isEmpty(deliveryOn) && callFor.equals(AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS)) {
            validationResult.setValue(new Pair(false, R.string.enter_delivery_on_date));
            return;
        }else if(TextUtils.isEmpty(earliestDelivery) && callFor.equals(AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS)){
            validationResult.setValue(new Pair(false, R.string.please_enter_earliest_delivery_time));
            return;
        }else if(TextUtils.isEmpty(earliestDelivery) && callFor.equals(AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS)){
            validationResult.setValue(new Pair(false, R.string.delivery_time_if_delivery_is_for_today));
            return;
        }else {
            validationResult.setValue(new Pair(true, 0));
        }
    }
}