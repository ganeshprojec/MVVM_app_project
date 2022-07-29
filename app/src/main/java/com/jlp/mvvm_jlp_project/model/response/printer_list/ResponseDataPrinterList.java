package com.jlp.mvvm_jlp_project.model.response.printer_list;

import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.DeliveryItemDetails;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "FindPrinterResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataPrinterList {



    @Inject
    ResponseDataPrinterList(){}

    @Element(name = "PrinterDetails", required = false)
    public PrinterDetails printerDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    public PrinterDetails getPrinterDetails() {
        return printerDetails;
    }

    public void setPrinterDetails(PrinterDetails printerDetails) {
        this.printerDetails = printerDetails;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }


}
