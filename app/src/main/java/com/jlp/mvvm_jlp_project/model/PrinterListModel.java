package com.jlp.mvvm_jlp_project.model;

public class PrinterListModel
{

    private String printerId;
    private String printerName;


    public PrinterListModel(String printerId,String printerName )
    {
        this.printerId =printerId;
        this.printerName = printerName;
    }


    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getPrinterName() {
        return printerName;
    }

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }


}
