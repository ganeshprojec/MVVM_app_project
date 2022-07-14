package com.jlp.mvvm_jlp_project.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class CarrierHandoverDetailsViewModel extends BaseViewModel {

    public MutableLiveData<List<TitleValueDataModel>> itemDelivery = new MutableLiveData<>();

    @Inject public CarrierHandoverDetailsViewModel() {}

    public void getItemDetailsDeliveryBarcodeData(){
        List<TitleValueDataModel> itemEnquiryModels = new ArrayList<>();
        itemEnquiryModels.add(new TitleValueDataModel(R.string.delivery_number,
                "1223454"
        ));
        itemEnquiryModels.add(new TitleValueDataModel(R.string.customer_name,
                "red435354"
        ));
        itemEnquiryModels.add(new TitleValueDataModel(R.string.no_of_delivery_items,
                "data new values"));
        itemEnquiryModels.add(new TitleValueDataModel(R.string.total_number_of_parts_lots,
                "12"));
        itemDelivery.setValue(itemEnquiryModels);
    }
}