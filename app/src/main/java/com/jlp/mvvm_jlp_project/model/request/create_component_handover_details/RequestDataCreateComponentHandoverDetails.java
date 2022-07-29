package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "CreateComponentHandoverDetailsRequest", strict = false)
public class RequestDataCreateComponentHandoverDetails {
    @Inject
    RequestDataCreateComponentHandoverDetails(){}

    @Element(name = "deliveryGoodsDetails", required = false)
    DeliveryGoodsDetails deliveryGoodsDetails;

    public DeliveryGoodsDetails getDeliveryGoodsDetails() {
        return deliveryGoodsDetails;
    }

    public void setDeliveryGoodsDetails(DeliveryGoodsDetails deliveryGoodsDetails) {
        this.deliveryGoodsDetails = deliveryGoodsDetails;
    }
}
