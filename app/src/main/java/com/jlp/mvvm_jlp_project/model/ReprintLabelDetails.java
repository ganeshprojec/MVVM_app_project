package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;

import javax.inject.Inject;

public class ReprintLabelDetails
{
    @Inject
    public ReprintLabelDetails() {}

    @Element(name = "printerId",required = false)
    public String printerId;

    @Element(name = "labelsPrinted",required = false)
    public String labelsPrinted;



    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }

    public String getLabelsPrinted() {
        return labelsPrinted;
    }

    public void setLabelsPrinted(String labelsPrinted) {
        this.labelsPrinted = labelsPrinted;
    }

}
