package com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "LotDetails", strict = false)
public class LotDetails
{
    @Inject
    public LotDetails() {}

    @Element(name = "componentId",required = false)
    public String componentId;

    @Element(name = "goodId",required = false)
    public String goodId;

    @Element(name = "productCode",required = false)
    public String productCode;

    @Element(name = "requestedLot",required = false)
    public String requestedLot;

    @Element(name = "updatedCurrentLot",required = false)
    public String updatedCurrentLot;

    @Element(name = "userId",required = false)
    public String userId;

    @Element(name = "userName",required = false)
    public String userName;

    @Element(name = "deliveryId",required = false)
    public String deliveryId;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getRequestedLot() {
        return requestedLot;
    }

    public void setRequestedLot(String requestedLot) {
        this.requestedLot = requestedLot;
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

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getUpdatedCurrentLot() {
        return updatedCurrentLot;
    }

    public void setUpdatedCurrentLot(String updatedCurrentLot) {
        this.updatedCurrentLot = updatedCurrentLot;
    }

}
