package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "Body", strict = false)
public class ResponseBodyFindDeliveriesAndDeliveryItems {

    @Element(name = "FindDeliveriesAndDeliveryItems",required = false)
    private ResponseDataFindDeliveriesAndDeliveryItems responseDataFindDeliveriesAndDeliveryItems;

    public ResponseDataFindDeliveriesAndDeliveryItems getResponseDataFindDeliveriesAndDeliveryItems() {
        return responseDataFindDeliveriesAndDeliveryItems;
    }

    public void setResponseDataFindDeliveriesAndDeliveryItems(ResponseDataFindDeliveriesAndDeliveryItems responseDataFindDeliveriesAndDeliveryItems) {
        this.responseDataFindDeliveriesAndDeliveryItems = responseDataFindDeliveriesAndDeliveryItems;
    }
}
