package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root
public class DeliveryGoodsDetails {
    @Element(name = "deliveryGoodId",required = false)
    public String deliveryGoodId;
    @Element(name = "productCode",required = false)
    public String productCode;
    @Element(name = "productDescription",required = false)
    public String productDescription;
    @ElementList(name = "componentDetails", inline = true)
    public List<ComponentDetails> componentDetails = new ArrayList<>();

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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public List<ComponentDetails> getComponentDetails() {
        return componentDetails;
    }

    public void setComponentDetails(List<ComponentDetails> componentDetails) {
        this.componentDetails = componentDetails;
    }
}
