package com.jlp.mvvm_jlp_project.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.route_details.RequestEnvelopeRouteDetails;
import com.jlp.mvvm_jlp_project.model.request.route_item_update_status.RequestEnvelopeUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.request.route_management_summary.RequestEnvelopRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.response.route_details.ResponseDataRouteDetails;
import com.jlp.mvvm_jlp_project.model.response.route_details.ResponseEnvelopeRouteDetails;
import com.jlp.mvvm_jlp_project.model.response.route_item_update_status.ResponseDataUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.response.route_item_update_status.ResponseEnvelopeUpdateItemStatus;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseDataRouteManagementSummary;
import com.jlp.mvvm_jlp_project.model.response.route_management_summary.ResponseEnvelopeRouteManagementSummary;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RouteManagementSummaryRepository {
    private static final String TAG = RouteManagementSummaryRepository.class.getSimpleName();

    private final ApiService apiService;

    public MutableLiveData<Resource<ResponseDataRouteManagementSummary>> _responseSummary = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataRouteDetails>> _responseRouteDetails = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataUpdateItemStatus>> _responseUpdateStatus = new MutableLiveData<>();


    @Inject
    public RouteManagementSummaryRepository(ApiService apiService) {
        this.apiService = apiService;
    }


    /**
     * Api call for FindRouteManagementSummary
     *
     * @param envelope RequestEnvelopRouteManagementSummary
     */
    public void callFindRouteManagementSummary(RequestEnvelopRouteManagementSummary envelope) {
        _responseSummary.postValue(Resource.loading(null));

        Call<ResponseEnvelopeRouteManagementSummary> responseEnvelopeCall
                = apiService.serviceSummaryDetailsApi(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeRouteManagementSummary>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeRouteManagementSummary> call, Response<ResponseEnvelopeRouteManagementSummary> response) {
                handleFindSummaryDetails(response);
            }

            @Override
            public void onFailure(Call<ResponseEnvelopeRouteManagementSummary> call, Throwable t) {
                _responseSummary.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }


    /**
     * Api call for FindRouteManagementSummary
     *
     * @param envelope RequestEnvelopRouteManagementSummary
     */
    public void callFindRouteManagementDetails(RequestEnvelopeRouteDetails envelope) {
        _responseRouteDetails.postValue(Resource.loading(null));

        Call<ResponseEnvelopeRouteDetails> responseEnvelopeCall
                = apiService.serviceRouteDetailsApi(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeRouteDetails>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeRouteDetails> call, Response<ResponseEnvelopeRouteDetails> response) {
                handleRouteDetails(response);
            }

            @Override
            public void onFailure(Call<ResponseEnvelopeRouteDetails> call, Throwable t) {
                _responseRouteDetails.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }


    /**
     * Api call for FindRouteManagementSummary
     *
     * @param envelope RequestEnvelopeUpdateItemStatus
     */
    public void callUpdateItemStatus(RequestEnvelopeUpdateItemStatus envelope) {
        _responseUpdateStatus.postValue(Resource.loading(null));

        Call<ResponseEnvelopeUpdateItemStatus> responseEnvelopeCall
                = apiService.serviceUpdateItemStatus(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeUpdateItemStatus>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeUpdateItemStatus> call, Response<ResponseEnvelopeUpdateItemStatus> response) {
                handleUpdateItemStatus(response);
            }

            @Override
            public void onFailure(Call<ResponseEnvelopeUpdateItemStatus> call, Throwable t) {
                _responseUpdateStatus.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }


    /**
     * handled find Summary details for Route management response and did some validation on response
     *
     * @param response ResponseEnvelopeRouteManagementSummary to manipulate the error data
     */
    private void handleFindSummaryDetails(Response<ResponseEnvelopeRouteManagementSummary> response) {
        try {
            if (response.isSuccessful() && response.body() != null &&
                    response.body().getResponseBodyRouteManagementSummary().
                            getResponseDataRouteManagementSummary().getDitsErrors() == null) {

                _responseSummary.postValue(Resource.success(response.body().
                        getResponseBodyRouteManagementSummary().getResponseDataRouteManagementSummary()));

            } else if (response.errorBody() != null) {

                _responseSummary.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));

            } else if (response.body().getResponseBodyRouteManagementSummary().getResponseDataRouteManagementSummary().getDitsErrors() != null) {

                _responseSummary.postValue(Resource.error(response.body().getResponseBodyRouteManagementSummary().
                                getResponseDataRouteManagementSummary().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBodyRouteManagementSummary().
                                getResponseDataRouteManagementSummary()));

            } else {

                _responseSummary.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);

            }
        } catch (Exception ex) {
            _responseSummary.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));

            Log.e(TAG, AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE + ex);

        }
    }


    /**
     * handled find Summary details for Route management response and did some validation on response
     *
     * @param response ResponseEnvelopeRouteDetails to manipulate the error data
     */
    private void handleRouteDetails(Response<ResponseEnvelopeRouteDetails> response) {
        try {
            if (response.isSuccessful() && response.body() != null &&
                    response.body().getResponseBody().
                            getResponseData().getDitsErrors() == null) {

                _responseRouteDetails.postValue(Resource.success(response.body().
                        getResponseBody().getResponseData()));

            } else if (response.errorBody() != null) {

                _responseRouteDetails.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));

            } else if (response.body().getResponseBody().getResponseData().getDitsErrors() != null) {

                _responseRouteDetails.postValue(Resource.error(response.body().getResponseBody().
                                getResponseData().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBody().
                                getResponseData()));

            } else {

                _responseRouteDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);

            }
        } catch (Exception ex) {
            _responseRouteDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));

            Log.e(TAG, AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE + ex);

        }
    }


    /**
     * handled find Summary details for Route management response and did some validation on response
     *
     * @param response ResponseEnvelopeRouteDetails to manipulate the error data
     */
    private void handleUpdateItemStatus(Response<ResponseEnvelopeUpdateItemStatus> response) {
        try {
            if (response.isSuccessful() && response.body() != null &&
                    response.body().getResponseBody().
                            getResponseData().getDitsErrors() == null) {

                _responseUpdateStatus.postValue(Resource.success(response.body().
                        getResponseBody().getResponseData()));

            } else if (response.errorBody() != null) {

                _responseUpdateStatus.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));

            } else if (response.body().getResponseBody().getResponseData().getDitsErrors() != null) {

                _responseUpdateStatus.postValue(Resource.error(response.body().getResponseBody().
                                getResponseData().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBody().
                                getResponseData()));

            } else {

                _responseUpdateStatus.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);

            }
        } catch (Exception ex) {
            _responseUpdateStatus.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG, AppConstants.ERROR_SOMETHING_WENT_WRONG + ex);

        }
    }


}
