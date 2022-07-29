package com.jlp.mvvm_jlp_project.model.request.printer_list;

import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestBodyFindDeliveryItemDetailsForComponentBarcode;
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
public class RequestEnveloperPrinterList {

    @Inject
    RequestEnveloperPrinterList(){}


    @Element(name = "soapenv:Body", required = false)
    private RequestBodyPrinterList requestBodyPrinterList;

    public RequestBodyPrinterList getRequestBodyPrinterList() {
        return requestBodyPrinterList;
    }

    public void setRequestBodyPrinterList(RequestBodyPrinterList requestBodyPrinterList) {
        this.requestBodyPrinterList = requestBodyPrinterList;
    }
}
