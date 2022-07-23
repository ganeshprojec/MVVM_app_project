package com.jlp.mvvm_jlp_project.model.request.change_password_and_logon;

/**
 * Created by Sandeep(Techno Learning) on 16,June,2022
 */


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "cds:ChangePasswordAndLogonRequest", strict = false)
public class RequestDataChangePasswordAndLogon {
    @Inject
    RequestDataChangePasswordAndLogon(){}

    @Element(name = "cds:ChangePasswordAndLogonDetails",required = false)
    public ChangePasswordAndLogonDetails changePasswordDetails;

    public ChangePasswordAndLogonDetails getChangePasswordDetails() {
        return changePasswordDetails;
    }

    public void setChangePasswordDetails(ChangePasswordAndLogonDetails changePasswordDetails) {
        this.changePasswordDetails = changePasswordDetails;
    }
}
