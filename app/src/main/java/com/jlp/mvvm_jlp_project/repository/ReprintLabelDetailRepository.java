package com.jlp.mvvm_jlp_project.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product.RequestEnvelopeFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestEnvelopeReprintLabel;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseDataFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseEnvelopeFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;
import com.jlp.mvvm_jlp_project.model.response.reprint_label_detail.ResponseDataReprintLabel;
import com.jlp.mvvm_jlp_project.model.response.reprint_label_detail.ResponseEnvelopeReprintLabel;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReprintLabelDetailRepository
{
    private static final String TAG = ReprintLabelDetailRepository.class.getSimpleName();
    private final ApiService apiService;

    public MutableLiveData<Resource<ResponseDataReprintLabel>> _responseDataReprintLabel = new MutableLiveData<>();

    @Inject
    public ReprintLabelDetailRepository(ApiService apiService) {
        this.apiService = apiService;
    }


    public void findReprintLabelData(RequestEnvelopeReprintLabel envelope){
        _responseDataReprintLabel.postValue(Resource.loading(null));
        Call<ResponseEnvelopeReprintLabel> responseEnvelopeCall
                = apiService.findReprintLabels(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeReprintLabel>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeReprintLabel> call, Response<ResponseEnvelopeReprintLabel> response) {
                handleFindReprintLabels(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeReprintLabel> call, Throwable t) {
                _responseDataReprintLabel.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled find Delivery Item Details For Component Barcode response and did some validation on response
     * @param response ResponseEnvelopeFindDeliveryDetailsForComponentBarcode to manipulate the error data
     */
    private void handleFindReprintLabels(Response<ResponseEnvelopeReprintLabel> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyPrinterList().
                            getResponseDataReprintLabel().getDitsErrors()==null) {
                _responseDataReprintLabel.postValue(Resource.success(response.body().
                        getResponseBodyPrinterList().getResponseDataReprintLabel()));
            }else if(response.errorBody()!=null){
                _responseDataReprintLabel.postValue(Resource.error("Error while getting the response", null));
            }else if(response.body().getResponseBodyPrinterList().getResponseDataReprintLabel().getDitsErrors()!=null){
                _responseDataReprintLabel.postValue(Resource.error( response.body().getResponseBodyPrinterList().
                                getResponseDataReprintLabel().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                        response.body().getResponseBodyPrinterList().
                                getResponseDataReprintLabel()));
            } else{
                _responseDataReprintLabel.postValue(Resource.error("Something Went Wrong", null));
                Log.i(TAG ,"Response is neither success nor error");
            }
        }catch (Exception ex){
            _responseDataReprintLabel.postValue(Resource.error("Something Went Wrong", null));
            Log.e(TAG ,"Error while handling the response : "+ex);
        }
    }
}
