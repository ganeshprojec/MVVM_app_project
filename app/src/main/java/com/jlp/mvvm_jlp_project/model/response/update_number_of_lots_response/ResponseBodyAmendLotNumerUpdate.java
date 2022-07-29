package com.jlp.mvvm_jlp_project.model.response.update_number_of_lots_response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
public class ResponseBodyAmendLotNumerUpdate {

    @Element(name = "UpdateNumberOfLotsResponse",required = false)
    private ResponseDataAmendLotNumerUpdate responseDataAmendLotNumerUpdate;

    public ResponseDataAmendLotNumerUpdate getResponseDataAmendLotNumerUpdate() {
        return responseDataAmendLotNumerUpdate;
    }

    public void setResponseDataAmendLotNumerUpdate(ResponseDataAmendLotNumerUpdate responseDataAmendLotNumerUpdate) {
        this.responseDataAmendLotNumerUpdate = responseDataAmendLotNumerUpdate;
    }


}
