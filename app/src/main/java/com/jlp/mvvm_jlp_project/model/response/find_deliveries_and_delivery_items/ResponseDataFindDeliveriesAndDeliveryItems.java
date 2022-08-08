package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;


import android.os.Parcel;

import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "FindDeliveriesAndDeliveryItemsResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataFindDeliveriesAndDeliveryItems {
    @Element(name = "DeliveryDetails", required = false)
    public DeliveryDetails deliveryDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataFindDeliveriesAndDeliveryItems(){}

    protected ResponseDataFindDeliveriesAndDeliveryItems(Parcel in) {
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
