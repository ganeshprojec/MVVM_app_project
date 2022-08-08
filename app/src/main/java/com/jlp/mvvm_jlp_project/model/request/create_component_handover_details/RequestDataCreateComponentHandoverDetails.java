package com.jlp.mvvm_jlp_project.model.request.create_component_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Root(name = "CreateComponentHandoverDetailsRequest", strict = false)
public class RequestDataCreateComponentHandoverDetails {
    @Inject RequestDataCreateComponentHandoverDetails(){}

    @Element(name = "CreateComponentHandoverDetails",required = false)
    public CreateComponentHandoverDetails createComponentHandoverDetails;

    public CreateComponentHandoverDetails getCreateComponentHandoverDetails() {
        return createComponentHandoverDetails;
    }

    public void setCreateComponentHandoverDetails(CreateComponentHandoverDetails createComponentHandoverDetails) {
        this.createComponentHandoverDetails = createComponentHandoverDetails;
    }
}
