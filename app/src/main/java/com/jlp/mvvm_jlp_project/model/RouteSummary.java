package com.jlp.mvvm_jlp_project.model;

public class RouteSummary {

    private String routeNumber = new String();
    private String deliveryDate = new String();
    private String totalDeliveries = new String();
    private String totalLots = new String();
    private String deliveryLotsLoaded = new String();


    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(String totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }

    public String getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(String totalLots) {
        this.totalLots = totalLots;
    }

    public String getDeliveryLotsLoaded() {
        return deliveryLotsLoaded;
    }

    public void setDeliveryLotsLoaded(String deliveryLotsLoaded) {
        this.deliveryLotsLoaded = deliveryLotsLoaded;
    }
}
