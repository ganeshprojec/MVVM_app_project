package com.jlp.mvvm_jlp_project.model.response.reprint_label_detail;

import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.ReprintLabelDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindPrinterResponse", strict = false)
public class ResponseDataReprintLabel {

    @Inject
    ResponseDataReprintLabel(){}

    @Element(name = "ReprintLabelDetails", required = false)
    public ReprintLabelDetails reprintLabelDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    public ReprintLabelDetails getReprintLabelDetails() {
        return reprintLabelDetails;
    }

    public void setReprintLabelDetails(ReprintLabelDetails reprintLabelDetails) {
        this.reprintLabelDetails = reprintLabelDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }


}
