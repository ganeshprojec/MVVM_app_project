package com.jlp.mvvm_jlp_project.model.response.find_handover_details;/*
 * Created by Sandeep(Techno Learning) on 04,July,2022
 */

import android.os.Parcel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FoundHandoverDetails", strict = false)
public class FoundHandoverDetails {

    @Inject public FoundHandoverDetails() {}

    // TODO Need to verify the dateTime Objects
    @Element(name = "deliveryId",required = false)
    public String deliveryId;
    @Element(name = "agreedDelDate",required = false)
    public String agreedDelDate;
    @Element(name = "latestDelTime",required = false)
    public String latestDelTime;
    @Element(name = "handoverTo",required = false)
    public String handoverTo;
    @Element(name = "handoverDate",required = false)
    public String handoverDate;
    @Element(name = "handoverRef",required = false)
    public String handoverRef;
    @Element(name = "serviceIncluded",required = false)
    public Boolean serviceIncluded;
    @Element(name = "crtUserId",required = false)
    public String crtUserId;
    @Element(name = "crtStamp",required = false)
    public String crtStamp;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getAgreedDelDate() {
        return agreedDelDate;
    }

    public void setAgreedDelDate(String agreedDelDate) {
        this.agreedDelDate = agreedDelDate;
    }

    public String getLatestDelTime() {
        return latestDelTime;
    }

    public void setLatestDelTime(String latestDelTime) {
        this.latestDelTime = latestDelTime;
    }

    public String getHandoverTo() {
        return handoverTo;
    }

    public void setHandoverTo(String handoverTo) {
        this.handoverTo = handoverTo;
    }

    public String getHandoverDate() {
        return handoverDate;
    }

    public void setHandoverDate(String handoverDate) {
        this.handoverDate = handoverDate;
    }

    public String getHandoverRef() {
        return handoverRef;
    }

    public void setHandoverRef(String handoverRef) {
        this.handoverRef = handoverRef;
    }

    public Boolean getServiceIncluded() {
        return serviceIncluded;
    }

    public void setServiceIncluded(Boolean serviceIncluded) {
        this.serviceIncluded = serviceIncluded;
    }

    public String getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(String crtUserId) {
        this.crtUserId = crtUserId;
    }

    public String getCrtStamp() {
        return crtStamp;
    }

    public void setCrtStamp(String crtStamp) {
        this.crtStamp = crtStamp;
    }
}
