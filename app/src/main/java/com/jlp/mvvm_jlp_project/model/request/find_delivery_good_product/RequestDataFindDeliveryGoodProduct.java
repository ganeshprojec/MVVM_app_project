package com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindDeliveryGoodProductsRequest", strict = false)
public class RequestDataFindDeliveryGoodProduct
{

    @Inject
    RequestDataFindDeliveryGoodProduct(){}

    @Element(name = "cds:deliveryId",required = false)
    public String deliveryId;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }


}
