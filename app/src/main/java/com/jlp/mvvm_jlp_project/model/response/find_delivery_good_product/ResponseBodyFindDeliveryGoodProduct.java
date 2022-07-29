package com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product;

import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
public class ResponseBodyFindDeliveryGoodProduct {

    @Element(name = "FindDeliveryGoodProductsResponse",required = false)
    private ResponseDataFindDeliveryGoodProduct responseDataFindDeliveryGoodProduct;

    public ResponseDataFindDeliveryGoodProduct getResponseDataFindDeliveryGoodProduct() {
        return responseDataFindDeliveryGoodProduct;
    }

    public void setResponseDataFindDeliveryGoodProduct(ResponseDataFindDeliveryGoodProduct responseDataFindDeliveryGoodProduct) {
        this.responseDataFindDeliveryGoodProduct = responseDataFindDeliveryGoodProduct;
    }



}
