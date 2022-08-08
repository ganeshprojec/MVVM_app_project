package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

import javax.inject.Inject;

@Root(name = "DeliveryGoodsDetails", strict = false)
public class DeliveryGoodsDetails {

    @Element(name = "deliveryGoodId", required = false)
    String deliveryGoodId = new String();

    @Element(name = "deliveryPickupIndicator", required = false)
    String deliveryPickupIndicator = new String();

    @Element(name = "productCode", required = false)
    String productCode = new String();

    @Element(name = "orderDescriptionClean", required = false)
    String orderDescriptionClean = new String();

    @Element(name = "PartActiveQuantity", required = false)
    String partActiveQuantity = new String();

    public ArrayList<DeliveryLotDetails> getLotInfo() {
        return lotInfo;
    }

    public void setLotInfo(ArrayList<DeliveryLotDetails> lotInfo) {
        this.lotInfo = lotInfo;
    }

    @ElementList(name = "cds:DeliveryGoodsDetails", entry = "componentDetails", required = false, inline = true)
    //@Element(name = "componentDetails", required = false)
    ArrayList<DeliveryLotDetails> lotInfo = new ArrayList<DeliveryLotDetails>();


    @Inject public DeliveryGoodsDetails() {}


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

    public String getPartActiveQuantity() {
        return partActiveQuantity;
    }

    public void setPartActiveQuantity(String partActiveQuantity) {
        this.partActiveQuantity = partActiveQuantity;
    }

    @Override
    public String toString() {
        return "DeliveryGoodsDetails{" +
                "deliveryGoodId='" + deliveryGoodId + '\'' +
                ", deliveryPickupIndicator='" + deliveryPickupIndicator + '\'' +
                ", productCode='" + productCode + '\'' +
                ", orderDescriptionClean='" + orderDescriptionClean + '\'' +
                ", partActiveQuantity='" + partActiveQuantity + '\'' +
                ", lotInfo=" + lotInfo +
                '}';
    }
}
