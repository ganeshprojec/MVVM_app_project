package com.jlp.mvvm_jlp_project.model.response.create_component_handover_details;/*
 * Created by Sandeep(Techno Learning) on 28,July,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

public class DeliveryGoodsDetails {
    @Element(name = "DeliveryGoodsDetail", required = false)
    public DeliveryGoodsDetail deliveryGoodsDetail;

    public DeliveryGoodsDetail getDeliveryGoodsDetail() {
        return deliveryGoodsDetail;
    }

    public void setDeliveryGoodsDetail(DeliveryGoodsDetail deliveryGoodsDetail) {
        this.deliveryGoodsDetail = deliveryGoodsDetail;
    }
}
