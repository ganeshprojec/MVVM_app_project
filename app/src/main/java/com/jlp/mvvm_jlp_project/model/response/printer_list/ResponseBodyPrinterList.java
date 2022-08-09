package com.jlp.mvvm_jlp_project.model.response.printer_list;

import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.ResponseDataFindDeliveryItemDetailsForComponentBarcode;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
public class ResponseBodyPrinterList {


    public ResponseDataPrinterList getResponseDataPrinterList() {
        return responseDataPrinterList;
    }

    public void setResponseDataPrinterList(ResponseDataPrinterList responseDataPrinterList) {
        this.responseDataPrinterList = responseDataPrinterList;
    }

    @Element(name = "FindPrinterResponse",required = false)
    private ResponseDataPrinterList responseDataPrinterList;
}
