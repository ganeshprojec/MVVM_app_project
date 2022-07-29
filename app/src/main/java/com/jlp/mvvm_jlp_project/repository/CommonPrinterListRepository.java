package com.jlp.mvvm_jlp_project.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestEnveloperPrinterList;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseDataFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseEnveloperPrinterList;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonPrinterListRepository
{
    private static final String TAG = CommonPrinterListRepository.class.getSimpleName();
    private final ApiService apiService;

    public MutableLiveData<Resource<ResponseDataPrinterList>> _responseDataPrinterList = new MutableLiveData<>();

    @Inject
    public CommonPrinterListRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void findPrinterListData(RequestEnveloperPrinterList envelope){
        _responseDataPrinterList.postValue(Resource.loading(null));
        Call<ResponseEnveloperPrinterList> responseEnvelopeCall
                = apiService.findPrinterListData(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnveloperPrinterList>() {
            @Override
            public void onResponse(Call<ResponseEnveloperPrinterList> call, Response<ResponseEnveloperPrinterList> response) {
                hindandlefPrinterListData(response);
            }
            @Override
            public void onFailure(Call<ResponseEnveloperPrinterList> call, Throwable t) {
                _responseDataPrinterList.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    private void hindandlefPrinterListData(Response<ResponseEnveloperPrinterList> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyPrinterList().
                            getResponseDataPrinterList().getDitsErrors()==null) {
                _responseDataPrinterList.postValue(Resource.success(response.body().
                        getResponseBodyPrinterList().getResponseDataPrinterList()));
            }else if(response.errorBody()!=null){
                _responseDataPrinterList.postValue(Resource.error("Error while getting the response", null));
            }else if(response.body().getResponseBodyPrinterList().getResponseDataPrinterList().getDitsErrors()!=null){
                _responseDataPrinterList.postValue(Resource.error( response.body().getResponseBodyPrinterList().
                                getResponseDataPrinterList().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBodyPrinterList().
                                getResponseDataPrinterList()));
            } else{
                _responseDataPrinterList.postValue(Resource.error("Something Went Wrong", null));
                Log.i(TAG ,"Response is neither success nor error");
            }
        }catch (Exception ex){
            _responseDataPrinterList.postValue(Resource.error("Something Went Wrong", null));
            Log.e(TAG ,"Error while handling the response : "+ex);
        }
    }
}





