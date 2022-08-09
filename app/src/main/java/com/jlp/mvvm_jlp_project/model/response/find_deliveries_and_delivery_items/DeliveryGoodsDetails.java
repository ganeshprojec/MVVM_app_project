package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;/*
 * Created by Sandeep(Techno Learning) on 21,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class DeliveryGoodsDetails {

    @Element(name = "deliveryGoodId",required = false)
    public String deliveryGoodId;
    @Element(name = "deliveryPickupIndicator",required = false)
    public String deliveryPickupIndicator;
    @Element(name = "productCode",required = false)
    public String productCode;
    @Element(name = "orderDescriptionClean",required = false)
    public String orderDescriptionClean;
    @Element(name = "PartActiveQuantity",required = false)
    public int PartActiveQuantity;
    @ElementList(name = "componentDetails", required = false, inline=true)
    public List<ComponentDetails> componentDetails = new ArrayList<>();

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
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

    public List<ComponentDetails> getComponentDetails() {
        return componentDetails;
    }

    public void setComponentDetails(List<ComponentDetails> componentDetails) {
        this.componentDetails = componentDetails;
    }
}
