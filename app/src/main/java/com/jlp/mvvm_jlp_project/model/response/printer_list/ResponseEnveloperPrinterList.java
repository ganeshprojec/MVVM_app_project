package com.jlp.mvvm_jlp_project.model.response.printer_list;


import com.jlp.mvvm_jlp_project.model.response.Header;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseBodyFindDeliveryItemDetailsForComponentBarcode;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
@NamespaceList({
        @Namespace(prefix = "soapenv", reference = "http://schemas.xmlsoap.org/soap/envelope/"),
        @Namespace( prefix = "soapenc", reference = "http://schemas.xmlsoap.org/soap/encoding/"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
})
public class ResponseEnveloperPrinterList {

    @Element(name = "Body", required = false)
    private ResponseBodyPrinterList responseBodyPrinterList;

    @Element(name = "Header", required = true)
    private Header header = new Header();

    public ResponseBodyPrinterList getResponseBodyPrinterList() {
        return responseBodyPrinterList;
    }

    public void setResponseBodyPrinterList(ResponseBodyPrinterList responseBodyPrinterList) {
        this.responseBodyPrinterList = responseBodyPrinterList;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }



}
