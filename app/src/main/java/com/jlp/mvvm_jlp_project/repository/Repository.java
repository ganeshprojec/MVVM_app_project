package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import androidx.lifecycle.LiveData;
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
import com.jlp.mvvm_jlp_project.utils.Constants;
import com.jlp.mvvm_jlp_project.utils.Resource;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {
    private ApiService apiService;
    public MutableLiveData<Resource<ResponseDataChangePassword>> _responseDataChangePassword = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataAuthenticateUser>> _responseAuthenticateUser = new MutableLiveData<>();

    @Inject public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void changePassword(EnvelopeRequestChangePassword envelope){
        _responseDataChangePassword.postValue(Resource.loading(null));
        Call<EnvelopeResponseChangePassword> responseEnvelopeCall
                = apiService.changePasswordAndLogon(envelope);
        responseEnvelopeCall.enqueue(new Callback<EnvelopeResponseChangePassword>() {
            @Override
            public void onResponse(Call<EnvelopeResponseChangePassword> call, Response<EnvelopeResponseChangePassword> response) {
                handleChangePasswordResponse(response);
            }
            @Override
            public void onFailure(Call<EnvelopeResponseChangePassword> call, Throwable t) {
                _responseDataChangePassword.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    public void authenticateUser(EnvelopeRequestAuthenticateUser envelope){
        _responseAuthenticateUser.postValue(Resource.loading(null));
        Call<EnvelopeResponseAuthenticateUser> responseEnvelopeCall
                = apiService.authenticateUser(envelope);
        responseEnvelopeCall.enqueue(new Callback<EnvelopeResponseAuthenticateUser>() {
            @Override
            public void onResponse(Call<EnvelopeResponseAuthenticateUser> call, Response<EnvelopeResponseAuthenticateUser> response) {
                handleAuthenticateUserResponse(response);
            }
            @Override
            public void onFailure(Call<EnvelopeResponseAuthenticateUser> call, Throwable t) {
                _responseAuthenticateUser.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    private void handleChangePasswordResponse(Response<EnvelopeResponseChangePassword> response){
        if(response.isSuccessful() && response.body()!=null &&
                response.body().getResponseBodyChangePassword()!=null &&
                response.body().getResponseBodyChangePassword().getResponseDataChangePassword()!=null &&
                response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getWasChangePasswordSuccessful()!=null &&
                response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getWasChangePasswordSuccessful().equals(AppConstants.SUCCESS)) {
            _responseDataChangePassword.postValue(Resource.success(response.body().getResponseBodyChangePassword().getResponseDataChangePassword()));
        }else if(response.errorBody()!=null){
            _responseDataChangePassword.postValue(Resource.error("Error while getting the response", null));
        }else if(response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getDitsErrors()!=null){
            _responseDataChangePassword.postValue(Resource.error( response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                    response.body().getResponseBodyChangePassword().getResponseDataChangePassword()));
        } else{
            _responseDataChangePassword.postValue(Resource.error("Something Went Wrong", null));
        }
    }

    private void handleAuthenticateUserResponse(Response<EnvelopeResponseAuthenticateUser> response){
        if(response.isSuccessful() && response.body()!=null &&
                response.body().getResponseBodyAuthenticateUser()!=null &&
                response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser()!=null &&
                response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser().getUserResponse()!=null        ) {
            _responseAuthenticateUser.postValue(Resource.success(response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser()));
        }else if(response.errorBody()!=null){
            _responseDataChangePassword.postValue(Resource.error("Error while getting the response", null));
        }else if(response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser().getDitsErrors()!=null){
            _responseAuthenticateUser.postValue(Resource.error( response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                    response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser()));
        } else{
            _responseAuthenticateUser.postValue(Resource.error("Something Went Wrong", null));
        }
    }
}
