package com.jlp.mvvm_jlp_project.model.request.route_details;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyRouteDetails {

    @Inject
    public RequestBodyRouteDetails() {
    }

    @Element(name = "cds:FindDeliveriesAndDeliveryItemsRequest", required = false)
    private RequestDataRouteDetails requestData;

    public RequestDataRouteDetails getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestDataRouteDetails requestData) {
        this.requestData = requestData;
    }
}
