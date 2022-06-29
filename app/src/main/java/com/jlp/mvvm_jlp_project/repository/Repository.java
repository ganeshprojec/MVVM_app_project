package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import androidx.lifecycle.MutableLiveData;
import com.jlp.mvvm_jlp_project.api.api.ApiService;
import com.jlp.mvvm_jlp_project.model.response.ErrorResponse;
import com.jlp.mvvm_jlp_project.model.request.EnvelopeRequest;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.EnvelopeResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {
    private ApiService apiService;
    @Inject public ResponseDataAuthenticateUser responseDataAuthenticateUser;
    @Inject public ErrorResponse errorResponse;

    @Inject public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public MutableLiveData<ResponseDataAuthenticateUser> authenticateUser(EnvelopeRequest envelope){
        Call<EnvelopeResponse> responseEnvelopeCall
                = apiService.authenticateUser(envelope);
        final MutableLiveData<ResponseDataAuthenticateUser> responseMutableLiveData = new MutableLiveData<>();
        responseEnvelopeCall.enqueue(new Callback<EnvelopeResponse>() {
            @Override
            public void onResponse(Call<EnvelopeResponse> call, Response<EnvelopeResponse> response) {
                try {
                    responseDataAuthenticateUser = ((EnvelopeResponse)response.body())
                            .getBody().getResponseDataAuthenticateUser();

                    if(responseDataAuthenticateUser.getUserResponse()!=null){
                        errorResponse.setError(false);
                        responseDataAuthenticateUser.setErrorResponse(errorResponse);
                        responseMutableLiveData.postValue(responseDataAuthenticateUser);
                    }else if(responseDataAuthenticateUser.getDitsErrors()!=null){
                        responseDataAuthenticateUser.setErrorResponse(handleError(responseDataAuthenticateUser.getDitsErrors().getDitsError().errorType.ErrorMessage));
                        responseMutableLiveData.postValue(responseDataAuthenticateUser);
                    }else{
                        responseDataAuthenticateUser.setErrorResponse(handleError(responseDataAuthenticateUser.getDitsErrors().getDitsError().errorType.ErrorMessage));
                        responseMutableLiveData.postValue(responseDataAuthenticateUser);
                    }
                }catch (Exception ex){
                    responseDataAuthenticateUser.setErrorResponse(handleError(new Exception(ex)));
                    responseMutableLiveData.postValue(responseDataAuthenticateUser);
                }
            }

            @Override
            public void onFailure(Call<EnvelopeResponse> call, Throwable t) {
                responseDataAuthenticateUser.setErrorResponse(handleError(new Exception(t)));
                responseMutableLiveData.postValue(responseDataAuthenticateUser);
            }
        });
        return  responseMutableLiveData;
    }

    private ErrorResponse handleError(Exception e) {
        errorResponse.setError(true);
        errorResponse.setErrorMessage(e.getMessage());
        errorResponse.setException(e);
        return errorResponse;
    }

    private ErrorResponse handleError(String errorMessage) {
        errorResponse.setError(true);
        errorResponse.setErrorFromRemote(true);
        errorResponse.setErrorMessage(errorMessage);
        errorResponse.setErrorMessageToDisplay(errorMessage);
        return errorResponse;
    }
}
