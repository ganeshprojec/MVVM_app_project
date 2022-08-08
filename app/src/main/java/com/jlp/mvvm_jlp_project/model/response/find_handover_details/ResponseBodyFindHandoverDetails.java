package com.jlp.mvvm_jlp_project.model.response.find_handover_details;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "Body", strict = false)
public class ResponseBodyFindHandoverDetails {

    @Element(name = "FindHandoverDetailsResponse",required = false)
    private ResponseDataFindHandoverDetails responseDataFindHandoverDetails;

    public ResponseDataFindHandoverDetails getResponseDataFindHandoverDetails() {
        return responseDataFindHandoverDetails;
    }

    public void setResponseDataFindHandoverDetails(ResponseDataFindHandoverDetails responseDataFindHandoverDetails) {
        this.responseDataFindHandoverDetails = responseDataFindHandoverDetails;
    }
}
