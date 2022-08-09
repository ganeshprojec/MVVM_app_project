package com.jlp.mvvm_jlp_project.model.request.reprint_label_detail;

import com.jlp.mvvm_jlp_project.model.ReprintLabelDetails;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.AuthenticationDetails;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "ReprintLabelsRequest", strict = false)
public class RequestDataReprintLabel {

    @Inject
    RequestDataReprintLabel(){}

    public ReprintLabelDetailsReq getReprintLabelDetails() {
        return reprintLabelDetails;
    }

    public void setReprintLabelDetails(ReprintLabelDetailsReq reprintLabelDetails) {
        this.reprintLabelDetails = reprintLabelDetails;
    }

    @Element(name = "cds:ReprintLabelDetails",required = false)
    public ReprintLabelDetailsReq reprintLabelDetails;









}
