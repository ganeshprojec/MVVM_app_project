package com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details;


import android.os.Parcel;

import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.HandoverDetails;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "CreateOrUpdateHandoverDetailsResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataCreateOrUpdateHandoverDetails {

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    @Inject
    ResponseDataCreateOrUpdateHandoverDetails(){}

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
