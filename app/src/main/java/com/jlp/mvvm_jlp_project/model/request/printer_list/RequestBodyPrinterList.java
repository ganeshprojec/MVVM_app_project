package com.jlp.mvvm_jlp_project.model.request.printer_list;

import com.jlp.mvvm_jlp_project.model.request.find_delivery_item_details_for_component_barcode.RequestDataFindDeliveryItemDetailsForComponentBarcode;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyPrinterList {


    @Inject
    RequestBodyPrinterList(){}

    @Element(name = "cds:FindPrinterRequest",required = false)
    private RequestDataPrinterList requestDataPrinterList;


    public RequestDataPrinterList getRequestDataPrinterList() {
        return requestDataPrinterList;
    }

    public void setRequestDataPrinterList(RequestDataPrinterList requestDataPrinterList) {
        this.requestDataPrinterList = requestDataPrinterList;
    }


}
