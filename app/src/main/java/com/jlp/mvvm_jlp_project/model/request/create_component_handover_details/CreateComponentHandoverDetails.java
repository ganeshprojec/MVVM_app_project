package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.Date;
import java.util.List;

public class CreateComponentHandoverDetails {
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
    @Element(name = "userId",required = false)
    public String userId;
    @Element(name = "userName",required = false)
    public String userName;
    @ElementList(name = "DeliveryGoodsDetails",required = false, inline = true)
    public List<DeliveryGoodsDetails> deliveryGoodsDetails;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<DeliveryGoodsDetails> getDeliveryGoodsDetails() {
        return deliveryGoodsDetails;
    }

    public void setDeliveryGoodsDetails(List<DeliveryGoodsDetails> deliveryGoodsDetails) {
        this.deliveryGoodsDetails = deliveryGoodsDetails;
    }
}
