package com.jlp.mvvm_jlp_project.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items.RequestEnvelopeFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product.RequestEnvelopeFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_handover_details.RequestEnvelopeFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestEnvelopeRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestBodyRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestEnvelopRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.LotDetails;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.RequestEnvelopeAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseDataFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.DeliveryItemDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseDataFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.ResponseDataFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.record_location_of_item.ResponseDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response.ResponseDataAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.repository.CommonBarcodeScannerRepository;
import com.jlp.mvvm_jlp_project.repository.RouteManagementSummaryRepository;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class CommonBarcodeScannerViewModel extends BaseViewModel {
    CommonBarcodeScannerRepository repository;
    RouteManagementSummaryRepository routeSummayRepo;

    public MutableLiveData<Pair<Boolean, Integer>> validationResult = new MutableLiveData<>();

    public MutableLiveData<List<TitleValueDataModel>> itemEnquiry = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>> responseFindDeliveryDetailsForComponentBarcode
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindLocationDetailsForBarcode>> responseFindLocationDetailsForBarcode
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataRecordLocationOfItem>> responseDataRecordLocationOfItem
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindDeliveryItemDetailsForComponentBarcode>> responseFindDeliveryItemDetailsForComponentBarcode
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataRouteManagementSummary>> responseSummaryDetails
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindHandoverDetails>> responseFindHandoverDetails
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindDeliveriesAndDeliveryItems>> responseFindDeliveriesAndDeliveryItems
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindDeliveryGoodProduct>> responseDataFindDeliveryGoodProduct = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataAmendLotNumerUpdate>> responseDataAmendLotNumberUpdate = new MutableLiveData<>();


    @Inject
    public CommonBarcodeScannerViewModel(@NonNull Application application, CommonBarcodeScannerRepository repository, RouteManagementSummaryRepository routeSummayRepo) {
        super(application);
        this.routeSummayRepo = routeSummayRepo;
        this.repository = repository;
        this.responseSummaryDetails = routeSummayRepo._responseSummary;
        this.responseFindDeliveryDetailsForComponentBarcode = repository._responseFindDeliveryDetailsForComponentBarcode;
        this.responseFindLocationDetailsForBarcode = repository._responseFindLocationDetailsForBarcode;
        this.responseDataRecordLocationOfItem = repository._responseDataRecordLocationOfItem;
        this.responseFindDeliveryItemDetailsForComponentBarcode = repository._responseFindDeliveryItemDetailsForComponentBarcode;
        this.responseFindHandoverDetails = repository._responseFindHandoverDetails;
        this.responseFindDeliveriesAndDeliveryItems = repository._responseFindDeliveriesAndDeliveryItems;
        this.responseDataAmendLotNumberUpdate = repository._responseDataAmendLotNumerUpdate;
        this.responseDataFindDeliveryGoodProduct = repository._responseDataFindDeliveryGoodProduct;
    }

    public void findDeliveryDetailsForComponentBarcode(RequestEnvelopeFindDeliveryDetailsForComponentBarcode envelope) {
        repository.findDeliveryDetailsForComponentBarcode(envelope);
    }

    public void findLocationDetailsForBarcode(RequestEnvelopeFindLocationDetailsForBarcode envelope) {
        repository.findLocationDetailsForBarcode(envelope);
    }

    public void findHanoverDetails(RequestEnvelopeFindHandoverDetails envelope) {
        repository.findHanoverDetails(envelope);
    }

    public void findDeliveriesAndDeliveryItems(RequestEnvelopeFindDeliveriesAndDeliveryItems envelope) {
        repository.findDeliveriesAndDeliveryItems(envelope);
    }

    public void findDeliveryItemDetailsForComponentBarcode(RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode envelope) {
        repository.findDeliveryItemDetailsForComponentBarcode(envelope);
    }

    public void recordLocationOfItem(RequestEnvelopeRecordLocationOfItem envelope) {
        repository.recordLocationOfItem(envelope);
    }

    public void findDeliveryGoodProducts(RequestEnvelopeFindDeliveryGoodProduct envelope){
        repository.findDeliveryGoodProducts(envelope);
    }

    public void findUpdateLotNumRequired(RequestEnvelopeAmendLotNumerUpdate envelope) {
        repository.updateLostNumerRequire(envelope);
    }

    public void validateBarcode(String barcode, String callFor) {
        Pair result = new Pair<>(true, 0);
        switch (callFor) {
            case AppConstants.FRAGMENT_ITEM_ENQUIRY:
            case AppConstants.FRAGMENT_MULTI_MOVEMENT_FOR_COMPONENT_BARCODE:
            case AppConstants.FRAGMENT_ITEM_MOVEMENT_FOR_COMPONENT_BARCODE: {
                if (TextUtils.isEmpty(barcode)) {
                    result = new Pair(false, R.string.item_barcode_required);
                }
                break;
            }
            case AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS:
            case AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS:{
                if (TextUtils.isEmpty(barcode)) {
                    result = new Pair(false, R.string.please_enter_delivery_number);
                } else if(barcode.contains("@@")){
                    result = new Pair(false, R.string.delivery_reference_not_recognised);
                }else if(barcode.contains("!!")){
                    result = new Pair(false, R.string.delivery_reference_not_recognised);
                }
                break;
            }
            case AppConstants.FRAGMENT_REPRINT_LABELS: {
                if (TextUtils.isEmpty(barcode)) {
                    result = new Pair(false, R.string.enter_delivery_number_scan);
                } else if (barcode.length() < 6) {
                    result = new Pair(false, R.string.invalid_deliveryno_barcode);
                }
                break;
            }

            case AppConstants.FRAGMENT_ROUTE_MANAGEMENT: {
                if (TextUtils.isEmpty(barcode)) {
                    result = new Pair(false, R.string.enter_route_number_scan);
                } else if (barcode.length() < 18) {
                    result = new Pair(false, R.string.invalid_route_id);
                }
                break;
            }
        }

        validationResult.setValue(result);
    }

    public void getComponentBarcodeData(DeliveryItemProductDetails deliveryItemProductDetails) {
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(R.string.delivery_number,
                deliveryItemProductDetails.getDeliveryId()));
        titleValueDataModels.add(new TitleValueDataModel(R.string.route_number,
                deliveryItemProductDetails.getRouteResourceKey()));
        String deliveryDate =  deliveryItemProductDetails.getDeliveryDate();
        if (!TextUtils.isEmpty(deliveryDate)) {//deliveryDate.Text = datetime.ToString("dd.MM.yyyy");
            deliveryDate = StringUtils.getFormattedDate(deliveryDate, AppConstants.APP_DATE_FORMAT);
        } else {deliveryDate = "";}
        titleValueDataModels.add(new TitleValueDataModel(R.string.delivery_date,
                deliveryDate));
        titleValueDataModels.add(new TitleValueDataModel(R.string.last_recorded_location,
                deliveryItemProductDetails.getName15()));
        String lastUpdatedTimeStamp = deliveryItemProductDetails.getLastUpdatedTimeStamp();
        if (!TextUtils.isEmpty(lastUpdatedTimeStamp)) {
            lastUpdatedTimeStamp = StringUtils.getFormattedDate(lastUpdatedTimeStamp, AppConstants.APP_DATE_TIME_FORMAT);
        }
        else lastUpdatedTimeStamp = "-";
        titleValueDataModels.add(new TitleValueDataModel(R.string.time_of_last_move,
                lastUpdatedTimeStamp));
        String lastUpdatedUserId = deliveryItemProductDetails.getLastUpdatedUserId();
        if (TextUtils.isEmpty(lastUpdatedUserId)) { lastUpdatedUserId = "-"; }
        titleValueDataModels.add(new TitleValueDataModel(R.string.last_user_id,
                lastUpdatedUserId));
        titleValueDataModels.add(new TitleValueDataModel(R.string.product_code,
                deliveryItemProductDetails.getProductCode()));
        titleValueDataModels.add(new TitleValueDataModel(R.string.product_description,
                deliveryItemProductDetails.getOrderDescriptionClean()));
        String lotNumber = deliveryItemProductDetails.getCurrentLotNumber()+" of "+deliveryItemProductDetails.getTotalLotNumber();
        titleValueDataModels.add(new TitleValueDataModel(R.string.lot_number, lotNumber));

        String address = "";

        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressBuildingName)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressBuildingName;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressPremise)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressPremise;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressThoroughFare)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressThoroughFare;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressCompanyName)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressCompanyName;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressLocality)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressLocality;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressPostTown)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressPostTown;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressCounty)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressCounty;
        }
        if(!TextUtils.isEmpty(deliveryItemProductDetails.deliveryAddressPostCode)){
            address = address +" "+deliveryItemProductDetails.deliveryAddressPostCode;
        }

        titleValueDataModels.add(new TitleValueDataModel(R.string.address, address));
        itemEnquiry.setValue(titleValueDataModels);
    }

    public void getItemDetailsComponentBarcodeData(DeliveryItemDetails deliveryItemDetails) {
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(R.string.delivery_number,
                deliveryItemDetails.getDeliveryId()
        ));
        titleValueDataModels.add(new TitleValueDataModel(R.string.route_number,
                deliveryItemDetails.getRouteResourceKey()
        ));
        titleValueDataModels.add(new TitleValueDataModel(R.string.item,
                deliveryItemDetails.getGoodId()));
        titleValueDataModels.add(new TitleValueDataModel(R.string.product_description,
                deliveryItemDetails.getOrderDescriptionClean()));
        itemEnquiry.setValue(titleValueDataModels);
    }

    public void getLocationBarcodeData(DeliveryItemProductDetails deliveryItemProductDetails,
                                       LocationDetails locationDetails) {
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(R.string.delivery_number,
                deliveryItemProductDetails.getDeliveryId()
        ));
        titleValueDataModels.add(new TitleValueDataModel(R.string.route_number,
                deliveryItemProductDetails.getRouteResourceKey()
        ));
        titleValueDataModels.add(new TitleValueDataModel(R.string.item,
                deliveryItemProductDetails.getProductCode()));
        titleValueDataModels.add(new TitleValueDataModel(R.string.product_description,
                deliveryItemProductDetails.getOrderDescriptionClean()));
        itemEnquiry.setValue(titleValueDataModels);
    }

    public void updateAdapterData(String lotNumber, String location) {
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(R.string.lot_number, lotNumber));
        titleValueDataModels.add(new TitleValueDataModel(R.string.stored_in_location, location));
        itemEnquiry.setValue(titleValueDataModels);
    }


    public void callSummaryDetails(String barcodeRoute) {
        routeSummayRepo.callFindRouteManagementSummary(getEnvelopSummaryDetails(barcodeRoute));
    }


    private RequestEnvelopRouteManagementSummary getEnvelopSummaryDetails(String routeId) {

        RequestEnvelopRouteManagementSummary requestEnvelop = new RequestEnvelopRouteManagementSummary();
        RequestBodyRouteManagementSummary requestBody = new RequestBodyRouteManagementSummary();
        RequestDataRouteManagementSummary requestData = new RequestDataRouteManagementSummary();

        requestData.setRouteId(routeId);
        requestBody.setRequestDataRouteManagementSummary(requestData);
        requestEnvelop.setRequestBodyRouteManagementSummary(requestBody);
        return requestEnvelop;
    }

    public void validateLotNumberRequired(String inputLotsRequired, String totalLotNum){
        Pair resultLotRequired =new Pair<>(true,0);
        if(TextUtils.isEmpty(inputLotsRequired)){
            resultLotRequired = new Pair(false, R.string.lot_number_required);
        }else if(inputLotsRequired.equals(totalLotNum)){
            resultLotRequired = new Pair(false, R.string.number_lots_mustbe_different);
        }else if(inputLotsRequired.equals("0")){
            resultLotRequired = new Pair(false, R.string.invalid_input);
        }
        validationResult.setValue(resultLotRequired);

    }

    public void getAmendLotItemDetails(DeliveryItemDetails deliveryItemDetails){
        List<TitleValueDataModel> titleValueDataModels = new ArrayList<>();
        titleValueDataModels.add(new TitleValueDataModel(R.string.delivery_number,deliveryItemDetails.getDeliveryId()));

        titleValueDataModels.add(new TitleValueDataModel(R.string.item,
                deliveryItemDetails.getProductCode()));
        titleValueDataModels.add(new TitleValueDataModel(R.string.product_description,
                deliveryItemDetails.getOrderDescriptionClean()));
        titleValueDataModels.add(new TitleValueDataModel(R.string.str_current_lot,deliveryItemDetails.getCurrentLotNumber()));
        itemEnquiry.setValue(titleValueDataModels);
    }

    public void updateAmendLotNumAdapterData(LotDetails lotDetails, DeliveryItemDetails deliveryItemDetails) {
        List<TitleValueDataModel> titleValueDataModels = itemEnquiry.getValue();
        titleValueDataModels.remove(3);
        titleValueDataModels.add(new TitleValueDataModel(R.string.str_current_lot, lotDetails.getUpdatedCurrentLot()));
        itemEnquiry.setValue(titleValueDataModels);
    }
}