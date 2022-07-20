package com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details;

import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv" ,reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "cds" ,reference = Constants.NAMESPACE)
})
public class RequestEnvelopeCreateOrUpdateHandoverDetails {

    @Inject
    RequestEnvelopeCreateOrUpdateHandoverDetails(){}

    @Element(name = "soapenv:Body", required = false)
    private RequestBodyCreateOrUpdateHandoverDetails requestBodyCreateOrUpdateHandoverDetails;

    public RequestBodyCreateOrUpdateHandoverDetails getRequestBodyCreateOrUpdateHandoverDetails() {
        return requestBodyCreateOrUpdateHandoverDetails;
    }

    public void setRequestBodyCreateOrUpdateHandoverDetails(RequestBodyCreateOrUpdateHandoverDetails requestBodyCreateOrUpdateHandoverDetails) {
        this.requestBodyCreateOrUpdateHandoverDetails = requestBodyCreateOrUpdateHandoverDetails;
    }
}
