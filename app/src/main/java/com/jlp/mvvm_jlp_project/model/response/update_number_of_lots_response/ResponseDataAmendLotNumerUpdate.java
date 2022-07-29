package com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response;


import com.jlp.mvvm_jlp_project.model.request.update_number_of_lots_request.LotDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "UpdateNumberOfLotsResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataAmendLotNumerUpdate {

    @Inject
    ResponseDataAmendLotNumerUpdate(){}

    @Element(name = "LotDetails",required = false)
    private LotDetails lotDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    public LotDetails getLotDetails() {
        return lotDetails;
    }

    public void setLotDetails(LotDetails lotDetails) {
        this.lotDetails = lotDetails;
    }



    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }




}
