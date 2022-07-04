package com.jlp.mvvm_jlp_project.api;

import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseEnvelopeFindLocationDetailsForBarcode;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

public interface ApiService {

    @Headers({"Content-Type: application/soap+xml", "charset: utf-8", "Content-Length: length"})
    @POST("/AuthenticateUser")
    Call<ResponseEnvelopeAuthenticateUser> authenticateUser(@Body RequestEnvelopeAuthenticateUser envelope);

    @Headers({"Content-Type: application/soap+xml", "charset: utf-8", "Content-Length: length"})
    @POST("/ChangePassword")
    Call<ResponseEnvelopeChangePassword> changePasswordAndLogon(@Body RequestEnvelopeChangePassword envelope);

    @Headers({"Content-Type: application/soap+xml", "charset: utf-8", "Content-Length: length"})
    @POST("/FindLocationDetailsForBarcode")
    Call<ResponseEnvelopeFindLocationDetailsForBarcode> findLocationDetailsForBarcode(@Body RequestEnvelopeFindLocationDetailsForBarcode envelope);


}