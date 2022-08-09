package com.jlp.mvvm_jlp_project.model;

public class DeliveryGoodProductModel
{
    private String DeliveryGoodId;
    private String ProductCode;
    private String OrderDescriptionClean;
    private boolean isSelected;

    public String getDeliveryGoodId() {
        return DeliveryGoodId;
    }

    public void setDeliveryGoodId(String deliveryGoodId) {
        DeliveryGoodId = deliveryGoodId;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getOrderDescriptionClean() {
        return OrderDescriptionClean;
    }

    public void setOrderDescriptionClean(String orderDescriptionClean) {
        OrderDescriptionClean = orderDescriptionClean;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
