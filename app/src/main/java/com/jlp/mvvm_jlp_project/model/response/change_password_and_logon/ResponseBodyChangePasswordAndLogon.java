package com.jlp.mvvm_jlp_project.model.response.change_password_and_logon;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "Body", strict = false)
public class ResponseBodyChangePasswordAndLogon {

    @Element(name = "ChangePasswordAndLogonResponse",required = false)
    private ResponseDataChangePasswordAndLogon responseDataChangePasswordAndLogon;

    public ResponseDataChangePasswordAndLogon getResponseDataChangePasswordAndLogon() {
        return responseDataChangePasswordAndLogon;
    }

    public void setResponseDataChangePasswordAndLogon(ResponseDataChangePasswordAndLogon responseDataChangePasswordAndLogon) {
        this.responseDataChangePasswordAndLogon = responseDataChangePasswordAndLogon;
    }
}
