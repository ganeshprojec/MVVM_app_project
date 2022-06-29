package com.jlp.mvvm_jlp_project.model.response;/*
 * Created by Sandeep(Techno Learning) on 28,June,2022
 */

import javax.inject.Inject;

public class ErrorResponse {
    public boolean isError = false;
    public boolean isErrorFromRemote = false;
    public String errorCode;
    public String errorMessage;
    public Exception exception;
    public String errorMessageToDisplay = "Something went wrong";

    @Inject public ErrorResponse(){}

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getErrorMessageToDisplay() {
        return errorMessageToDisplay;
    }

    public void setErrorMessageToDisplay(String errorMessageToDisplay) {
        this.errorMessageToDisplay = errorMessageToDisplay;
    }

    public boolean isErrorFromRemote() {
        return isErrorFromRemote;
    }

    public void setErrorFromRemote(boolean errorFromRemote) {
        isErrorFromRemote = errorFromRemote;
    }
}
