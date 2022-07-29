package com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product;


import com.jlp.mvvm_jlp_project.model.response.Header;
import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseBodyPrinterList;

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
public class ResponseEnvelopeFindDeliveryGoodProduct {

    @Element(name = "Body", required = false)
    private ResponseBodyFindDeliveryGoodProduct responseBodyFindDeliveryGoodProduct;

    @Element(name = "Header", required = true)
    private Header header = new Header();

    public ResponseBodyFindDeliveryGoodProduct getResponseBodyFindDeliveryGoodProduct() {
        return responseBodyFindDeliveryGoodProduct;
    }

    public void setResponseBodyFindDeliveryGoodProduct(ResponseBodyFindDeliveryGoodProduct responseBodyFindDeliveryGoodProduct) {
        this.responseBodyFindDeliveryGoodProduct = responseBodyFindDeliveryGoodProduct;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }



}
