package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;/*
 * Created by Sandeep(Techno Learning) on 04,July,2022
 */

import android.os.Parcel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import javax.inject.Inject;

@Root(name = "HandoverDetails", strict = false)
public class DeliveryDetails {

    @Element(name = "deliveryId",required = false)
    public String deliveryId;
    @Element(name = "deliveryDate",required = false)
    public String deliveryDate;
    @Element(name = "RecipientName",required = false)
    public String RecipientName;
    @Element(name = "totalLotNumber",required = false)
    public String totalLotNumber;
    @Element(name = "serviceIncluded",required = false)
    public String serviceIncluded;
    @Element(name = "customerDeliveryAddress",required = false)
    public CustomerDeliveryAddress customerDeliveryAddress;
    @Element(name = "DeliveryAddress",required = false)
    public DeliveryAddress deliveryAddress;
    @ElementList(name = "DeliveryGoodsDetails", inline=true)
    public List<DeliveryGoodsDetails> deliveryGoodsDetails;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String recipientName) {
        RecipientName = recipientName;
    }

    public String getTotalLotNumber() {
        return totalLotNumber;
    }

    public void setTotalLotNumber(String totalLotNumber) {
        this.totalLotNumber = totalLotNumber;
    }

    public String getServiceIncluded() {
        return serviceIncluded;
    }

    public void setServiceIncluded(String serviceIncluded) {
        this.serviceIncluded = serviceIncluded;
    }

    public CustomerDeliveryAddress getCustomerDeliveryAddress() {
        return customerDeliveryAddress;
    }

    public void setCustomerDeliveryAddress(CustomerDeliveryAddress customerDeliveryAddress) {
        this.customerDeliveryAddress = customerDeliveryAddress;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<DeliveryGoodsDetails> getDeliveryGoodsDetails() {
        return deliveryGoodsDetails;
    }

    public void setDeliveryGoodsDetails(List<DeliveryGoodsDetails> deliveryGoodsDetails) {
        this.deliveryGoodsDetails = deliveryGoodsDetails;
    }
}
