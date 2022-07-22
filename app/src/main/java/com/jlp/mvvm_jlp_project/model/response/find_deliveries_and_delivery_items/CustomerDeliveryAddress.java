package com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items;/*
 * Created by Sandeep(Techno Learning) on 20,July,2022
 */

import org.simpleframework.xml.Element;

import javax.inject.Inject;

public class CustomerDeliveryAddress {

    @Element(name = "Title",required = false)
    public String title;
    @Element(name = "Initials",required = false)
    public String initials;
    @Element(name = "Surname",required = false)
    public String surname;
    @Element(name = "Forename",required = false)
    public String forename;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }
}
