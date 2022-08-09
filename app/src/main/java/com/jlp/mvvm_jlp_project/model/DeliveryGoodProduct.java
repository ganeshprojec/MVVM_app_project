package com.jlp.mvvm_jlp_project.model;

import com.jlp.mvvm_jlp_project.model.response.DITSErrors;

import org.simpleframework.xml.Element;

import javax.inject.Inject;

public class DeliveryGoodProduct
{
    @Inject
    public DeliveryGoodProduct() {}

    @Element(name = "deliveryId",required = false)
    public String deliveryId;

    @Element(name = "deliveryGoodId",required = false)
    public String deliveryGoodId;

    @Element(name = "productCode",required = false)
    public String productCode;

    @Element(name = "orderDescriptionClean",required = false)
    public String orderDescriptionClean;



    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryGoodId() {
        return deliveryGoodId;
    }

    public void setDeliveryGoodId(String deliveryGoodId) {
        this.deliveryGoodId = deliveryGoodId;
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



    public boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



}
