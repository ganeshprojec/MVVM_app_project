package com.jlp.mvvm_jlp_project.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "CustomerDeliveryAddress", strict = false)
public class CustomerName {

    @Element(name = "Title", required = false)
    String title = new String();

    @Element(name = "Initials", required = false)
    String initials = new String();

    @Element(name = "Surname", required = false)
    String surname = new String();

    @Element(name = "Forename", required = false)
    String forename = new String();

    @Inject
    public CustomerName() {
    }

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

    public String getFullName() {
        return "" + getTitle() + " " + getForename() + " " + getSurname();
    }

    @Override
    public String toString() {
        return "CustomerName{" +
                "title='" + title + '\'' +
                ", initials='" + initials + '\'' +
                ", surname='" + surname + '\'' +
                ", forename='" + forename + '\'' +
                '}';
    }
}
