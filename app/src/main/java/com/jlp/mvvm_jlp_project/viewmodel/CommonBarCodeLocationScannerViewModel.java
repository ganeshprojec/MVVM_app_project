package com.jlp.mvvm_jlp_project.viewmodel;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.repository.CommonBarCodeLocationScannerRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class CommonBarCodeLocationScannerViewModel extends BaseViewModel {
    CommonBarCodeLocationScannerRepository repository;

    public MutableLiveData<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>> responseFindDeliveryDetailsForComponentBarcode
            = new MutableLiveData<>();

    public MutableLiveData<Resource<ResponseDataFindLocationDetailsForBarcode>> responseFindLocationDetailsForBarcode
            = new MutableLiveData<>();

    @Inject
    public CommonBarCodeLocationScannerViewModel(CommonBarCodeLocationScannerRepository repository) {
        this.repository = repository;
        this.responseFindDeliveryDetailsForComponentBarcode = repository._responseFindDeliveryDetailsForComponentBarcode;
        this.responseFindLocationDetailsForBarcode = repository._responseFindLocationDetailsForBarcode;
    }

    public void findDeliveryDetailsForComponentBarcode(RequestEnvelopeFindDeliveryDetailsForComponentBarcode envelope){
        repository.findDeliveryDetailsForComponentBarcode(envelope);
    }

    public void findLocationDetailsForBarcode(RequestEnvelopeFindLocationDetailsForBarcode envelope){
        repository.findLocationDetailsForBarcode(envelope);
    }
}