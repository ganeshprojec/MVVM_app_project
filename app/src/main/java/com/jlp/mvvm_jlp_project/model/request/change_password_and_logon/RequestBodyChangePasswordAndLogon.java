package com.jlp.mvvm_jlp_project.model.request.change_password_and_logon;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "soapenv:Body", strict = false)
public class RequestBodyChangePasswordAndLogon {

    @Inject
    RequestBodyChangePasswordAndLogon(){}

    @Element(name = "cds:ChangePasswordAndLogonRequest",required = false)
    private RequestDataChangePasswordAndLogon requestDataChangePasswordAndLogon;

    public RequestDataChangePasswordAndLogon getRequestDataChangePasswordAndLogon() {
        return requestDataChangePasswordAndLogon;
    }

    public void setRequestDataChangePasswordAndLogon(RequestDataChangePasswordAndLogon requestDataChangePasswordAndLogon) {
        this.requestDataChangePasswordAndLogon = requestDataChangePasswordAndLogon;
    }
}
