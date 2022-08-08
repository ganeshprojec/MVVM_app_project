package com.jlp.mvvm_jlp_project.view.auth;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentChangePasswordBinding;
import com.jlp.mvvm_jlp_project.model.request.change_password.ChangePasswordDetails;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestEnvelopeChangePassword;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestBodyChangePassword;
import com.jlp.mvvm_jlp_project.model.request.change_password.RequestDataChangePassword;
import com.jlp.mvvm_jlp_project.model.request.change_password_and_logon.ChangePasswordAndLogonDetails;
import com.jlp.mvvm_jlp_project.model.request.change_password_and_logon.RequestBodyChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.model.request.change_password_and_logon.RequestDataChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.model.request.change_password_and_logon.RequestEnvelopeChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.model.response.change_password.ResponseDataChangePassword;
import com.jlp.mvvm_jlp_project.model.response.change_password_and_logon.ResponseDataChangePasswordAndLogon;
import com.jlp.mvvm_jlp_project.pref.AppPreferencesHelper;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;
import com.jlp.mvvm_jlp_project.viewmodel.AuthViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChangePasswordFragment extends BaseFragment {

    private static final String TAG = ChangePasswordFragment.class.getSimpleName();

    private @NonNull
    FragmentChangePasswordBinding binding;
    private AuthViewModel authViewModel;
    private ProgressDialog progressDialog;

    @Inject
    RequestEnvelopeChangePassword envelopeRequestChangePassword;
    @Inject
    RequestBodyChangePassword requestBodyChangePassword;
    @Inject
    RequestDataChangePassword requestDataChangePassword;
    @Inject
    ChangePasswordDetails changePasswordDetails;

    @Inject
    RequestEnvelopeChangePasswordAndLogon requestEnvelopeChangePasswordAndLogon;
    @Inject
    RequestBodyChangePasswordAndLogon requestBodyChangePasswordAndLogon;
    @Inject
    RequestDataChangePasswordAndLogon requestDataChangePasswordAndLogon;
    @Inject
    ChangePasswordAndLogonDetails changePasswordAndLogonDetails;

    private String callFor;

    @Inject
    AppPreferencesHelper appPreferencesHelper;


    public ChangePasswordFragment(String callFor){
        this.callFor = callFor;
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        initObserver();
        initListener();
    }


    private void initListener() {
        binding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                if(isNetworkConnected()){
                    authViewModel.validateChangePassword(
                            binding.inputUsername.getText().toString().trim(),
                            binding.inputOldPassword.getText().toString().trim(),
                            binding.inputNewPassword.getText().toString().trim(),
                            binding.inputConfirmPassword.getText().toString().trim());
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
                }
            }
        });
    }

    /**
     * Added here observer for validation and api call response
     */
    private void initObserver() {
        authViewModel.responseDataChangePassword.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataChangePassword>>() {
            @Override
            public void onChanged(Resource<ResponseDataChangePassword> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING:{
                            progressDialog = Utils.showProgressBar(getActivity());
                            break;
                        }

                        case ERROR:{
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }

                        case SUCCESS:{
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            Toast.makeText(getContext(), R.string.password_changed_successfully, Toast.LENGTH_LONG).show();
                            Helper.redirectToActivity(getActivity(), HomeActivity.class, true);
                            break;
                        }
                    }
                }
            }
        });

        authViewModel.responseDataChangePasswordAndLogon.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataChangePasswordAndLogon>>() {
            @Override
            public void onChanged(Resource<ResponseDataChangePasswordAndLogon> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING:{
                            progressDialog = Utils.showProgressBar(getActivity());
                            break;
                        }

                        case ERROR:{
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }

                        case SUCCESS:{
                            clearViews();
                            Toast.makeText(getContext(), R.string.password_changed_successfully, Toast.LENGTH_LONG).show();
                            Helper.handleResponseAndDoLogin(response.data.getAuthenticationDetails(), getActivity(), appPreferencesHelper);
                            break;
                        }
                    }
                }
            }
        });

        authViewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first){
                    changePassword(binding.inputUsername.getText().toString().trim(),
                            binding.inputOldPassword.getText().toString().trim(),
                            binding.inputNewPassword.getText().toString().trim()
                            );
                } else {
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });
    }


    private void changePassword(String userId, String oldPassword, String newPassword) {
        if (Utils.isInternetAvailable(getContext())){
            switch (callFor){
                case AppConstants.FRAGMENT_CHANGE_PASSWORD:{
                    prepareRequestDataForChangePassword(userId, oldPassword, newPassword);
                    authViewModel.changePassword(envelopeRequestChangePassword);
                    break;
                }
                case AppConstants.FRAGMENT_CHANGE_PASSWORD_AND_LOGON:{
                    prepareRequestDataForChangePasswordAndLogon(userId, oldPassword, newPassword);
                    authViewModel.changePasswordAndLogon(requestEnvelopeChangePasswordAndLogon);
                    break;
                }
            }
        }else{
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
        }
    }

    /**
     * Preparing request data envelope, body, data for soap api call
     * @param userId input userid
     * @param password input password
     */
    private void prepareRequestDataForChangePassword(String userId, String oldPassword, String newPassword) {
        changePasswordDetails.setUserId(userId);
        changePasswordDetails.setOldPassword(oldPassword);
        changePasswordDetails.setNewPassword(newPassword);
        requestDataChangePassword.setChangePasswordDetails(changePasswordDetails);
        requestBodyChangePassword.setRequestDataChangePassword(requestDataChangePassword);
        envelopeRequestChangePassword.setRequestBodyChangePassword(requestBodyChangePassword);
    }

    private void prepareRequestDataForChangePasswordAndLogon(String userId, String oldPassword, String newPassword) {
        changePasswordAndLogonDetails.setUserId(userId);
        changePasswordAndLogonDetails.setOldPassword(oldPassword);
        changePasswordAndLogonDetails.setNewPassword(newPassword);
        requestDataChangePasswordAndLogon.setChangePasswordDetails(changePasswordAndLogonDetails);
        requestBodyChangePasswordAndLogon.setRequestDataChangePasswordAndLogon(requestDataChangePasswordAndLogon);
        requestEnvelopeChangePasswordAndLogon.setRequestBodyChangePasswordAndLogon(requestBodyChangePasswordAndLogon);
    }

    /**
     * Clear text of all views when navigating to another screen
     */
    private void clearViews(){
        binding.inputOldPassword.setText("");
        binding.inputNewPassword.setText("");
        binding.inputConfirmPassword.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}