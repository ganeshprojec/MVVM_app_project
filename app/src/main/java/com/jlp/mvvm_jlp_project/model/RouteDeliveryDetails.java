package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "DeliveryDetails", strict = false)
public class RouteDeliveryDetails {

    @Element(name = "deliveryId", required = false)
    String deliveryId = new String();

    @Element(name = "deliveryDate", required = false)
    String deliveryDate = new String();

    @Element(name = "RecipientName", required = false)
    String recipientName = new String();

    @Element(name = "totalLotNumber", required = false)
    String totalLotNumber = new String();

    @Element(name = "serviceIncluded", required = false)
    String serviceIncluded = new String();

    @Element(name = "CustomerDeliveryAddress", required = false)
    CustomerName name = new CustomerName();

    @Element(name = "DeliveryAddress", required = false)
    DeliveryAddress address = new DeliveryAddress();

    @ElementList(name = "cds:DeliveryDetails", entry = "DeliveryGoodsDetails", required = false, inline = true)
    //@ElementList(name = "cds:DeliveryGoodsDetails", required = false)
    ArrayList<DeliveryGoodsDetails> goods = new ArrayList<DeliveryGoodsDetails>();

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getTotalLotNumber() {
        return totalLotNumber;
    }

    public void setTotalLotNumber(String totalLotNumber) {
        this.totalLotNumber = totalLotNumber;
    }

    public String getServiceIncluded() {
        return serviceIncluded;
    }

    public void setServiceIncluded(String serviceIncluded) {
        this.serviceIncluded = serviceIncluded;
    }

    public CustomerName getName() {
        return name;
    }

    public void setName(CustomerName name) {
        this.name = name;
    }

    public DeliveryAddress getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddress address) {
        this.address = address;
    }

    public ArrayList<DeliveryGoodsDetails> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<DeliveryGoodsDetails> goods) {
        this.goods = goods;
    }

    public ArrayList<DeliveryDetails> getDetailsList() {
        ArrayList<DeliveryDetails> list = new ArrayList<>();
        for (int i = 0; i < getGoods().size(); i++) {
            DeliveryDetails detail = new DeliveryDetails();
            detail.setDeliveryNumber(getDeliveryId());
            detail.setCustomerName(getName().getFullName());
            detail.setItemNumber(getGoods().get(i).getProductCode());
            detail.setProductDescription(
                    " " + getGoods().get(i).getOrderDescriptionClean());

            ArrayList<LotsInfo> listLot = new ArrayList<>();
            for (int j = 0; j < getGoods().get(i).getLotInfo().size(); j++) {
                DeliveryLotDetails lot = getGoods().get(i).getLotInfo().get(j);
                if (lot.getStatusText().trim().equalsIgnoreCase("in bay")) {
                    // TODO: set name15 as labelText
                    // second param to be set as name15 text
                }
                listLot.add(new LotsInfo("L" + lot.getNumber(),
                        "" + lot.getStatusText(), lot.number, lot.lotId, lot.lotBarcode, lot.status, lot.statusText));
            }

            detail.setLotsList(listLot);
            list.add(detail);
        }

        return list;
    }

    @Override
    public String toString() {
        return "RouteDeliveryDetails{" +
                "deliveryId='" + deliveryId + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", totalLotNumber='" + totalLotNumber + '\'' +
                ", serviceIncluded='" + serviceIncluded + '\'' +
                ", name=" + name +
                ", address=" + address +
                ", goods=" + goods +
                '}';
    }
}
