package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;

import javax.inject.Inject;

public class PrinterDetails {
    @Inject
    public PrinterDetails() {}

    @Element(name = "printerId",required = false)
    public String printerId;

    @Element(name = "printerName",required = false)
    public String printerName;

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }


}
