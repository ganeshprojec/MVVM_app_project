package com.jlp.mvvm_jlp_project.model.request.find_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyFindHandoverDetails {

    @Inject
    RequestBodyFindHandoverDetails(){}

    @Element(name = "cds:FindHandoverDetailsRequest",required = false)
    private RequestDataFindHandoverDetails requestDataFindHandoverDetails;

    public RequestDataFindHandoverDetails getRequestDataFindHandoverDetails() {
        return requestDataFindHandoverDetails;
    }

    public void setRequestDataFindHandoverDetails(RequestDataFindHandoverDetails requestDataFindHandoverDetails) {
        this.requestDataFindHandoverDetails = requestDataFindHandoverDetails;
    }
}
