package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;/*
 * Created by Sandeep(Techno Learning) on 21,July,2022
 */

import org.simpleframework.xml.Element;

public class DeliveryGoodsDetails {

    @Element(name = "deliveryGoodId",required = false)
    public String deliveryGoodId;
    @Element(name = "deliveryPickupIndicator",required = false)
    public String deliveryPickupIndicator;
    @Element(name = "productCode",required = false)
    public int productCode;
    @Element(name = "orderDescriptionClean",required = false)
    public String orderDescriptionClean;
    @Element(name = "PartActiveQuantity",required = false)
    public int PartActiveQuantity;
    @Element(name = "componentDetails",required = false)
    public ComponentDetails componentDetails;

    public String getDeliveryGoodId() {
        return deliveryGoodId;
    }

    public void setDeliveryGoodId(String deliveryGoodId) {
        this.deliveryGoodId = deliveryGoodId;
    }

    public String getDeliveryPickupIndicator() {
        return deliveryPickupIndicator;
    }

    public void setDeliveryPickupIndicator(String deliveryPickupIndicator) {
        this.deliveryPickupIndicator = deliveryPickupIndicator;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getOrderDescriptionClean() {
        return orderDescriptionClean;
    }

    public void setOrderDescriptionClean(String orderDescriptionClean) {
        this.orderDescriptionClean = orderDescriptionClean;
    }

    public int getPartActiveQuantity() {
        return PartActiveQuantity;
    }

    public void setPartActiveQuantity(int partActiveQuantity) {
        PartActiveQuantity = partActiveQuantity;
    }

    public ComponentDetails getComponentDetails() {
        return componentDetails;
    }

    public void setComponentDetails(ComponentDetails componentDetails) {
        this.componentDetails = componentDetails;
    }
}
