package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "ItemStatusDetails", strict = false)
public class ItemStatusDetails {

    @Inject
    public ItemStatusDetails() {
    }

    @Element(name = "componentId", required = false)
    String componentId = new String();

    @Element(name = "componentNum", required = false)
    String componentNum = new String();

    @Element(name = "componentStatus", required = false)
    String componentStatus = new String();

    @Element(name = "deliveryId", required = false)
    String deliveryId = new String();

    @Element(name = "productCode", required = false)
    String productCode = new String();

    @Element(name = "userId", required = false)
    String userId = new String();

    @Element(name = "userIdAuthorised", required = false)
    String userIdAuthorized = new String();

    @Element(name = "userName", required = false)
    String userName = new String();

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentNum() {
        return componentNum;
    }

    public void setComponentNum(String componentNum) {
        this.componentNum = componentNum;
    }

    public String getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        this.componentStatus = componentStatus;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdAuthorized() {
        return userIdAuthorized;
    }

    public void setUserIdAuthorized(String userIdAuthorized) {
        this.userIdAuthorized = userIdAuthorized;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "ItemStatusDetails{" +
                "componentId='" + componentId + '\'' +
                ", componentNum='" + componentNum + '\'' +
                ", componentStatus='" + componentStatus + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", userId='" + userId + '\'' +
                ", userIdAuthorized='" + userIdAuthorized + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
