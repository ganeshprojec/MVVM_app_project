package com.jlp.mvvm_jlp_project.model.response.route_details;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "Body", strict = false)
public class ResponseBodyRouteDetails {

    @Element(name = "FindDeliveriesAndDeliveryItemsResponse", required = false)
    private ResponseDataRouteDetails responseData;

    public ResponseDataRouteDetails getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataRouteDetails responseData) {
        this.responseData = responseData;
    }
}
