package com.jlp.mvvm_jlp_project.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.PrinterListModel;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestEnvelopeFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestEnveloperPrinterList;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseDataFindDeliveryItemDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.LocationDetails;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;
import com.jlp.mvvm_jlp_project.repository.CommonBarcodeScannerRepository;
import com.jlp.mvvm_jlp_project.repository.CommonPrinterListRepository;
import com.jlp.mvvm_jlp_project.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CommonPrinterViewModel extends  BaseViewModel
{

     CommonPrinterListRepository commonPrinterListRepository;

    public MutableLiveData<Resource<ResponseDataPrinterList>> responseDataPrinterList = new MutableLiveData<>();

    @Inject
    public CommonPrinterViewModel(CommonPrinterListRepository repository) {
        this.commonPrinterListRepository = repository;

        this.responseDataPrinterList = repository._responseDataPrinterList;

    }

    public void getPrinterList(RequestEnveloperPrinterList envelope){
        commonPrinterListRepository.findPrinterListData(envelope);
    }


}
