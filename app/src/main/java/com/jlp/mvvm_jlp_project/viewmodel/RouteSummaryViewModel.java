package com.jlp.mvvm_jlp_project.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.DeliveryDetails;
import com.jlp.mvvm_jlp_project.model.ItemStatusDetails;
import com.jlp.mvvm_jlp_project.model.LotsInfo;
import com.jlp.mvvm_jlp_project.model.RouteSummary;
import com.jlp.mvvm_jlp_project.model.request.route_details.RequestBodyRouteDetails;
import com.jlp.mvvm_jlp_project.model.request.route_details.RequestDataRouteDetails;
import com.jlp.mvvm_jlp_project.model.request.route_details.RequestEnvelopeRouteDetails;
import com.jlp.mvvm_jlp_project.model.request.route_item_update_status.RequestBodyUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.request.route_item_update_status.RequestDataUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.request.route_item_update_status.RequestEnvelopeUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestEnvelopRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.response.route_details.ResponseDataRouteDetails;
import com.jlp.mvvm_jlp_project.model.response.route_item_update_status.ResponseDataUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.repository.RouteManagementSummaryRepository;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ActivityContext;

@HiltViewModel
public class RouteSummaryViewModel extends BaseViewModel {

    private ArrayList<DeliveryDetails> backupList = new ArrayList<>();
    public RouteSummary summary = new RouteSummary();

    RouteManagementSummaryRepository repository;

    public MutableLiveData<Resource<ResponseDataRouteManagementSummary>> responseSummaryDetails
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataRouteDetails>> responseRouteDeliveryDetails
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataUpdateItemStatus>> responseUpdateItemStatus
            = new MutableLiveData<>();


    /**
     * @param application Application context
     * @param repository  backend call
     * @author Ganesh
     * <p>
     * For ViewModel constructor
     */
    @Inject
    public RouteSummaryViewModel(@NonNull Application application, RouteManagementSummaryRepository repository) {
        super(application);

        this.repository = repository;
        this.responseSummaryDetails = repository._responseSummary;
        this.responseRouteDeliveryDetails = repository._responseRouteDetails;
        this.responseUpdateItemStatus = repository._responseUpdateStatus;
    }

    public ArrayList<DeliveryDetails> getBackupList() {
        return backupList;
    }

    // for dummy List populate
    public ArrayList<DeliveryDetails> getDummyList(@ActivityContext Context context) {
        ArrayList<DeliveryDetails> list = new ArrayList<>();

        ArrayList<LotsInfo> lots = new ArrayList<>();
        for (int j = 0; j < 10; j++) {

            lots.add(new LotsInfo(Helper.getXmlString(getApplication(), R.string.dummy_lotSr) + (j + 1),
                    Helper.getXmlString(getApplication(), R.string.dummy_location_b5), "", "", "", "", ""));
        }

        for (int i = 0; i < 15; i++) {
            list.add(new DeliveryDetails(Helper.getXmlString(getApplication(), R.string.dummy_delivery_number),
                    Helper.getXmlString(getApplication(), R.string.dummy_customer_name), Helper.getXmlString(getApplication(), R.string.dummy_item_num),
                    Helper.getXmlString(getApplication(), R.string.dummy_product_desc),
                    lots));
        }

        backupList = new ArrayList<>();
        backupList.addAll(list);

        return list;

    }


    /**
     * @param location String name by which filtering
     * @author Ganesh
     * <p>
     * For Filter logic for the list of Lots and resultant delivery details return
     */
    public ArrayList<DeliveryDetails> getFilterByLocation(String location) {
        ArrayList<DeliveryDetails> list = new ArrayList<>();

        for (int i = 0; i < backupList.size(); i++) {

            ArrayList<LotsInfo> lotsList = getFilteredLots(backupList.get(i).getLotsList(), location);

            // add in filtered Main list
            DeliveryDetails filteredModel = DeliveryDetails.getCopy(backupList.get(i));
            filteredModel.setLotsList(lotsList);
            list.add(filteredModel);
        }

        return list;
    }

    /**
     * @param lots     list of lots in One Delivery Goods to be filtered out
     * @param location String to which by filtering
     * @author Ganesh
     * <p>
     * For filter from list of lots
     */
    private ArrayList<LotsInfo> getFilteredLots(ArrayList<LotsInfo> lots, String location) {
        ArrayList<LotsInfo> tempList = new ArrayList<>();

        for (int i = 0; i < lots.size(); i++) {
            /*String tempLocation = lots.get(i).getLotNumber();
            if (!(tempLocation.equalsIgnoreCase(location))) {
                tempList.add(LotsInfo.getCopy(lots.get(i)));
            }*/
            //TODO: Check condition for NOT STORED & LOADED once text available
            String tempLocationName = lots.get(i).getLotLocation();
            if (!(tempLocationName.toLowerCase().contains(location.trim().toLowerCase()))) {
                tempList.add(LotsInfo.getCopy(lots.get(i)));
            }
        }

        return tempList;
    }

    /**
     * @param envelope request envelop
     * @author Ganesh
     * <p>
     * For calling summary webservice,
     * NOte: This is only ready service call, no use on this page
     */
    public void callFindSummaryDetails(RequestEnvelopRouteManagementSummary envelope) {
        repository.callFindRouteManagementSummary(envelope);
    }

    /**
     * @param deliveryId Application context
     * @author Ganesh
     * <p>
     * For calling webservice
     */
    public void callRouteDetails(String deliveryId) {
        repository.callFindRouteManagementDetails(getEnvelop(deliveryId));
    }

    /**
     * @param itemUpdateStatus request data model
     * @author Ganesh
     * <p>
     * For calling actual web service call of Update status, for LOADED / MISSING
     */
    public void callUpdateStatus(ItemStatusDetails itemUpdateStatus) {
        repository.callUpdateItemStatus(getEnvelop(itemUpdateStatus));
    }


    /**
     * @param itemUpdateStatus model
     * @author Ganesh
     * <p>
     * For creating envelop for Update Status
     */
    private RequestEnvelopeUpdateItemStatus getEnvelop(ItemStatusDetails itemUpdateStatus) {

        RequestEnvelopeUpdateItemStatus requestEnvelop = new RequestEnvelopeUpdateItemStatus();
        RequestBodyUpdateItemStatus requestBody = new RequestBodyUpdateItemStatus();
        RequestDataUpdateItemStatus requestData = new RequestDataUpdateItemStatus();

        requestData.setItemStatus(itemUpdateStatus);
        requestBody.setRequestData(requestData);
        requestEnvelop.setRequestBody(requestBody);

        return requestEnvelop;
    }


    /**
     * @param deliveryId model
     * @author Ganesh
     * <p>
     * For creating envelop for Route & Deliveries Details
     */
    private RequestEnvelopeRouteDetails getEnvelop(String deliveryId) {

        RequestEnvelopeRouteDetails requestEnvelop = new RequestEnvelopeRouteDetails();
        RequestBodyRouteDetails requestBody = new RequestBodyRouteDetails();
        RequestDataRouteDetails requestData = new RequestDataRouteDetails();
        requestData.setDeliveryId(deliveryId);
        requestBody.setRequestData(requestData);
        requestEnvelop.setRequestBody(requestBody);

        return requestEnvelop;
    }


}
