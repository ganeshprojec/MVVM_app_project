package com.jlp.mvvm_jlp_project.viewmodel;/*
 * Created by Sandeep(Techno Learning) on 18,June,2022
 */

import android.app.Application;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.request.change_password_and_logon.RequestEnvelopeChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseDataChangePassword;
import com.jlp.mvvm_jlp_project.model.response.change_password_and_logon.ResponseDataChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.repository.UserRepository;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.StringUtils;
import com.jlp.mvvm_jlp_project.utils.Utils;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthViewModel extends BaseViewModel {
    UserRepository repository;
    public MutableLiveData<Pair<Boolean, Integer>> validationResult = new MutableLiveData<>();
    public MutableLiveData<Resource<ResponseDataChangePassword>> responseDataChangePassword;
    public MutableLiveData<Resource<ResponseDataAuthenticateUser>> responseAuthenticateUser;
    public MutableLiveData<Resource<ResponseDataChangePasswordAndLogon>> responseDataChangePasswordAndLogon;

    @Inject
    public AuthViewModel(@NonNull Application application, UserRepository repository) {
        super(application);
        this.repository = repository;
        responseDataChangePassword = repository._responseDataChangePassword;
        responseAuthenticateUser = repository._responseAuthenticateUser;
        responseDataChangePasswordAndLogon = repository._responseDataChangePasswordAndLogo;
    }

    public void validateLogin(String userId, String password) {
        Pair result = new Pair<>(true, 0);
        if (TextUtils.isEmpty(userId) && TextUtils.isEmpty(password)) {
            result = new Pair(false, R.string.please_enter_user_id_and_password);
        } else if (TextUtils.isEmpty(userId)) {
            result = new Pair(false, R.string.please_enter_user_id);
        } else if (TextUtils.isEmpty(password)) {
            result = new Pair(false, R.string.please_enter_password);
        }
        validationResult.setValue(result);
    }

    public void validateChangePassword(String oldPassword,
                                       String newPassword, String confirmPassword) {
        Pair result = new Pair<>(true, 0);
        if (TextUtils.isEmpty(oldPassword)) {
            result = new Pair(false, R.string.please_enter_old_password);
        } else if (TextUtils.isEmpty(newPassword)) {
            result = new Pair(false, R.string.please_enter_new_password);
        } else if (TextUtils.isEmpty(confirmPassword)) {
            result = new Pair(false, R.string.please_enter_confirm_password);
        } else if (!newPassword.matches(AppConstants.PASSWORD_REGEXP)) {
            result = new Pair(false, R.string.wrong_password_format);
        }  else if (!newPassword.equals(confirmPassword)) {
            result = new Pair(false, R.string.new_password_and_confirm_password_mismatch);
        } else if (newPassword.equals(AppConstants.PASSWORD)) {
            result = new Pair(false, R.string.new_password_and_old_password_should_not_be_same);
        }
        validationResult.setValue(result);
    }

    public void authenticateUser(RequestEnvelopeAuthenticateUser envelope) {
        repository.authenticateUser(envelope);
    }

    public void changePassword(RequestEnvelopeChangePassword envelope) {
        repository.changePassword(envelope);
    }

    public void changePasswordAndLogon(RequestEnvelopeChangePasswordAndLogon envelope) {
        repository.changePasswordAndLogon(envelope);
    }


    public int test(int num) {
        return num;
    }
}
