package com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product;


import com.jlp.mvvm_jlp_project.model.DeliveryGoodProductDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindDeliveryGoodProductsResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataFindDeliveryGoodProduct {

    @Inject
    ResponseDataFindDeliveryGoodProduct(){}

    @Element(name = "DeliveryGoodProductDetails", required = false)
    public DeliveryGoodProductDetails deliveryGoodProductDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;


    public DeliveryGoodProductDetails getDeliveryGoodProductDetails() {
        return deliveryGoodProductDetails;
    }

    public void setDeliveryGoodProductDetails(DeliveryGoodProductDetails deliveryGoodProductDetails) {
        this.deliveryGoodProductDetails = deliveryGoodProductDetails;
    }


    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }


}
