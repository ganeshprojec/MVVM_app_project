package com.jlp.mvvm_jlp_project.model.request.printer_list;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindPrinterRequest", strict = false)
public class RequestDataPrinterList
{
    @Inject
    RequestDataPrinterList(){}

    @Element(name = "cds:deliveryCentreNumber",required = false)
    public String deliveryCentreNumber;



    public String getDeliveryCentreNumber() {
        return deliveryCentreNumber;
    }

    public void setDeliveryCentreNumber(String deliveryCentreNumber) {
        this.deliveryCentreNumber = deliveryCentreNumber;
    }


}
