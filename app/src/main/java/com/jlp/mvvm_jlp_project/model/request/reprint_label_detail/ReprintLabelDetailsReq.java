package com.jlp.mvvm_jlp_project.model.request.reprint_label_detail;

import org.simpleframework.xml.Element;

import javax.inject.Inject;

public class ReprintLabelDetailsReq {


    @Inject
    ReprintLabelDetailsReq(){}

    @Element(name = "cds:printerId",required = false)
    public String printerId;
    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }

    /*1 or more repetitions*/

    @Element(name = "cds:deliveryGoodId",required = false)
    public String deliveryGoodId;

    @Element(name = "cds:deliveryId",required = false)
    public String deliveryId;

    public String getDeliveryGoodId() {
        return deliveryGoodId;
    }

    public void setDeliveryGoodId(String deliveryGoodId) {
        this.deliveryGoodId = deliveryGoodId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
}
