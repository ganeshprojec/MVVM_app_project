package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

@Root(name = "CreateComponentHandoverDetails", strict = false)
public class CreateComponentHandoverDetails {

    @Inject
    CreateComponentHandoverDetails(){}

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
    @Element(name = "userId",required = false)
    public String userId;
    @Element(name = "userName",required = false)
    public String userName;
    @ElementList(name = "DeliveryGoodsDetails",  inline = true)
    public List<DeliveryGoodsDetails> deliveryGoodsDetails = new ArrayList<>();

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
