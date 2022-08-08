package com.jlp.mvvm_jlp_project.model.response.find_handover_details;


import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "FindHandoverDetailsResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataFindHandoverDetails {
    @Element(name = "FoundHandoverDetails", required = false)
    public FoundHandoverDetails foundHandoverDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataFindHandoverDetails(){}

    public FoundHandoverDetails getFoundHandoverDetails() {
        return foundHandoverDetails;
    }

    public void setFoundHandoverDetails(FoundHandoverDetails foundHandoverDetails) {
        this.foundHandoverDetails = foundHandoverDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
