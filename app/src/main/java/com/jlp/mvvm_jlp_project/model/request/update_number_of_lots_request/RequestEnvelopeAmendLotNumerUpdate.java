package com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request;


import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv" ,reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "cds" ,reference = Constants.NAMESPACE)
})
public class RequestEnvelopeAmendLotNumerUpdate {

    @Inject
    RequestEnvelopeAmendLotNumerUpdate(){}


    @Element(name = "soapenv:Body", required = false)
    private RequestBodyAmendLotNumerUpdate requestBodyAmendLotNumerUpdate;


    public RequestBodyAmendLotNumerUpdate getRequestBodyAmendLotNumerUpdate() {
        return requestBodyAmendLotNumerUpdate;
    }

    public void setRequestBodyAmendLotNumerUpdate(RequestBodyAmendLotNumerUpdate requestBodyAmendLotNumerUpdate) {
        this.requestBodyAmendLotNumerUpdate = requestBodyAmendLotNumerUpdate;
    }

}
