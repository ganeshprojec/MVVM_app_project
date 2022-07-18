package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "DeliveryAddress", strict = false)
public class DeliveryAddress {

    @Element(name = "AddressId", required = false)
    String addressId = new String();

    @Element(name = "BuildingName", required = false)
    String buildingName = new String();

    @Element(name = "Premise", required = false)
    String premises = new String();

    @Element(name = "CompanyName", required = false)
    String companyName = new String();

    @Element(name = "Country", required = false)
    String country = new String();

    @Element(name = "County", required = false)
    String county = new String();

    @Element(name = "Crtstamp", required = false)
    String crtStamp = new String();

    @Element(name = "DPSCode", required = false)
    String dpsCode = new String();

    @Element(name = "Locality", required = false)
    String locality = new String();

    @Element(name = "LocationReferenceNumber", required = false)
    String locationReferenceNumber = new String();

    @Element(name = "Postcode", required = false)
    String postcode = new String();

    @Element(name = "PostTown", required = false)
    String postTown = new String();

    @Element(name = "Thoroughfare", required = false)
    String thoroughfare = new String();

    @Element(name = "Updstamp", required = false)
    String updStamp = new String();

    @Inject
    public DeliveryAddress() {
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPremises() {
        return premises;
    }

    public void setPremises(String premises) {
        this.premises = premises;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCrtStamp() {
        return crtStamp;
    }

    public void setCrtStamp(String crtStamp) {
        this.crtStamp = crtStamp;
    }

    public String getDpsCode() {
        return dpsCode;
    }

    public void setDpsCode(String dpsCode) {
        this.dpsCode = dpsCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocationReferenceNumber() {
        return locationReferenceNumber;
    }

    public void setLocationReferenceNumber(String locationReferenceNumber) {
        this.locationReferenceNumber = locationReferenceNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(String thoroughfare) {
        this.thoroughfare = thoroughfare;
    }

    public String getUpdStamp() {
        return updStamp;
    }

    public void setUpdStamp(String updStamp) {
        this.updStamp = updStamp;
    }


    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "addressId='" + addressId + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", premises='" + premises + '\'' +
                ", companyName='" + companyName + '\'' +
                ", country='" + country + '\'' +
                ", county='" + county + '\'' +
                ", crtStamp='" + crtStamp + '\'' +
                ", dpsCode='" + dpsCode + '\'' +
                ", locality='" + locality + '\'' +
                ", locationReferenceNumber='" + locationReferenceNumber + '\'' +
                ", postcode='" + postcode + '\'' +
                ", postTown='" + postTown + '\'' +
                ", thoroughfare='" + thoroughfare + '\'' +
                ", updStamp='" + updStamp + '\'' +
                '}';
    }
}
