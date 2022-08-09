package com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product;


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
public class RequestEnvelopeFindDeliveryGoodProduct {

    @Inject
    RequestEnvelopeFindDeliveryGoodProduct(){}


    @Element(name = "soapenv:Body", required = false)
    private RequestBodyFindDeliveryGoodProduct requestBodyFindDeliveryGoodProduct;

    public RequestBodyFindDeliveryGoodProduct getRequestBodyFindDeliveryGoodProduct() {
        return requestBodyFindDeliveryGoodProduct;
    }

    public void setRequestBodyFindDeliveryGoodProduct(RequestBodyFindDeliveryGoodProduct requestBodyFindDeliveryGoodProduct) {
        this.requestBodyFindDeliveryGoodProduct = requestBodyFindDeliveryGoodProduct;
    }


}
