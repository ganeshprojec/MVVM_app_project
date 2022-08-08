package com.jlp.mvvm_jlp_project.model;


public class LotsInfo {

    public static final String NOT_STORED = "Not Stored";
    public static final String STORED = "Loaded";
    public static final String MISSING = "MISSING";


    // for list labelText
    private String lotNumber = new String();

    // for List LabelText
    private String lotLocation = new String();

    String number = new String();

    String lotId = new String();

    String lotBarcode = new String();

    String status = new String();

    String statusText = new String();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLotBarcode() {
        return lotBarcode;
    }

    public void setLotBarcode(String lotBarcode) {
        this.lotBarcode = lotBarcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


    public LotsInfo() {
    }

    public LotsInfo(String lotNumber, String lotLocation, String number, String lotId, String lotBarcode, String status, String statusText) {
        this.lotNumber = lotNumber;
        this.lotLocation = lotLocation;
        this.number = number;
        this.lotId = lotId;
        this.lotBarcode = lotBarcode;
        this.status = status;
        this.statusText = statusText;
    }

   /* public LotsInfo(String lotNumber, String lotLocation) {
        this.lotNumber = lotNumber;
        this.lotLocation = lotLocation;
    }*/


    public static LotsInfo getCopy(LotsInfo lot) {
        return new LotsInfo(lot.lotNumber, lot.lotLocation, lot.number, lot.lotId, lot.lotBarcode, lot.status, lot.statusText);
    }


    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotLocation() {
        return lotLocation;
    }

    public void setLotLocation(String lotLocation) {
        this.lotLocation = lotLocation;
    }


    public ItemStatusDetails getItemUpdateStatusModel(String deliveryId, String productCode) {
        ItemStatusDetails forUpdate = new ItemStatusDetails();

        forUpdate.setComponentId(getLotId());
        forUpdate.setComponentNum(getNumber());

        forUpdate.setDeliveryId(deliveryId);
        forUpdate.setProductCode(productCode);


        /*forUpdate.setUserName(username);
        forUpdate.setUserId(userId);
        forUpdate.setUserIdAuthorized(authorizeUserId);
        forUpdate.setComponentStatus(getStatus());
        */
        return forUpdate;
    }


    @Override
    public String toString() {
        return "LotsInfo{" +
                "lotNumber='" + lotNumber + '\'' +
                ", lotLocation='" + lotLocation + '\'' +
                ", number='" + number + '\'' +
                ", lotId='" + lotId + '\'' +
                ", lotBarcode='" + lotBarcode + '\'' +
                ", status='" + status + '\'' +
                ", statusText='" + statusText + '\'' +
                '}';
    }
}
