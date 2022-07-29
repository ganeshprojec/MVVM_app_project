package com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "CreateOrUpdateHandoverDetailsRequest", strict = false)
public class RequestDataCreateOrUpdateHandoverDetails {
    @Inject
    RequestDataCreateOrUpdateHandoverDetails(){}

    @Element(name = "cds:CreateOrUpdateHandoverDetails",required = false)
    public CreateOrUpdateHandoverDetails createOrUpdateHandoverDetails;

    public CreateOrUpdateHandoverDetails getCreateOrUpdateHandoverDetails() {
        return createOrUpdateHandoverDetails;
    }

    public void setCreateOrUpdateHandoverDetails(CreateOrUpdateHandoverDetails createOrUpdateHandoverDetails) {
        this.createOrUpdateHandoverDetails = createOrUpdateHandoverDetails;
    }
}
