package com.jlp.mvvm_jlp_project.model.request.route_details;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;


@Root(name = "FindDeliveriesAndDeliveryItemsRequest", strict = false)
public class RequestDataRouteDetails {
    @Inject
    public RequestDataRouteDetails() {
    }

    @Element(name = "deliveryId", required = false)
    private String deliveryId;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String routeId) {
        this.deliveryId = routeId;
    }
}
