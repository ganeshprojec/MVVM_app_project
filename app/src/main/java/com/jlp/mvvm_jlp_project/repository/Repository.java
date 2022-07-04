package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import androidx.lifecycle.MutableLiveData;
import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseDataChangePassword;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
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

    /**
     * Api call for change password
     * @param envelope
     */
    public void changePassword(RequestEnvelopeChangePassword envelope){
        _responseDataChangePassword.postValue(Resource.loading(null));
        Call<ResponseEnvelopeChangePassword> responseEnvelopeCall
                = apiService.changePasswordAndLogon(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeChangePassword>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeChangePassword> call, Response<ResponseEnvelopeChangePassword> response) {
                handleChangePasswordResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeChangePassword> call, Throwable t) {
                _responseDataChangePassword.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * Api call for authenticate user
     * @param envelope
     */
    public void authenticateUser(RequestEnvelopeAuthenticateUser envelope){
        _responseAuthenticateUser.postValue(Resource.loading(null));
        Call<ResponseEnvelopeAuthenticateUser> responseEnvelopeCall
                = apiService.authenticateUser(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeAuthenticateUser>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeAuthenticateUser> call, Response<ResponseEnvelopeAuthenticateUser> response) {
                handleAuthenticateUserResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeAuthenticateUser> call, Throwable t) {
                _responseAuthenticateUser.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }


    /**
     * handled change password response and did some validation on response
     * @param response
     */
    private void handleChangePasswordResponse(Response<ResponseEnvelopeChangePassword> response){
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

    /**
     * handled authenticate user response and did some validation on response
     * @param response
     */
    private void handleAuthenticateUserResponse(Response<ResponseEnvelopeAuthenticateUser> response){
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
