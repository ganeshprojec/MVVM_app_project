package com.jlp.mvvm_jlp_project.model.response.authenticate_user;/*
 * Created by Sandeep(Techno Learning) on 25,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public
class AuthenticationDetails {
    @Element(name = "userId", required = false)
    public String userId;
    @Element(name = "userName", required = false)
    public String userName;
    @ElementList(name = "deliveryCentreNumber", inline=true)
    public List<DeliveryCentreNumber> deliveryCentreNumber = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<DeliveryCentreNumber> getDeliveryCentreNumber() {
        return deliveryCentreNumber;
    }

    public void setDeliveryCentreNumber(List<DeliveryCentreNumber> deliveryCentreNumber) {
        this.deliveryCentreNumber = deliveryCentreNumber;
    }
}
