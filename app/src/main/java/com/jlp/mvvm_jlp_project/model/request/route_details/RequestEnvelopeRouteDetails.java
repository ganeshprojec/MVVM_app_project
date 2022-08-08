package com.jlp.mvvm_jlp_project.model.request.route_details;

import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import javax.inject.Inject;


@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace(prefix = "cds", reference = Constants.NAMESPACE)
})
public class RequestEnvelopeRouteDetails {

    @Inject
    public RequestEnvelopeRouteDetails() {
    }

    @Element(name = "soapenv:Body", required = false)
    private RequestBodyRouteDetails requestBody;

    public RequestBodyRouteDetails getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBodyRouteDetails requestBody) {
        this.requestBody = requestBody;
    }
}
