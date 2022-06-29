package com.jlp.mvvm_jlp_project.model.response.authenticate_user;/*
 * Created by Sandeep(Techno Learning) on 28,June,2022
 */

import org.simpleframework.xml.Element;

public class ErrorType {
    @Element(name = "ErrorNumber", required = false)
    public String ErrorNumber;

    @Element(name = "ErrorMessage", required = false)
    public String ErrorMessage;
}
