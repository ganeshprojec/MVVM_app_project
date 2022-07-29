package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "DeliveryGoodsDetails")
public class DeliveryGoodsDetails {
    @Element(name = "deliveryGoodId",required = false)
    public String deliveryGoodId;
    @Element(name = "productCode",required = false)
    public int productCode;
    @Element(name = "productDescription",required = false)
    public String productDescription;
    @Element(name = "componentDetails",required = false)
    public ComponentDetails componentDetails;

    public String getDeliveryGoodId() {
        return deliveryGoodId;
    }

    public void setDeliveryGoodId(String deliveryGoodId) {
        this.deliveryGoodId = deliveryGoodId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ComponentDetails getComponentDetails() {
        return componentDetails;
    }

    public void setComponentDetails(ComponentDetails componentDetails) {
        this.componentDetails = componentDetails;
    }
}
