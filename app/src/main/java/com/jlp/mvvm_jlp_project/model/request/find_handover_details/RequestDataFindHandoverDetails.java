package com.jlp.mvvm_jlp_project.model.request.find_handover_details;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindHandoverDetailsRequest", strict = false)
public class RequestDataFindHandoverDetails {
    @Inject
    RequestDataFindHandoverDetails(){}

    @Element(name = "cds:barcode",required = false)
    public String barcode;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
