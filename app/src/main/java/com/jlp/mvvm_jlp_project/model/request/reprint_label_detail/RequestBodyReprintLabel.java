package com.jlp.mvvm_jlp_project.model.request.reprint_label_detail;

import com.jlp.mvvm_jlp_project.model.request.printer_list.RequestDataPrinterList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyReprintLabel {

    @Inject
    RequestBodyReprintLabel(){}

    @Element(name = "cds:ReprintLabelsRequest",required = false)
    private RequestDataReprintLabel requestDataReprintLabel;

    public RequestDataReprintLabel getRequestDataReprintLabel() {
        return requestDataReprintLabel;
    }

    public void setRequestDataReprintLabel(RequestDataReprintLabel requestDataReprintLabel) {
        this.requestDataReprintLabel = requestDataReprintLabel;
    }


}
