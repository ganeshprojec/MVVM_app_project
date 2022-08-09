package com.jlp.mvvm_jlp_project.model.request.reprint_label_detail;

import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestBodyPrinterList;
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
public class RequestEnvelopeReprintLabel {

    @Inject
    RequestEnvelopeReprintLabel(){}

    @Element(name = "soapenv:Body", required = false)
    private RequestBodyReprintLabel requestBodyReprintLabel;

    public RequestBodyReprintLabel getRequestBodyReprintLabel() {
        return requestBodyReprintLabel;
    }

    public void setRequestBodyReprintLabel(RequestBodyReprintLabel requestBodyReprintLabel) {
        this.requestBodyReprintLabel = requestBodyReprintLabel;
    }


}
