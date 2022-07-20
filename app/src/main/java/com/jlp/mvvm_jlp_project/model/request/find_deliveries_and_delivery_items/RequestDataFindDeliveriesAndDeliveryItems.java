package com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindDeliveriesAndDeliveryItemsRequest", strict = false)
public class RequestDataFindDeliveriesAndDeliveryItems {
    @Inject
    RequestDataFindDeliveriesAndDeliveryItems(){}

    @Element(name = "cds:barcode",required = false)
    public String barcode;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
