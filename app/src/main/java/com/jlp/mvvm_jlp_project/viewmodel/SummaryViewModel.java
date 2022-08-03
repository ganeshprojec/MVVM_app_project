package com.jlp.mvvm_jlp_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestBodyRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestEnvelopRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.repository.RouteManagementSummaryRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SummaryViewModel extends BaseViewModel {

    RouteManagementSummaryRepository repository;

    public MutableLiveData<Resource<ResponseDataRouteManagementSummary>> responseSummaryDetails
            = new MutableLiveData<>();


    /**
     * @param application Application
     * @author Ganesh
     * <p>
     * For ViewModel constructor
     */
    @Inject
    public SummaryViewModel(@NonNull Application application, RouteManagementSummaryRepository repository) {
        super(application);
        this.repository = repository;
        this.responseSummaryDetails = repository._responseSummary;
    }


    /**
     * @param barcodeRoute Route Resource ID came from Barcode scanner page
     * @author Ganesh
     * <p>
     * For calling summary details page.
     */
    public void callSummaryDetails(String barcodeRoute) {
        repository.callFindRouteManagementSummary(getEnvelop(barcodeRoute));
    }

    /**
     * @param routeId String Rote ID came from scanner page
     * @author Ganesh
     * <p>
     * For creating envelop for Summary Details
     */
    private RequestEnvelopRouteManagementSummary getEnvelop(String routeId) {

        RequestEnvelopRouteManagementSummary requestEnvelop = new RequestEnvelopRouteManagementSummary();
        RequestBodyRouteManagementSummary requestBody = new RequestBodyRouteManagementSummary();
        RequestDataRouteManagementSummary requestData = new RequestDataRouteManagementSummary();

        requestData.setRouteId(routeId);
        requestBody.setRequestDataRouteManagementSummary(requestData);
        requestEnvelop.setRequestBodyRouteManagementSummary(requestBody);

        return requestEnvelop;
    }


}
