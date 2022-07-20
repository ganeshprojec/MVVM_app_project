package com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyCreateOrUpdateHandoverDetails {

    @Inject
    RequestBodyCreateOrUpdateHandoverDetails(){}

    @Element(name = "cds:CreateOrUpdateHandoverDetailsRequest",required = false)
    private RequestDataCreateOrUpdateHandoverDetails requestDataCreateOrUpdateHandoverDetails;

    public RequestDataCreateOrUpdateHandoverDetails getRequestDataCreateOrUpdateHandoverDetails() {
        return requestDataCreateOrUpdateHandoverDetails;
    }

    public void setRequestDataCreateOrUpdateHandoverDetails(RequestDataCreateOrUpdateHandoverDetails requestDataCreateOrUpdateHandoverDetails) {
        this.requestDataCreateOrUpdateHandoverDetails = requestDataCreateOrUpdateHandoverDetails;
    }
}
