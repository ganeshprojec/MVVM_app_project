package com.jlp.mvvm_jlp_project.repository;/*
 * Created by Sandeep(Techno Learning) on 21,June,2022
 */

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.request.change_password_and_logon.RequestEnvelopeChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.model.response.DITSErrors;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseDataChangePassword;
import com.jlp.mvvm_jlp_project.model.response.change_password_and_logon.ResponseDataChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.model.response.change_password_and_logon.ResponseEnvelopeChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.ResourcesProvider;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private ApiService apiService;
    private ResourcesProvider resourcesProvider;
    public MutableLiveData<Resource<ResponseDataChangePassword>> _responseDataChangePassword = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataAuthenticateUser>> _responseAuthenticateUser = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataChangePasswordAndLogon>> _responseDataChangePasswordAndLogo = new MutableLiveData<>();

    @Inject public UserRepository(ApiService apiService, ResourcesProvider resourcesProvider) {
        this.apiService = apiService;
        this.resourcesProvider = resourcesProvider;
    }

    /**
     * Api call for change password
     * @param envelope
     */
    public void changePassword(RequestEnvelopeChangePassword envelope){
        _responseDataChangePassword.postValue(Resource.loading(null));
        Call<ResponseEnvelopeChangePassword> responseEnvelopeCall
                = apiService.changePassword(envelope);
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
     * handled change password response and did some validation on response
     * @param response
     */
    private void handleChangePasswordResponse(Response<ResponseEnvelopeChangePassword> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getDitsErrors()==null) {
                _responseDataChangePassword.postValue(Resource.success(response.body().getResponseBodyChangePassword().getResponseDataChangePassword()));
            }else if(response.errorBody()!=null){
                _responseDataChangePassword.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getDitsErrors()!=null){
                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodes(response.body().getResponseBodyChangePassword().getResponseDataChangePassword().getDitsErrors());
                _responseDataChangePassword.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseDataChangePassword.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseDataChangePassword.postValue(Resource.error(AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE, null));
            Log.e(TAG, AppConstants.ERROR_WHILE_HANDLING_THE_RESPONSE + ex);
        }
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
     * handled authenticate user response and did some validation on response
     * @param response
     */
    private void handleAuthenticateUserResponse(Response<ResponseEnvelopeAuthenticateUser> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser().getDitsErrors()==null) {
                _responseAuthenticateUser.postValue(Resource.success(response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser()));
            }else if(response.errorBody()!=null){
                _responseAuthenticateUser.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser().getDitsErrors()!=null){
                String errorNumber = response.body().getResponseBodyAuthenticateUser().getResponseDataAuthenticateUser().getDitsErrors().getDitsError().getErrorType().getErrorNumber();
                if(errorNumber.equals(AppConstants.ONE_ZERO_ONE)){
                    _responseAuthenticateUser.postValue(Resource.error(resourcesProvider.getString(R.string.login_failure), errorNumber, null));
                }else{
                    _responseAuthenticateUser.postValue(Resource.error(response.body().getResponseBodyAuthenticateUser()
                                    .getResponseDataAuthenticateUser().getDitsErrors().getDitsError().getErrorType().getErrorMessage(),
                            errorNumber, null));
                }
            } else{
                _responseAuthenticateUser.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);

            }
        }catch (Exception ex){
            _responseAuthenticateUser.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG, AppConstants.ERROR_SOMETHING_WENT_WRONG + ex);
        }
    }

    /**
     * Api call for authenticate user
     * @param envelope
     */
    public void changePasswordAndLogon(RequestEnvelopeChangePasswordAndLogon envelope){
        _responseDataChangePasswordAndLogo.postValue(Resource.loading(null));
        Call<ResponseEnvelopeChangePasswordAndLogon> responseEnvelopeCall
                = apiService.changePasswordAndLogon(envelope);
        responseEnvelopeCall.enqueue(new Callback<ResponseEnvelopeChangePasswordAndLogon>() {
            @Override
            public void onResponse(Call<ResponseEnvelopeChangePasswordAndLogon> call, Response<ResponseEnvelopeChangePasswordAndLogon> response) {
                handleChangePasswordAndLogonResponse(response);
            }
            @Override
            public void onFailure(Call<ResponseEnvelopeChangePasswordAndLogon> call, Throwable t) {
                _responseDataChangePasswordAndLogo.postValue(Resource.error(t.getMessage(), null));
            }
        });
    }

    /**
     * handled authenticate user response and did some validation on response
     * @param response
     */
    private void handleChangePasswordAndLogonResponse(Response<ResponseEnvelopeChangePasswordAndLogon> response){
        try {
            if(response.isSuccessful() && response.body()!=null &&
                    response.body().getResponseBodyChangePasswordAndLogon().getResponseDataChangePasswordAndLogon().getDitsErrors()==null) {
                _responseDataChangePasswordAndLogo.postValue(Resource.success(response.body().getResponseBodyChangePasswordAndLogon().getResponseDataChangePasswordAndLogon()));
            }else if(response.errorBody()!=null){
                _responseDataChangePasswordAndLogo.postValue(Resource.error(AppConstants.ERROR_WHILE_GETTING_THE_RESPONSE, null));
            }else if(response.body().getResponseBodyChangePasswordAndLogon().getResponseDataChangePasswordAndLogon().getDitsErrors()!=null){

                Pair pairOfErrorMessageAndCode = handleCommonAPIResponseErrorCodes(response.body().getResponseBodyChangePasswordAndLogon().getResponseDataChangePasswordAndLogon().getDitsErrors());
                _responseDataChangePasswordAndLogo.postValue(Resource.error(pairOfErrorMessageAndCode.first.toString(), pairOfErrorMessageAndCode.second.toString(),null));
            } else{
                _responseDataChangePasswordAndLogo.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
                Log.i(TAG, AppConstants.ERROR_RESPONSE_IS_NEITHER_SUCCESS_NOR_ERROR);
            }
        }catch (Exception ex){
            _responseDataChangePasswordAndLogo.postValue(Resource.error(AppConstants.ERROR_SOMETHING_WENT_WRONG, null));
            Log.e(TAG, AppConstants.ERROR_SOMETHING_WENT_WRONG + ex);
        }
    }

    private Pair handleCommonAPIResponseErrorCodes(DITSErrors ditsErrors) {
        Pair pairOfErrorMessageAndCode = new Pair<> (ditsErrors.getDitsError().getErrorType().getErrorMessage(),
                ditsErrors.getDitsError().getErrorType().getErrorNumber());
        String errorNumber = ditsErrors.getDitsError().getErrorType().getErrorNumber();
        if(errorNumber.equals(AppConstants.ONE_ZERO_ONE)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.invalid_authentication_details), AppConstants.ONE_ZERO_ONE);
        } else if(errorNumber.equals(AppConstants.ONE_ZERO_TWO)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.no_valid_branches_setup), AppConstants.ONE_ZERO_TWO);
        }else if(errorNumber.equals(AppConstants.ONE_ZERO_THREE)){
            pairOfErrorMessageAndCode =  new Pair(resourcesProvider.getString(R.string.credentials_not_accepted), AppConstants.ONE_ZERO_THREE);
        }
        return pairOfErrorMessageAndCode;
    }
}
