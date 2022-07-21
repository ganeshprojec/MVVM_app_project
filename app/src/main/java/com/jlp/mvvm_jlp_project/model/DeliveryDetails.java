package com.jlp.mvvm_jlp_project.model;

import java.util.ArrayList;

public class DeliveryDetails {

    private String deliveryNumber = new String();
    private String customerName = new String();

    private String itemNumber = new String();

    private String productCode = new String();
    private String productDescription = new String();

    private ArrayList<LotsInfo> lotsList = new ArrayList<>();

    public DeliveryDetails() {
    }

    public DeliveryDetails(String deliNumber, String custName, String itemNum, String productDesc, ArrayList<LotsInfo> list) {
        this.deliveryNumber = deliNumber;
        this.customerName = custName;
        this.itemNumber = itemNum;
        this.productDescription = productDesc;
        this.lotsList = list;
    }

    public static DeliveryDetails getCopy(DeliveryDetails details) {
        return new DeliveryDetails(details.deliveryNumber, details.customerName, details.getItemNumber(),
                details.getProductDescription(), details.getLotsList());
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ArrayList<LotsInfo> getLotsList() {
        return lotsList;
    }

    public void setLotsList(ArrayList<LotsInfo> lotsList) {
        this.lotsList = lotsList;
    }


    public Integer getTotalLotsInItemCount() {
        return getLotsList().size();
    }

    public Integer getTotalLotsLoadedInItemCount() {
        Integer tempInt = 0;
        for (int i = 0; i < getLotsList().size(); i++) {
            String statusText = getLotsList().get(i).getStatusText().toLowerCase().trim();
            if (statusText.equalsIgnoreCase(LotsInfo.NOT_STORED)) {
                continue;
            } else if (statusText.equalsIgnoreCase(LotsInfo.STORED)) {
                tempInt++;
            }
        }

        return tempInt;
    }

    public ArrayList<LotsInfo> getListLotsLoadedInItem() {

        ArrayList<LotsInfo> listLotsNotStored = new ArrayList<>();

        for (int i = 0; i < getLotsList().size(); i++) {

            String statusText = getLotsList().get(i).getStatusText().toLowerCase().trim();
            if (statusText.equalsIgnoreCase(LotsInfo.NOT_STORED)) {
                listLotsNotStored.add(getLotsList().get(i));
            } /*else if (statusText.equalsIgnoreCase(LotsInfo.STORED)) {
                tempInt++;
            }*/
        }

        return listLotsNotStored;
    }


    // filter only missing items
    public ArrayList<ItemStatusDetails> getItemMissingInItem() {

        ArrayList<ItemStatusDetails> listLotsNotStored = new ArrayList<>();

        for (int i = 0; i < getLotsList().size(); i++) {

            String statusText = getLotsList().get(i).getStatusText().toLowerCase().trim();
            if (statusText.equalsIgnoreCase(LotsInfo.NOT_STORED)) {
                listLotsNotStored.add(getLotsList().get(i).getItemUpdateStatusModel(getDeliveryNumber(), getProductCode()));
            } /*else if (statusText.equalsIgnoreCase(LotsInfo.STORED)) {
                tempInt++;
            }*/
        }

        return listLotsNotStored;
    }


    public static Integer missingItemCount(ArrayList<DeliveryDetails> list) {
        Integer tempInt = 0;

        int totalLots = 0;
        int totalLoaded = 0;
        for (int i = 0; i < list.size(); i++) {
            totalLots = totalLots + list.get(i).getTotalLotsInItemCount();
            totalLoaded = totalLoaded + list.get(i).getTotalLotsLoadedInItemCount();
        }

        /*Log.e("Lots", "" + list.toString());
        Log.e("Loaded Lots", "" + totalLoaded);
        Log.e("Total Lots", "" + totalLots);*/

        tempInt = totalLots - totalLoaded;
        return tempInt;
    }

    public static ArrayList<ItemStatusDetails> getAllMissingItems(ArrayList<DeliveryDetails> list) {

        ArrayList<ItemStatusDetails> listLots = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            DeliveryDetails details = list.get(i);

            listLots.addAll(list.get(i).getItemMissingInItem());
        }

        return listLots;
    }


    @Override
    public String toString() {
        return "DeliveryDetails{" +
                "deliveryNumber='" + deliveryNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", itemNumber='" + itemNumber + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", lotsList=" + lotsList +
                '}';
    }
}
