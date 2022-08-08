package com.jlp.mvvm_jlp_project.model.response.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CreatedComponentHandoverDetails")
public class CreatedComponentHandoverDetails {
    @Element(name = "deliveryId",required = false)
    public int deliveryId;
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
    @Element(name = "crtUserId",required = false)
    public String crtUserId;
    @Element(name = "crtStamp",required = false)
    public String crtStamp;
    @Element(name = "updUserId",required = false)
    public String updUserId;
    @Element(name = "updStamp",required = false)
    public String updStamp;
    @Element(name = "DeliveryGoodsDetails", required = false)
    public DeliveryGoodsDetails deliveryGoodsDetails;

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
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

    public String getUpdStamp() {
        return updStamp;
    }

    public void setUpdStamp(String updStamp) {
        this.updStamp = updStamp;
    }

    public String getUpdUserId() {
        return updUserId;
    }

    public void setUpdUserId(String updUserId) {
        this.updUserId = updUserId;
    }

    public DeliveryGoodsDetails getDeliveryGoodsDetails() {
        return deliveryGoodsDetails;
    }

    public void setDeliveryGoodsDetails(DeliveryGoodsDetails deliveryGoodsDetails) {
        this.deliveryGoodsDetails = deliveryGoodsDetails;
    }
}
