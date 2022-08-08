package com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details;/*
 * Created by Sandeep(Techno Learning) on 25,July,2022
 */

import org.simpleframework.xml.Element;

import javax.inject.Inject;

public class CreateOrUpdateHandoverDetails {
    @Element(name = "cds:deliveryId",required = false)
    public String deliveryId;
    @Element(name = "cds:agreedDelDate",required = false)
    public String agreedDelDate;
    @Element(name = "cds:latestDelTime",required = false)
    public String latestDelTime;
    @Element(name = "cds:handoverTo",required = false)
    public String handoverTo;
    @Element(name = "cds:handoverDate",required = false)
    public String handoverDate;
    @Element(name = "cds:handoverRef",required = false)
    public String handoverRef;
    @Element(name = "cds:userId",required = false)
    public String userId;
    @Element(name = "cds:userName",required = false)
    public String userName;

    @Inject CreateOrUpdateHandoverDetails(){}

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
}
