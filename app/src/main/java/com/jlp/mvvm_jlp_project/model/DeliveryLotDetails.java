package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "componentDetails", strict = false)
public class DeliveryLotDetails {

    @Element(name = "componentNum", required = false)
    String number = new String();

    @Element(name = "componentId", required = false)
    String lotId = new String();

    @Element(name = "componentBarcode", required = false)
    String lotBarcode = new String();

    @Element(name = "componentStatus", required = false)
    String status = new String();

    @Element(name = "componentStatusText", required = false)
    String statusText = new String();

    @Inject
    public DeliveryLotDetails() {
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLotBarcode() {
        return lotBarcode;
    }

    public void setLotBarcode(String lotBarcode) {
        this.lotBarcode = lotBarcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


    @Override
    public String toString() {
        return "DeliveryLotDetails{" +
                "number='" + number + '\'' +
                ", lotId='" + lotId + '\'' +
                ", lotBarcode='" + lotBarcode + '\'' +
                ", status='" + status + '\'' +
                ", statusText='" + statusText + '\'' +
                '}';
    }
}
