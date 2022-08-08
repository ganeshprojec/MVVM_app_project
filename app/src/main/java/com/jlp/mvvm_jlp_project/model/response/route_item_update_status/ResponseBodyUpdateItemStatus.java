package com.jlp.mvvm_jlp_project.model.response.route_item_update_status;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
public class ResponseBodyUpdateItemStatus {

    @Element(name = "UpdateItemStatusResponse", required = false)
    private ResponseDataUpdateItemStatus responseData;

    public ResponseDataUpdateItemStatus getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataUpdateItemStatus responseData) {
        this.responseData = responseData;
    }
}



