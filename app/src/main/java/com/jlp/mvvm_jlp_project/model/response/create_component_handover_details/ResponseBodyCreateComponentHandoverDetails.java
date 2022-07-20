package com.jlp.mvvm_jlp_project.model.response.create_component_handover_details;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "Body", strict = false)
public class ResponseBodyCreateComponentHandoverDetails {

    @Element(name = "CreateComponentHandoverDetailsResponse",required = false)
    private ResponseDataCreateComponentHandoverDetails responseDataFindDeliveriesAndDeliveryItems;

    public ResponseDataCreateComponentHandoverDetails getResponseDataFindDeliveriesAndDeliveryItems() {
        return responseDataFindDeliveriesAndDeliveryItems;
    }

    public void setResponseDataFindDeliveriesAndDeliveryItems(ResponseDataCreateComponentHandoverDetails responseDataFindDeliveriesAndDeliveryItems) {
        this.responseDataFindDeliveriesAndDeliveryItems = responseDataFindDeliveriesAndDeliveryItems;
    }
}
