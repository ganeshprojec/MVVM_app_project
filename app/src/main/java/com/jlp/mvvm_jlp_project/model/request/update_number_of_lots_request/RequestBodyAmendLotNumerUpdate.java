package com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyAmendLotNumerUpdate {


    @Inject
    RequestBodyAmendLotNumerUpdate(){}



    @Element(name = "cds:UpdateNumberOfLotsRequest",required = false)
    private RequestDataAmendLotNumerUpdate requestDataAmendLotNumerUpdate;

    public RequestDataAmendLotNumerUpdate getRequestDataAmendLotNumerUpdate() {
        return requestDataAmendLotNumerUpdate;
    }

    public void setRequestDataAmendLotNumerUpdate(RequestDataAmendLotNumerUpdate requestDataAmendLotNumerUpdate) {
        this.requestDataAmendLotNumerUpdate = requestDataAmendLotNumerUpdate;
    }

}
