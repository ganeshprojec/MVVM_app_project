package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 25,July,2022
 */

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.ResourcesProvider;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrierCollectionAndHandoverDetailsRepository {
    private static final String TAG = CarrierCollectionAndHandoverDetailsRepository.class.getSimpleName();
    private ResourcesProvider resourcesProvider;
    private ApiService apiService;
    public MutableLiveData<Resource<ResponseDataCreateOrUpdateHandoverDetails>> _responseDataCreateOrUpdateHandoverDetails = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataCreateComponentHandoverDetails>> _responseDataCreateComponentHandoverDetails = new MutableLiveData<>();

    @Inject
    public CarrierCollectionAndHandoverDetailsRepository(ApiService apiService, ResourcesProvider resourcesProvider) {
        this.apiService = apiService;
        this.resourcesProvider = resourcesProvider;
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
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(resourcesProvider.getString(R.string.error_while_getting_the_response), null));
            }else if(response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails().getDitsErrors()!=null){
                String errorNumber = response.body().getResponseBodyCreateOrUpdateHandoverDetails().getResponseDataCreateOrUpdateHandoverDetails().getDitsErrors().getDitsError().getErrorType().getErrorNumber();
                if(errorNumber.equals(AppConstants.TWO_THOUSAND) || errorNumber.equals(AppConstants.TEN_THOUSAND)){
                    _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(resourcesProvider.getString(R.string.item_barcode_invalid), errorNumber, null));
                }else{
                    _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(response.body().getResponseBodyCreateOrUpdateHandoverDetails()
                                    .getResponseDataCreateOrUpdateHandoverDetails().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                            errorNumber, null));
                }
            } else{
                _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(resourcesProvider.getString(R.string.something_went_wrong), null));
                Log.i(TAG, resourcesProvider.getString(R.string.response_is_neither_success_nor_error));
            }
        }catch (Exception ex){
            _responseDataCreateOrUpdateHandoverDetails.postValue(Resource.error(resourcesProvider.getString(R.string.error_while_handling_the_response), null));
            Log.e(TAG, resourcesProvider.getString(R.string.error_while_handling_the_response) + ex);
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
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error(resourcesProvider.getString(R.string.error_while_getting_the_response), null));
            }else if(response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodes(response.body().getResponseBodyCreateComponentHandoverDetails().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors());
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseDataCreateComponentHandoverDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, resourcesProvider.getString(R.string.response_is_neither_success_nor_error));
            }
        }catch (Exception ex){
            _responseDataCreateComponentHandoverDetails.postValue(Resource.error(resourcesProvider.getString(R.string.error_while_handling_the_response), null));
            Log.e(TAG, resourcesProvider.getString(R.string.error_while_handling_the_response) + ex);
        }
    }

    private Pair handleCommonAPIResponseErrorCodes(DITSErrors ditsErrors) {
        Pair pairOfErrorMessageAndCode = new Pair<> (ditsErrors.getDitsError().getErrorType().getErrorMessage(),
                ditsErrors.getDitsError().getErrorType().getErrorNumber());
        String errorNumber = ditsErrors.getDitsError().getErrorType().getErrorNumber();
        if(errorNumber.equals(AppConstants.TWO_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.package_item_not_recognised), AppConstants.TWO_THOUSAND);
        } else if(errorNumber.equals(AppConstants.FOUR_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.create_has_failed), AppConstants.TWO_THOUSAND_AND_ONE);
        }else if(errorNumber.equals(AppConstants.SIX_HUNDRED)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.delivery_has_been_cancelled_and_can_not_be_handover), AppConstants.SIX_HUNDRED);
        }else if(errorNumber.equals(AppConstants.FIVE_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.update_has_failed), AppConstants.THREE_THOUSAND);
        }
        return pairOfErrorMessageAndCode;
    }
}
