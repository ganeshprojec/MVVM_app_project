package com.jlp.mvvm_jlp_project.model.response.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;

@Root(name = "CreatedComponentHandoverDetails")
public class CreatedComponentHandoverDetails {
    @Element(name = "deliveryId",required = false)
    public int deliveryId;
    @Element(name = "agreedDelDate",required = false)
    public Date agreedDelDate;
    @Element(name = "latestDelTime",required = false)
    public Date latestDelTime;
    @Element(name = "handoverTo",required = false)
    public String handoverTo;
    @Element(name = "handoverDate",required = false)
    public Date handoverDate;
    @Element(name = "handoverRef",required = false)
    public String handoverRef;
    @Element(name = "crtUserId",required = false)
    public String crtUserId;
    @Element(name = "deliveryId",required = false)
    public Date crtStamp;
    @Element(name = "updUserId",required = false)
    public String updUserId;
    @Element(name = "updStamp",required = false)
    public Date updStamp;
    @ElementList(name = "DeliveryGoodsDetails",required = false)
    public List<DeliveryGoodsDetails> DeliveryGoodsDetails;

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getAgreedDelDate() {
        return agreedDelDate;
    }

    public void setAgreedDelDate(Date agreedDelDate) {
        this.agreedDelDate = agreedDelDate;
    }

    public Date getLatestDelTime() {
        return latestDelTime;
    }

    public void setLatestDelTime(Date latestDelTime) {
        this.latestDelTime = latestDelTime;
    }

    public String getHandoverTo() {
        return handoverTo;
    }

    public void setHandoverTo(String handoverTo) {
        this.handoverTo = handoverTo;
    }

    public Date getHandoverDate() {
        return handoverDate;
    }

    public void setHandoverDate(Date handoverDate) {
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

    public Date getCrtStamp() {
        return crtStamp;
    }

    public void setCrtStamp(Date crtStamp) {
        this.crtStamp = crtStamp;
    }

    public String getUpdUserId() {
        return updUserId;
    }

    public void setUpdUserId(String updUserId) {
        this.updUserId = updUserId;
    }

    public Date getUpdStamp() {
        return updStamp;
    }

    public void setUpdStamp(Date updStamp) {
        this.updStamp = updStamp;
    }

    public List<com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.DeliveryGoodsDetails> getDeliveryGoodsDetails() {
        return DeliveryGoodsDetails;
    }

    public void setDeliveryGoodsDetails(List<com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.DeliveryGoodsDetails> deliveryGoodsDetails) {
        DeliveryGoodsDetails = deliveryGoodsDetails;
    }
}
