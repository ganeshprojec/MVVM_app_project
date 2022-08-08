package com.jlp.mvvm_jlp_project.model.response.change_password_and_logon;


import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.AuthenticationDetails;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "ChangePasswordAndLogonResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataChangePasswordAndLogon {
    @Element(name = "ChangePasswordAndLogonDetails", required = false)
    public AuthenticationDetails authenticationDetails;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }

    public AuthenticationDetails getAuthenticationDetails() {
        return authenticationDetails;
    }

    public void setAuthenticationDetails(AuthenticationDetails authenticationDetails) {
        this.authenticationDetails = authenticationDetails;
    }
}
