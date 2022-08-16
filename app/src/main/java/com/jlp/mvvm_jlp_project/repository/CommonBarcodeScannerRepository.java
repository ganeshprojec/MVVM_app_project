package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product.RequestEnvelopeFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.RequestEnvelopeAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items.RequestEnvelopeFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_handover_details.RequestEnvelopeFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.RequestEnvelopeRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseDataFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseEnvelopeFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response.ResponseDataAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response.ResponseEnvelopeAmendLotNumerUpdate;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseEnvelopeFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseDataFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.ResponseDataFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.ResponseEnvelopeFindHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.record_location_of_item.ResponseDataRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.model.response.record_location_of_item.ResponseEnvelopeRecordLocationOfItem;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.ResourcesProvider;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommonBarcodeScannerRepository {
    private static final String TAG = CommonBarcodeScannerRepository.class.getSimpleName();
    private final ApiService apiService;
    private ResourcesProvider resourcesProvider;
    public MutableLiveData<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>> _responseFindDeliveryDetailsForComponentBarcode = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataFindLocationDetailsForBarcode>> _responseFindLocationDetailsForBarcode = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataRecordLocationOfItem>> _responseDataRecordLocationOfItem = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataFindDeliveryItemDetailsForComponentBarcode>> _responseFindDeliveryItemDetailsForComponentBarcode = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataAmendLotNumerUpdate>> _responseDataAmendLotNumerUpdate = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataFindDeliveryGoodProduct>> _responseDataFindDeliveryGoodProduct = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataFindHandoverDetails>> _responseFindHandoverDetails = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataFindDeliveriesAndDeliveryItems>> _responseFindDeliveriesAndDeliveryItems = new MutableLiveData<>();


    @Inject public CommonBarcodeScannerRepository(ApiService apiService, ResourcesProvider resourcesProvider) {
        this.apiService = apiService;
        this.resourcesProvider = resourcesProvider;
    }

    /**
     * Api call for findDeliveryDetailsForComponentBarcode
     * @param envelope RequestEnvelopeFindLocationDetailsForBarcode
     */
    public void findDeliveryDetailsForComponentBarcode(RequestEnvelopeFindDeliveryDetailsForComponentBarcode envelope){
        _responseFindDeliveryDetailsForComponentBarcode.postValue(Resource.loading(null));
        Call<ResponseEnvelopeFindDeliveryDetailsForComponentBarcode> responseEnvelopeCall
                = apiService.findDeliveryDetailsForComponentBarcode(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeFindDeliveryDetailsForComponentBarcode>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeFindDeliveryDetailsForComponentBarcode> call, Response<ResponseEnvelopeFindDeliveryDetailsForComponentBarcode> response) {
                handleFindDeliveryDetailsForComponentBarcode(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeFindDeliveryDetailsForComponentBarcode> call, Throwable t) {
                _responseFindDeliveryDetailsForComponentBarcode.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find location details for barcode response and did some validation on response
     * @param response ResponseEnvelopeFindLocationDetailsForBarcode to manipulate the error data
     */
    private void handleFindDeliveryDetailsForComponentBarcode(Response<ResponseEnvelopeFindDeliveryDetailsForComponentBarcode> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyFindDeliveryDetailsForComponentBarcode().
                            getResponseDataFindDeliveryDetailsForComponentBarcode().getDitsErrors()==null) {
                _responseFindDeliveryDetailsForComponentBarcode.postValue(Resource.success(response.body().
                        getResponseBodyFindDeliveryDetailsForComponentBarcode().getResponseDataFindDeliveryDetailsForComponentBarcode()));
            }else if(response.errorBody()!=null){
                _responseFindDeliveryDetailsForComponentBarcode.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyFindDeliveryDetailsForComponentBarcode().getResponseDataFindDeliveryDetailsForComponentBarcode().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodesForComponentAndLocationBarcode(response.body().getResponseBodyFindDeliveryDetailsForComponentBarcode().getResponseDataFindDeliveryDetailsForComponentBarcode().getDitsErrors(),
                        resourcesProvider.getString(R.string.item_barcode_invalid));
                _responseFindLocationDetailsForBarcode.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseFindDeliveryDetailsForComponentBarcode.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG ,"Response is neither success nor error");
            }
        }catch (Exception ex){
            _responseFindDeliveryDetailsForComponentBarcode.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG ,AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE+ex);
        }
    }


    /**
     * Api call for findDeliveryItemDetailsForComponentBarcode
     * @param envelope RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode
     */
    public void findDeliveryItemDetailsForComponentBarcode(RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode envelope){
        _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.loading(null));
        Call<ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode> responseEnvelopeCall
                = apiService.findDeliveryItemDetailsForComponentBarcode(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode> call, Response<ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode> response) {
                handleFindDeliveryItemDetailsForComponentBarcode(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode> call, Throwable t) {
                _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find Delivery Item Details For Component Barcode response and did some validation on response
     * @param response ResponseEnvelopeFindDeliveryDetailsForComponentBarcode to manipulate the error data
     */
    private void handleFindDeliveryItemDetailsForComponentBarcode(Response<ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyFindDeliveryItemDetailsForComponentBarcode().
                            getResponseDataFindDeliveryItemDetailsForComponentBarcode().getDitsErrors()==null) {
                _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.success(response.body().
                        getResponseBodyFindDeliveryItemDetailsForComponentBarcode().getResponseDataFindDeliveryItemDetailsForComponentBarcode()));
            }else if(response.errorBody()!=null){
                _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyFindDeliveryItemDetailsForComponentBarcode().getResponseDataFindDeliveryItemDetailsForComponentBarcode().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodesForComponentAndLocationBarcode(response.body().getResponseBodyFindDeliveryItemDetailsForComponentBarcode().getResponseDataFindDeliveryItemDetailsForComponentBarcode().getDitsErrors(),
                        resourcesProvider.getString(R.string.item_barcode_invalid));
                _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG ,AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseFindDeliveryItemDetailsForComponentBarcode.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG ,AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE+ex);
        }
    }

    /**
     * Api call for findLocationDetailsForBarcode
     * @param envelope RequestEnvelopeFindLocationDetailsForBarcode
     */
    public void findLocationDetailsForBarcode(RequestEnvelopeFindLocationDetailsForBarcode envelope){
        _responseFindLocationDetailsForBarcode.postValue(Resource.loading(null));
        Call<ResponseEnvelopeFindLocationDetailsForBarcode> responseEnvelopeCall
                = apiService.findLocationDetailsForBarcode(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeFindLocationDetailsForBarcode>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeFindLocationDetailsForBarcode> call, Response<ResponseEnvelopeFindLocationDetailsForBarcode> response) {
                handleFindLocationDetailsForBarcodeResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeFindLocationDetailsForBarcode> call, Throwable t) {
                _responseFindLocationDetailsForBarcode.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find location details for barcode response and did some validation on response
     * @param response ResponseEnvelopeFindLocationDetailsForBarcode to manipulate the error data
     */
    private void handleFindLocationDetailsForBarcodeResponse(Response<ResponseEnvelopeFindLocationDetailsForBarcode> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode().getDitsErrors()==null) {
                _responseFindLocationDetailsForBarcode.postValue(Resource.success(response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode()));
            }else if(response.errorBody()!=null){
                _responseFindLocationDetailsForBarcode.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodesForComponentAndLocationBarcode(response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode().getDitsErrors(),
                        resourcesProvider.getString(R.string.location_barcode_not_recognised));
                _responseFindLocationDetailsForBarcode.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseFindLocationDetailsForBarcode.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG ,AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseFindLocationDetailsForBarcode.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG ,AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE+ex);
        }
    }

    /**
     * Api call for recordLocationOfItem
     * @param envelope RequestEnvelopeFindLocationDetailsForBarcode
     */
    public void recordLocationOfItem(RequestEnvelopeRecordLocationOfItem envelope){
        _responseDataRecordLocationOfItem.postValue(Resource.loading(null));
        Call<ResponseEnvelopeRecordLocationOfItem> responseEnvelopeCall
                = apiService.recordLocationOfItem(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeRecordLocationOfItem>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeRecordLocationOfItem> call, Response<ResponseEnvelopeRecordLocationOfItem> response) {
                handleRecordLocationOfItemResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeRecordLocationOfItem> call, Throwable t) {
                _responseDataRecordLocationOfItem.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled Record Location Of Item  response and did some validation on response
     * @param response ResponseEnvelopeRecordLocationOfItem to manipulate the error data
     */
    private void handleRecordLocationOfItemResponse(Response<ResponseEnvelopeRecordLocationOfItem> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyRecordLocationOfItem().getResponseDataRecordLocationOfItem().getDitsErrors()==null) {
                _responseDataRecordLocationOfItem.postValue(Resource.success(response.body().getResponseBodyRecordLocationOfItem().getResponseDataRecordLocationOfItem()));
            }else if(response.errorBody()!=null){
                _responseDataRecordLocationOfItem.postValue(Resource.error(AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyRecordLocationOfItem().getResponseDataRecordLocationOfItem().getDitsErrors()!=null){
                String errorNumber = response.body().getResponseBodyRecordLocationOfItem().getResponseDataRecordLocationOfItem().getDitsErrors().getDitsError().getErrorType().getErrorNumber();
                if(errorNumber.equals(AppConstants.FOUR_THOUSAND)){
                    _responseDataRecordLocationOfItem.postValue(Resource.error(resourcesProvider.getString(R.string.update_has_failed), errorNumber, null));
                }else if(errorNumber.equals(AppConstants.FIVE_THOUSAND)){
                    _responseDataRecordLocationOfItem.postValue(Resource.error(resourcesProvider.getString(R.string.create_has_failed), errorNumber, null));
                }else{
                    _responseDataRecordLocationOfItem.postValue(Resource.error(response.body().getResponseBodyRecordLocationOfItem()
                                    .getResponseDataRecordLocationOfItem().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                            errorNumber, null));
                }
            } else{
                _responseDataRecordLocationOfItem.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG ,AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseDataRecordLocationOfItem.postValue(Resource.error("Something Went Wrong", null));
            Log.e(TAG ,AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE+ex);
        }
    }


    /**
     * Api call for findLocationDetailsForBarcode
     * @param envelope RequestEnvelopeFindLocationDetailsForBarcode
     */
    public void findHanoverDetails(RequestEnvelopeFindHandoverDetails envelope){
        _responseFindHandoverDetails.postValue(Resource.loading(null));
        Call<ResponseEnvelopeFindHandoverDetails> responseEnvelopeCall
                = apiService.findHandoverDetails(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeFindHandoverDetails>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeFindHandoverDetails> call, Response<ResponseEnvelopeFindHandoverDetails> response) {
                handleFindHanoverDetailsResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeFindHandoverDetails> call, Throwable t) {
                _responseFindHandoverDetails.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find location details for barcode response and did some validation on response
     * @param response ResponseEnvelopeFindLocationDetailsForBarcode to manipulate the error data
     */
    private void handleFindHanoverDetailsResponse(Response<ResponseEnvelopeFindHandoverDetails> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyFindHandoverDetails().getResponseDataFindHandoverDetails().getDitsErrors()==null) {
                _responseFindHandoverDetails.postValue(Resource.success(response.body().getResponseBodyFindHandoverDetails().getResponseDataFindHandoverDetails()));
            }else if(response.errorBody()!=null){
                _responseFindHandoverDetails.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyFindHandoverDetails().getResponseDataFindHandoverDetails().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodesForCCDAndCHD(response.body().getResponseBodyFindHandoverDetails().getResponseDataFindHandoverDetails().getDitsErrors());
                _responseFindHandoverDetails.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            }else{
                _responseFindHandoverDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG ,AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseFindHandoverDetails.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG ,AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE+ex);
        }
    }


    /**
     * Api call for findLocationDetailsForBarcode
     * @param envelope RequestEnvelopeFindLocationDetailsForBarcode
     */
    public void findDeliveriesAndDeliveryItems(RequestEnvelopeFindDeliveriesAndDeliveryItems envelope){
        _responseFindDeliveriesAndDeliveryItems.postValue(Resource.loading(null));
        Call<ResponseEnvelopeFindDeliveriesAndDeliveryItems> responseEnvelopeCall
                = apiService.findDeliveriesAndDeliveryItems(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeFindDeliveriesAndDeliveryItems>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeFindDeliveriesAndDeliveryItems> call, Response<ResponseEnvelopeFindDeliveriesAndDeliveryItems> response) {
                handleFindDeliveriesAndDeliveryItemsResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeFindDeliveriesAndDeliveryItems> call, Throwable t) {
                _responseFindDeliveriesAndDeliveryItems.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find location details for barcode response and did some validation on response
     * @param response ResponseEnvelopeFindLocationDetailsForBarcode to manipulate the error data
     */
    private void handleFindDeliveriesAndDeliveryItemsResponse(Response<ResponseEnvelopeFindDeliveriesAndDeliveryItems> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyFindDeliveriesAndDeliveryItems().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors()==null) {
                _responseFindDeliveriesAndDeliveryItems.postValue(Resource.success(response.body().getResponseBodyFindDeliveriesAndDeliveryItems().getResponseDataFindDeliveriesAndDeliveryItems()));
            } else if(response.body().getResponseBodyFindDeliveriesAndDeliveryItems().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodesForCCDAndCHD(response.body().getResponseBodyFindDeliveriesAndDeliveryItems().getResponseDataFindDeliveriesAndDeliveryItems().getDitsErrors());
                _responseFindDeliveriesAndDeliveryItems.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseFindDeliveriesAndDeliveryItems.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG ,AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseFindDeliveriesAndDeliveryItems.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG , AppConstants.ERROR_SOMETHING_WENT_WRONG +ex);
        }
    }

    private Pair handleCommonAPIResponseErrorCodesForCCDAndCHD(DITSErrors ditsErrors) {
        Pair pairOfErrorMessageAndCode = new Pair<> (ditsErrors.getDitsError().getErrorType().getErrorMessage(), ditsErrors.getDitsError().getErrorType().getErrorNumber());
        String errorNumber = ditsErrors.getDitsError().getErrorType().getErrorNumber();
        if(errorNumber.equals(AppConstants.TWO_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.delivery_reference_not_recognised), AppConstants.TWO_THOUSAND);
        } else if(errorNumber.equals(AppConstants.TWO_THOUSAND_AND_ONE)){
            pairOfErrorMessageAndCode =  new Pair("", AppConstants.TWO_THOUSAND_AND_ONE);
        }else if(errorNumber.equals(AppConstants.SIX_HUNDRED)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.delivery_has_been_cancelled), AppConstants.SIX_HUNDRED);
        }else if(errorNumber.equals(AppConstants.THREE_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.multiple_handover_details_found), AppConstants.THREE_THOUSAND);
        }else if(errorNumber.equals(AppConstants.TEN_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.delivery_number_invalid), AppConstants.TEN_THOUSAND);
        }
        return pairOfErrorMessageAndCode;
    }

    private Pair handleCommonAPIResponseErrorCodesForComponentAndLocationBarcode(DITSErrors ditsErrors, String errorMessageToDisplay) {
        Pair pairOfErrorMessageAndCode = new Pair<> (ditsErrors.getDitsError().getErrorType().getErrorMessage(), ditsErrors.getDitsError().getErrorType().getErrorNumber());
        String errorNumber = ditsErrors.getDitsError().getErrorType().getErrorNumber();
        if(errorNumber.equals(AppConstants.TWO_THOUSAND) || errorNumber.equals(AppConstants.TEN_THOUSAND)){
            pairOfErrorMessageAndCode =  new Pair(errorMessageToDisplay, errorNumber);

        }
        return pairOfErrorMessageAndCode;
    }


    // UpdateLotsNumerRequireRepository
      public void updateLostNumerRequire(RequestEnvelopeAmendLotNumerUpdate envelope )
      {
          _responseDataAmendLotNumerUpdate.postValue(Resource.loading(null));
          Call<ResponseEnvelopeAmendLotNumerUpdate> responseEnvelopeCall
                  = apiService.updateLostNumerRequire(envelope);
          responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeAmendLotNumerUpdate>() {
              @Override
              public void onResponse(Call<ResponseEnvelopeAmendLotNumerUpdate> call, Response<ResponseEnvelopeAmendLotNumerUpdate> response) {
                  handleupdateLostNumerRequireResponse(response);
              }
              @Override
              public void onFailure(Call<ResponseEnvelopeAmendLotNumerUpdate> call, Throwable t) {
                  _responseDataAmendLotNumerUpdate.postValue(Resource.error(t.getMessage(), null));
              }
          });
      }

      private void handleupdateLostNumerRequireResponse(Response<ResponseEnvelopeAmendLotNumerUpdate> response)
      {
          try {
              if(response.isSuccessful() && response.body()!=null &&
                      response.body().getResponseBodyAmendLotNumerUpdate().
                              getResponseDataAmendLotNumerUpdate().getDitsErrors()==null) {
                  _responseDataAmendLotNumerUpdate.postValue(Resource.success(response.body().
                          getResponseBodyAmendLotNumerUpdate().getResponseDataAmendLotNumerUpdate()));
              }else if(response.errorBody()!=null){
                  _responseDataAmendLotNumerUpdate.postValue(Resource.error("Error while getting the response", null));
              }else if(response.body().getResponseBodyAmendLotNumerUpdate().getResponseDataAmendLotNumerUpdate().getDitsErrors()!=null){
                  _responseDataAmendLotNumerUpdate.postValue(Resource.error( response.body().getResponseBodyAmendLotNumerUpdate().
                                  getResponseDataAmendLotNumerUpdate().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                          response.body().getResponseBodyAmendLotNumerUpdate().
                                  getResponseDataAmendLotNumerUpdate()));
              } else{
                  _responseDataAmendLotNumerUpdate.postValue(Resource.error("Something Went Wrong", null));
                  Log.i(TAG ,"Response is neither success nor error");
              }
          }catch (Exception ex){
              _responseDataAmendLotNumerUpdate.postValue(Resource.error("Something Went Wrong", null));
              Log.e(TAG ,"Error while handling the response : "+ex);
          }

      }

      /*Reprint Label*/


    public void findDeliveryGoodProducts(RequestEnvelopeFindDeliveryGoodProduct envelope){
        _responseDataFindDeliveryGoodProduct.postValue(Resource.loading(null));
        Call<ResponseEnvelopeFindDeliveryGoodProduct> responseEnvelopeCall
                = apiService.FindDeliveryGoodProduct(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeFindDeliveryGoodProduct>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeFindDeliveryGoodProduct> call, Response<ResponseEnvelopeFindDeliveryGoodProduct> response) {
                handleFindDeliveryGoodProducts(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeFindDeliveryGoodProduct> call, Throwable t) {
                _responseDataFindDeliveryGoodProduct.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find Delivery Item Details For Component Barcode response and did some validation on response
     * @param response ResponseEnvelopeFindDeliveryDetailsForComponentBarcode to manipulate the error data
     */
    private void handleFindDeliveryGoodProducts(Response<ResponseEnvelopeFindDeliveryGoodProduct> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyFindDeliveryGoodProduct().
                            getResponseDataFindDeliveryGoodProduct().getDitsErrors()==null) {
                _responseDataFindDeliveryGoodProduct.postValue(Resource.success(response.body().
                        getResponseBodyFindDeliveryGoodProduct().getResponseDataFindDeliveryGoodProduct()));
            }else if(response.errorBody()!=null){
                _responseDataFindDeliveryGoodProduct.postValue(Resource.error("Error while getting the response", null));
            }else if(response.body().getResponseBodyFindDeliveryGoodProduct().getResponseDataFindDeliveryGoodProduct().getDitsErrors()!=null){
                _responseDataFindDeliveryGoodProduct.postValue(Resource.error( response.body().getResponseBodyFindDeliveryGoodProduct().
                                getResponseDataFindDeliveryGoodProduct().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBodyFindDeliveryGoodProduct().
                                getResponseDataFindDeliveryGoodProduct()));
            } else{
                _responseDataFindDeliveryGoodProduct.postValue(Resource.error("Something Went Wrong", null));
                Log.i(TAG ,"Response is neither success nor error");
            }
        }catch (Exception ex){
            _responseDataFindDeliveryGoodProduct.postValue(Resource.error("Something Went Wrong", null));
            Log.e(TAG ,"Error while handling the response : "+ex);
        }
    }

}
