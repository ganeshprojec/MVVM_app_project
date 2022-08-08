package com.jlp.mvvm_jlp_project.model.request.change_password_and_logon;/*
 * Created by Sandeep(Techno Learning) on 30,June,2022
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

@Root(name = "ChangePasswordAndLogonDetails", strict = false)
public class ChangePasswordAndLogonDetails {

    @Inject
    ChangePasswordAndLogonDetails(){}

    @Element(name = "cds:userId",required = false)
    public String userId;
    @Element(name = "cds:oldPassword",required = false)
    public String oldPassword;
    @Element(name = "cds:newPassword",required = false)
    public String newPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
