package com.jlp.mvvm_jlp_project.model.response.authenticate_user;


import com.jlp.mvvm_jlp_project.model.response.ErrorResponse;
import com.jlp.mvvm_jlp_project.utils.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import javax.inject.Inject;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

@Root(name = "AuthenticateUserResponse", strict = false)
@Namespace(reference = Constants.NAMESPACE)
public class ResponseDataAuthenticateUser {
    @Element(name = "AuthenticationDetails", required = false)
    public User userResponse;

    @Element(name = "DITSErrors",required = false)
    private DITSErrors ditsErrors;

    public ErrorResponse errorResponse;


    @Inject ResponseDataAuthenticateUser(){}

    public ResponseDataAuthenticateUser(User userResponse, ErrorResponse errorResponse) {
        this.userResponse = userResponse;
        this.errorResponse = errorResponse;
    }

    public User getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(User userResponse) {
        this.userResponse = userResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public DITSErrors getDitsErrors() {
        return ditsErrors;
    }

    public void setDitsErrors(DITSErrors ditsErrors) {
        this.ditsErrors = ditsErrors;
    }
}
