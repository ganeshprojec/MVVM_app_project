package com.jlp.mvvm_jlp_project.api.api;

import com.jlp.mvvm_jlp_project.model.request.EnvelopeRequest;
import com.jlp.mvvm_jlp_project.model.response.EnvelopeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *  Created by Sandeep(Techno Learning) on 16,June,2022
 */

public interface ApiService {
//    @Headers({"Content-Type: application/soap+xml", "charset: utf-8", "Content-Length: length"})
//    @POST("/websamples.countryinfo/CountryInfoService.wso")
//    Call<ResponseEnvelope> getCapital(@Body Envelope body);

    @Headers({"Content-Type: application/soap+xml", "charset: utf-8", "Content-Length: length"})
    @POST("/AuthenticateUser")
    Call<EnvelopeResponse> authenticateUser(@Body EnvelopeRequest envelope);


}