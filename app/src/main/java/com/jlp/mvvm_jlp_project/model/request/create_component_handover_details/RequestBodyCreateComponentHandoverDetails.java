package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyCreateComponentHandoverDetails {

    @Inject RequestBodyCreateComponentHandoverDetails(){}

    @Element(name = "cds:CreateComponentHandoverDetailsRequest", required = false)
    private RequestDataCreateComponentHandoverDetails requestDataCreateComponentHandoverDetails;

    public RequestDataCreateComponentHandoverDetails getRequestDataCreateComponentHandoverDetails() {
        return requestDataCreateComponentHandoverDetails;
    }

    public void setRequestDataCreateComponentHandoverDetails(RequestDataCreateComponentHandoverDetails requestDataCreateComponentHandoverDetails) {
        this.requestDataCreateComponentHandoverDetails = requestDataCreateComponentHandoverDetails;
    }
}
