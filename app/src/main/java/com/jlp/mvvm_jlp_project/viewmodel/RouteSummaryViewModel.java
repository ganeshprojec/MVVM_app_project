package com.jlp.mvvm_jlp_project.viewmodel;

import android.content.Context;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.DeliveryDetails;
import com.jlp.mvvm_jlp_project.model.LotsInfo;
import com.jlp.mvvm_jlp_project.utils.Helper;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ActivityContext;

@HiltViewModel
public class RouteSummaryViewModel extends BaseViewModel {

    @Inject
    public RouteSummaryViewModel() {

    }


    public ArrayList<DeliveryDetails> getDummyList(@ActivityContext Context context) {
        ArrayList<DeliveryDetails> list = new ArrayList<>();

        ArrayList<LotsInfo> lots = new ArrayList<>();
        for (int j = 0; j < 15; j++) {
            lots.add(new LotsInfo(Helper.getXmlString(context, R.string.dummy_lotSr),
                    Helper.getXmlString(context, R.string.dummy_location_b5)));
        }

        for (int i = 0; i < 10; i++) {
            list.add(new DeliveryDetails(Helper.getXmlString(context, R.string.dummy_delivery_number),
                    Helper.getXmlString(context, R.string.dummy_customer_name), Helper.getXmlString(context, R.string.dummy_item_num),
                    Helper.getXmlString(context, R.string.dummy_product_desc),
                    lots));
        }

        return list;

    }


}
