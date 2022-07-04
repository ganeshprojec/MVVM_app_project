package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemMovementRepository {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private final ApiService apiService;
    public MutableLiveData<Resource<ResponseDataFindLocationDetailsForBarcode>> _responseFindLocationDetailsForBarcode = new MutableLiveData<>();

    @Inject public ItemMovementRepository(ApiService apiService) {
        this.apiService = apiService;
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
                _responseFindLocationDetailsForBarcode.postValue(Resource.error("Error while getting the response", null));
            }else if(response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode().getDitsErrors()!=null){
                _responseFindLocationDetailsForBarcode.postValue(Resource.error( response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getFindLocationDetailsForBarcode().getResponseDataFindLocationDetailsForBarcode()));
            } else{
                _responseFindLocationDetailsForBarcode.postValue(Resource.error("Something Went Wrong", null));
                Log.i(TAG ,"Response is neither success nor error");
            }
        }catch (Exception ex){
            _responseFindLocationDetailsForBarcode.postValue(Resource.error("Something Went Wrong", null));
            Log.e(TAG ,"Error while handling the response : "+ex);
        }
    }
}
