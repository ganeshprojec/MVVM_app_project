package com.jlp.mvvm_jlp_project.viewmodel;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.repository.ItemEnquiryRepository;
import com.jlp.mvvm_jlp_project.repository.UserRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class ItemEnquiryViewModel extends BaseViewModel {
    ItemEnquiryRepository repository;

    public MutableLiveData<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>> responseFindDeliveryDetailsForComponentBarcode
            = new MutableLiveData<>();

    @Inject
    public ItemEnquiryViewModel(ItemEnquiryRepository repository) {
        this.repository = repository;
        this.responseFindDeliveryDetailsForComponentBarcode = repository._responseFindDeliveryDetailsForComponentBarcode;
    }

    public void findDeliveryDetailsForComponentBarcode(RequestEnvelopeFindDeliveryDetailsForComponentBarcode envelope){
        repository.findDeliveryDetailsForComponentBarcode(envelope);
    }
}