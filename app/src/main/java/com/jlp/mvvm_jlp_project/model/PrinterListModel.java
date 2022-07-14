package com.jlp.mvvm_jlp_project.model;

public class PrinterListModel
{
    private String printerCount;
    private String printerName;



    public PrinterListModel(String printerCount, String printerName)
    {
        this.printerCount = printerCount;
        this.printerName = printerName;
    }
    public void setPrinterCount(String printerCount) {
        this.printerCount = printerCount;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }


    public String getPrinterCount() {
        return printerCount;
    }

    public String getPrinterName() {
        return printerName;
    }

}
