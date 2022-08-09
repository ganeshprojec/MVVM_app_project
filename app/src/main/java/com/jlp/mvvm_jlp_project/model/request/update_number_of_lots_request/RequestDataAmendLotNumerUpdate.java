package com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request;


import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import javax.inject.Inject;

@Root(name = "UpdateNumberOfLotsRequest", strict = false)
public class RequestDataAmendLotNumerUpdate {

    @Inject
    RequestDataAmendLotNumerUpdate(){}

    @Element(name = "cds:LotDetails",required = false)
    public LotDetails lotDetails;

    public LotDetails getLotDetails() {
        return lotDetails;
    }

    public void setLotDetails(LotDetails lotDetails) {
        this.lotDetails = lotDetails;
    }



}
