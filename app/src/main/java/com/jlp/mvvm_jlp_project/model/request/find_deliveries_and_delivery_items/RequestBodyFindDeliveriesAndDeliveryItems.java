package com.jlp.mvvm_jlp_project.model.request.find_deliveries_and_delivery_items;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyFindDeliveriesAndDeliveryItems {

    @Inject
    RequestBodyFindDeliveriesAndDeliveryItems(){}

    @Element(name = "cds:FindDeliveriesAndDeliveryItemsRequest",required = false)
    private RequestDataFindDeliveriesAndDeliveryItems requestDataFindDeliveriesAndDeliveryItems;

    public RequestDataFindDeliveriesAndDeliveryItems getRequestDataFindDeliveriesAndDeliveryItems() {
        return requestDataFindDeliveriesAndDeliveryItems;
    }

    public void setRequestDataFindDeliveriesAndDeliveryItems(RequestDataFindDeliveriesAndDeliveryItems requestDataFindDeliveriesAndDeliveryItems) {
        this.requestDataFindDeliveriesAndDeliveryItems = requestDataFindDeliveriesAndDeliveryItems;
    }
}
