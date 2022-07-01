package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import androidx.lifecycle.MutableLiveData;
import com.jlp.mvvm_jlp_project.api.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.change_password.EnvelopeRequestChangePassword;
import com.jlp.mvvm_jlp_project.model.response.ErrorResponse;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.EnvelopeRequestAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.EnvelopeResponseAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.change_password.EnvelopeResponseChangePassword;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseDataChangePassword;
import com.jlp.mvvm_jlp_project.utils.AppConstants;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {
    private ApiService apiService;
    @Inject public ResponseDataAuthenticateUser responseDataAuthenticateUser;
    @Inject public ResponseDataChangePassword responseDataChangePassword;
    @Inject public ErrorResponse errorResponse;

    @Inject public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public MutableLiveData<ResponseDataAuthenticateUser> authenticateUser(EnvelopeRequestAuthenticateUser envelope){
        Call<EnvelopeResponseAuthenticateUser> responseEnvelopeCall
                = apiService.authenticateUser(envelope);
        final MutableLiveData<ResponseDataAuthenticateUser> responseMutableLiveData = new MutableLiveData<>();
        responseEnvelopeCall.enqueue(new Callback<EnvelopeResponseAuthenticateUser>() {
            @Override
            public void onResponse(Call<EnvelopeResponseAuthenticateUser> call, Response<EnvelopeResponseAuthenticateUser> response) {
                try {
                    responseDataAuthenticateUser = ((EnvelopeResponseAuthenticateUser)response.body())
                            .getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser();

                    if(responseDataAuthenticateUser.getUserResponse()!=null){
                        errorResponse.setError(false);
                        responseDataAuthenticateUser.setErrorResponse(errorResponse);
                        responseMutableLiveData.postValue(responseDataAuthenticateUser);
                    }else if(responseDataAuthenticateUser.getDitsErrors()!=null){
                        responseDataAuthenticateUser.setErrorResponse(handleError(responseDataAuthenticateUser.getDitsErrors().getDitsError().errorType.ErrorMessage));
                        responseMutableLiveData.postValue(responseDataAuthenticateUser);
                    }else{
                        responseDataAuthenticateUser.setErrorResponse(handleError("Something went wrong"));
                        responseMutableLiveData.postValue(responseDataAuthenticateUser);
                    }
                }catch (Exception ex){
                    responseDataAuthenticateUser.setErrorResponse(handleError(new Exception(ex)));
                    responseMutableLiveData.postValue(responseDataAuthenticateUser);
                }
            }

            @Override
            public void onFailure(Call<EnvelopeResponseAuthenticateUser> call, Throwable t) {
                responseDataAuthenticateUser.setErrorResponse(handleError(new Exception(t)));
                responseMutableLiveData.postValue(responseDataAuthenticateUser);
            }
        });
        return  responseMutableLiveData;
    }

    public MutableLiveData<ResponseDataChangePassword> changePasswordAndLogon(EnvelopeRequestChangePassword envelope){
        Call<EnvelopeResponseChangePassword> responseEnvelopeCall
                = apiService.changePasswordAndLogon(envelope);
        final MutableLiveData<ResponseDataChangePassword> responseMutableLiveData = new MutableLiveData<>();
        responseEnvelopeCall.enqueue(new Callback<EnvelopeResponseChangePassword>() {
            @Override
            public void onResponse(Call<EnvelopeResponseChangePassword> call, Response<EnvelopeResponseChangePassword> response) {
                try {
                    responseDataChangePassword = ((EnvelopeResponseChangePassword)response.body())
                            .getResponseBodyChangePassword().getResponseDataChangePassword();

                    if(responseDataChangePassword.getWasChangePasswordSuccessful()!=null && !responseDataChangePassword.getWasChangePasswordSuccessful().isEmpty()
                    && responseDataChangePassword.getWasChangePasswordSuccessful().equals(AppConstants.SUCCESS)){
                        errorResponse.setError(false);
                        responseDataChangePassword.setErrorResponse(errorResponse);
                        responseMutableLiveData.postValue(responseDataChangePassword);
                    }else if(responseDataChangePassword.getDitsErrors()!=null){
                        responseDataChangePassword.setErrorResponse(handleError(responseDataChangePassword.getDitsErrors().getDitsError().errorType.ErrorMessage));
                        responseMutableLiveData.postValue(responseDataChangePassword);
                    }else{
                        responseDataChangePassword.setErrorResponse(handleError("Something went wrong"));
                        responseMutableLiveData.postValue(responseDataChangePassword);
                    }
                }catch (Exception ex){
                    responseDataChangePassword.setErrorResponse(handleError(new Exception(ex)));
                    responseMutableLiveData.postValue(responseDataChangePassword);
                }
            }

            @Override
            public void onFailure(Call<EnvelopeResponseChangePassword> call, Throwable t) {
                responseDataAuthenticateUser.setErrorResponse(handleError(new Exception(t)));
                responseMutableLiveData.postValue(responseDataChangePassword);
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
