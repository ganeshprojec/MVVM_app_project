package com.jlp.mvvm_jlp_project.model.request.route_item_update_status;

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
public class RequestEnvelopeUpdateItemStatus {

    @Inject
    public RequestEnvelopeUpdateItemStatus() {
    }

    @Element(name = "soapenv:Body", required = false)
    private RequestBodyUpdateItemStatus requestBody;

    public RequestBodyUpdateItemStatus getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBodyUpdateItemStatus requestBody) {
        this.requestBody = requestBody;
    }
}
