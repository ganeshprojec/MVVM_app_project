package com.jlp.mvvm_jlp_project.model.request.route_item_update_status;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;


@Root(name = "soapenv:Body", strict = false)
public class RequestBodyUpdateItemStatus {

    @Inject
    public RequestBodyUpdateItemStatus() {
    }

    @Element(name = "cds:UpdateItemStatusRequest", required = false)
    private RequestDataUpdateItemStatus requestData;

    public RequestDataUpdateItemStatus getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestDataUpdateItemStatus requestData) {
        this.requestData = requestData;
    }
}
