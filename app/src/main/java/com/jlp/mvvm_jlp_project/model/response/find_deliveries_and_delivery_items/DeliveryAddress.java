package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;/*
 * Created by Sandeep(Techno Learning) on 21,July,2022
 */

import org.simpleframework.xml.Element;

public class DeliveryAddress {

    @Element(name = "AddressId",required = false)
    public String addressId;
    @Element(name = "BuildingName",required = false)
    public String buildingName;
    @Element(name = "Premise",required = false)
    public String premise;
    @Element(name = "CompanyName",required = false)
    public String companyName;
    @Element(name = "Country",required = false)
    public String country;
    @Element(name = "County",required = false)
    public String county;
    @Element(name = "Crtstamp",required = false)
    public String crtstamp;
    @Element(name = "DPSCode",required = false)
    public String dPSCode;
    @Element(name = "Locality",required = false)
    public String locality;
    @Element(name = "LocationReferenceNumber",required = false)
    public int locationReferenceNumber;
    @Element(name = "Postcode",required = false)
    public String postcode;
    @Element(name = "PostTown",required = false)
    public String postTown;
    @Element(name = "Thoroughfare",required = false)
    public String thoroughfare;
    @Element(name = "Updstamp",required = false)
    public String updstamp;

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

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
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

    public String getCrtstamp() {
        return crtstamp;
    }

    public void setCrtstamp(String crtstamp) {
        this.crtstamp = crtstamp;
    }

    public String getdPSCode() {
        return dPSCode;
    }

    public void setdPSCode(String dPSCode) {
        this.dPSCode = dPSCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getLocationReferenceNumber() {
        return locationReferenceNumber;
    }

    public void setLocationReferenceNumber(int locationReferenceNumber) {
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

    public String getUpdstamp() {
        return updstamp;
    }

    public void setUpdstamp(String updstamp) {
        this.updstamp = updstamp;
    }
}
