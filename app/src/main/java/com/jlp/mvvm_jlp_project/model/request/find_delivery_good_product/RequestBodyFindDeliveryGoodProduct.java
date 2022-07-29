package com.jlp.mvvm_jlp_project.model.request.find_delivery_good_product;

import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestDataPrinterList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyFindDeliveryGoodProduct {

    @Inject
    RequestBodyFindDeliveryGoodProduct(){}


    @Element(name = "cds:FindDeliveryGoodProductsRequest",required = false)
    private RequestDataFindDeliveryGoodProduct requestDataFindDeliveryGoodProduct;

    public RequestDataFindDeliveryGoodProduct getRequestDataFindDeliveryGoodProduct() {
        return requestDataFindDeliveryGoodProduct;
    }

    public void setRequestDataFindDeliveryGoodProduct(RequestDataFindDeliveryGoodProduct requestDataFindDeliveryGoodProduct) {
        this.requestDataFindDeliveryGoodProduct = requestDataFindDeliveryGoodProduct;
    }



}
