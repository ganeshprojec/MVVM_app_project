package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 25,July,2022
 */

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrierCollectionAndHandoverDetailsRepository {

    private static final String TAG = CarrierCollectionAndHandoverDetailsRepository.class.getSimpleName();
    private ApiService apiService;
    public MutableLiveData<Resource<ResponseDataCreateOrUpdateHandoverDetails>> _responseDataCreateOrUpdateHandoverDetails = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataCreateComponentHandoverDetails>> _responseDataCreateComponentHandoverDetails = new MutableLiveData<>();

    @Inject
    public CarrierCollectionAndHandoverDetailsRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * Api call for createOrUpdateHandoverDetails
     * @param envelope of type RequestEnvelopeCreateOrUpdateHandoverDetails
     */
    public void createOrUpdateHandoverDetails(RequestEnvelopeCreateOrUpdateHandoverDetails envelope){
        _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.loading(null));
        Call<ResponseEnvelopeCreateOrUpdateHandoverDetails> responseEnvelopeCall
                = apiService.createOrUpdateHandoverDetails(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeCreateOrUpdateHandoverDetails>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeCreateOrUpdateHandoverDetails> call, Response<ResponseEnvelopeCreateOrUpdateHandoverDetails> response) {
                handleCreateOrUpdateHandoverDetailsResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeCreateOrUpdateHandoverDetails> call, Throwable t) {
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled handle create or update handover details response and did some validation on response
     * @param response
     */
    private void handleCreateOrUpdateHandoverDetailsResponse(Response<ResponseEnvelopeCreateOrUpdateHandoverDetails> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails().getDitsErrors()==null) {
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.success(response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails()));
            }else if(response.errorBody()!=null){
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails().getDitsErrors()!=null){
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error( response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails()));
            } else{
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE, null));
            Log.e(TAG, AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE + ex);
        }
    }


    /**
     * Api call for createOrUpdateHandoverDetails
     * @param envelope of type RequestEnvelopeCreateOrUpdateHandoverDetails
     */
    public void createComponentHandoverDetails(RequestEnvelopeCreateComponentHandoverDetails envelope){
        _responseDataCreateComponentHandoverDetails.postValue(Resource.loading(null));
        Call<ResponseEnvelopeCreateComponentHandoverDetails> responseEnvelopeCall
                = apiService.createComponentHandoverDetails(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeCreateComponentHandoverDetails>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeCreateComponentHandoverDetails> call, Response<ResponseEnvelopeCreateComponentHandoverDetails> response) {
                handleCreateComponentHandoverDetailsResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeCreateComponentHandoverDetails> call, Throwable t) {
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled handle create or update handover details response and did some validation on response
     * @param response
     */
    private void handleCreateComponentHandoverDetailsResponse(Response<ResponseEnvelopeCreateComponentHandoverDetails> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors()==null) {
                _responseDataCreateComponentHandoverDetails.postValue(Resource.success(response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems()));
            }else if(response.errorBody()!=null){
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors()!=null){
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error( response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems()));
            } else{
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseDataCreateComponentHandoverDetails.postValue(Resource.error(AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE, null));
            Log.e(TAG, AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE + ex);
        }
    }
}
