package com.jlp.mvvm_jlp_project.viewmodel;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.repository.ItemEnquiryRepository;
import com.jlp.mvvm_jlp_project.repository.ItemMovementRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class ItemMovementViewModel extends BaseViewModel {
    ItemMovementRepository repository;

    public MutableLiveData<Resource<ResponseDataFindLocationDetailsForBarcode>> responseFindLocationDetailsForBarcode
            = new MutableLiveData<>();


    @Inject
    public ItemMovementViewModel(ItemMovementRepository repository) {
        this.repository = repository;
        this.responseFindLocationDetailsForBarcode = repository._responseFindLocationDetailsForBarcode;
    }

    public void findLocationDetailsForBarcode(RequestEnvelopeFindLocationDetailsForBarcode envelope){
        repository.findLocationDetailsForBarcode(envelope);
    }
}