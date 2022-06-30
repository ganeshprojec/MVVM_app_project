package com.jlp.mvvm_jlp_project.viewmodel;/*
 * Created by Sandeep(Techno Learning) on 18,June,2022
 */

import android.text.TextUtils;
import android.util.Pair;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.constants.AppConstants;
import com.jlp.mvvm_jlp_project.model.ChangePasswordRequestModel;
import com.jlp.mvvm_jlp_project.model.request.EnvelopeRequest;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.repository.Repository;
import com.jlp.mvvm_jlp_project.utils.StringUtils;
import com.jlp.mvvm_jlp_project.utils.Utils;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthViewModel extends BaseViewModel {

    Repository repository;
    public MutableLiveData<Pair<Boolean, Integer>> validationResult = new MutableLiveData<>();

    @Inject
    public AuthViewModel(Repository repository){
        this.repository = repository;
    }

    public void validateLogin(String username, String password) {
        Pair result = new Pair<> (true, 0);
        if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            result = new Pair(false, R.string.please_enter_user_id_and_password);
        }if(TextUtils.isEmpty(username)){
            result = new Pair(false, R.string.please_enter_user_id);
        }else if(TextUtils.isEmpty(password)){
            result = new Pair(false, R.string.please_enter_password);
        }else if(password.length() < AppConstants.MIN_PASSWORD_LENGTH || password.length() > AppConstants.MAX_PASSWORD_LENGTH){
            result = new Pair(false, R.string.password_should_be);
        }
        validationResult.setValue(result);
    }

    // TODO: Old password match remaining
    public void validateChangePassword(String username, String oldPassword,
                                       String newPassword, String confirmPassword) {
        Pair<Boolean, Integer> result = null;
        if(TextUtils.isEmpty(oldPassword)){
            result = new Pair(false, R.string.please_enter_old_password);
            return;
        }if(TextUtils.isEmpty(newPassword) && TextUtils.isEmpty(confirmPassword)){
            result = new Pair(false, R.string.please_enter_new_password_and_confirm);
        }else if(newPassword.length() < AppConstants.MIN_PASSWORD_LENGTH || newPassword.length() > AppConstants.MAX_PASSWORD_LENGTH){
            result = new Pair(false, R.string.new_password_should_be);
        }else if(StringUtils.isValidPassword(newPassword)){
            result = new Pair(false, R.string.at_least_one_alpha_one_num_req);
        }else if(!newPassword.equals(confirmPassword)){
            result = new Pair(false, R.string.new_password_and_confirm_password_mismatch);
        }else{
            result = new Pair <Boolean, Integer> (true, 0);
        }
        validationResult.setValue(result);
    }

    public MutableLiveData<ResponseDataAuthenticateUser> authenticateUser(EnvelopeRequest envelope){
        return repository.authenticateUser(envelope);
    }

    public void changePasswordUser(ChangePasswordRequestModel changePasswordRequestModel) {
    }
}
