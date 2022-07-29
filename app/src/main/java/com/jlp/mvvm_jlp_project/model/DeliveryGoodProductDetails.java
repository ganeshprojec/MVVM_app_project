package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DeliveryGoodProductDetails
{
    @Inject
    public DeliveryGoodProductDetails() {}


    @ElementList(name = "cds:DeliveryGoodProduct", entry = "DeliveryGoodProduct", required = false, inline = true)
    public List<DeliveryGoodProduct>  deliveryGoodProductDetails= new ArrayList<>();

    public List<DeliveryGoodProduct> getDeliveryGoodProductDetails() {
        return deliveryGoodProductDetails;
    }

    public void setDeliveryGoodProductDetails(List<DeliveryGoodProduct> deliveryGoodProductDetails) {
        this.deliveryGoodProductDetails = deliveryGoodProductDetails;
    }

}
