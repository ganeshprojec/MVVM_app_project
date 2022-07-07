package com.jlp.mvvm_jlp_project.viewmodel;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.repository.ItemMovementRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class CarrierHandoverDetailsViewModel extends BaseViewModel {
    ItemMovementRepository repository;


    @Inject
    public CarrierHandoverDetailsViewModel(ItemMovementRepository repository) {
        this.repository = repository;
    }
}