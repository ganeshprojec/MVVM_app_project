package com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "Body", strict = false)
public class ResponseBodyCreateOrUpdateHandoverDetails {

    @Element(name = "CreateOrUpdateHandoverDetailsResponse",required = false)
    private ResponseDataCreateOrUpdateHandoverDetails responseDataCreateOrUpdateHandoverDetails;

    public ResponseDataCreateOrUpdateHandoverDetails getResponseDataCreateOrUpdateHandoverDetails() {
        return responseDataCreateOrUpdateHandoverDetails;
    }

    public void setResponseDataCreateOrUpdateHandoverDetails(ResponseDataCreateOrUpdateHandoverDetails responseDataCreateOrUpdateHandoverDetails) {
        this.responseDataCreateOrUpdateHandoverDetails = responseDataCreateOrUpdateHandoverDetails;
    }
}
