package com.jlp.mvvm_jlp_project.model.response.reprint_label_detail;

import com.jlp.mvvm_jlp_project.model.response.printer_list.ResponseDataPrinterList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Body", strict = false)
public class ResponseBodyReprintLabel {

    @Element(name = "ReprintLabelsResponse",required = false)
    private ResponseDataReprintLabel responseDataReprintLabel;


    public ResponseDataReprintLabel getResponseDataReprintLabel() {
        return responseDataReprintLabel;
    }

    public void setResponseDataReprintLabel(ResponseDataReprintLabel responseDataReprintLabel) {
        this.responseDataReprintLabel = responseDataReprintLabel;
    }


}
