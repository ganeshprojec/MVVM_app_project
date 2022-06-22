package com.jlp.mvvm_jlp_project.model;/*
 * Created by Sandeep(Techno Learning) on 18,June,2022
 */


import android.util.Patterns;

public class LoginUserRequestModel {

    private String strEmailAddress;
    private String strPassword;

    public LoginUserRequestModel(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;
    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }

}
