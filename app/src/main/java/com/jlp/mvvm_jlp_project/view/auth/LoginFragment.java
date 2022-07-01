package com.jlp.mvvm_jlp_project.view.auth;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentLoginBinding;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.EnvelopeRequestAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.AuthenticationDetails;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestBodyAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.home.MenuActivity;
import com.jlp.mvvm_jlp_project.viewmodel.AuthViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog progressDialog;

    private @NonNull
    FragmentLoginBinding binding;
    private AuthViewModel authViewModel;

    @Inject
    EnvelopeRequestAuthenticateUser envelopeAuthenticateUser;
    @Inject
    RequestBodyAuthenticateUser bodyAuthenticateUser;
    @Inject
    RequestDataAuthenticateUser requestDataAuthenticateUser;
    @Inject
    AuthenticationDetails authenticationDetails;

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        initObserver(view);
        initListener();
    }

    private void initListener() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                if(Utils.isInternetAvailable(getContext())){
                    authViewModel.validateLogin(
                            binding.layoutUsername.inputUsername.getText().toString().trim(),
                            binding.layoutPassword.inputPassword.getText().toString().trim());
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
                }
            }
        });
    }

    private void authenticateUser(String userId, String password) {
        progressDialog = Utils.showProgressBar(getActivity());
        prepareRequestData(userId, password);
        authViewModel.authenticateUser(envelopeAuthenticateUser).observe(this, new Observer<ResponseDataAuthenticateUser>() {
            @Override
            public void onChanged(ResponseDataAuthenticateUser responseData) {
                Utils.hideProgressDialog(progressDialog);
                if (responseData!=null) {
                    if (!responseData.getErrorResponse().isError) {
                        clearViews();
                        Helper.redirectToActivity(getActivity(), MenuActivity.class, true);
                    }else{
                        Log.e(TAG, "Exception: "+responseData.getErrorResponse().getErrorMessage());
                        Utils.showErrorMessage(getActivity(), responseData.getErrorResponse().getErrorMessageToDisplay());
                    }
                }else{
                    Log.i(TAG, "Empty result set: "+responseData.getErrorResponse().getErrorMessage());
                }
            }
        });
    }

    private void prepareRequestData(String userId, String password) {
//        authenticationDetails.setUserId("TPU010");
//        authenticationDetails.setPassword("rty123");

        authenticationDetails.setUserId(userId);
        authenticationDetails.setPassword(password);
        requestDataAuthenticateUser.setAuthenticationDetails(authenticationDetails);
        bodyAuthenticateUser.setRequestDataAuthenticateUser(requestDataAuthenticateUser);
        envelopeAuthenticateUser.setRequestBodyAuthenticateUser(bodyAuthenticateUser);
    }

    private void initObserver(View view) {
        authViewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first){
                    authenticateUser(binding.layoutUsername.inputUsername.getText().toString().trim(),
                            binding.layoutPassword.inputPassword.getText().toString().trim());
                    //Helper.redirectToActivity(getActivity(), MenuActivity.class, false);
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });
    }

    private void clearViews(){
        binding.layoutUsername.inputUsername.setText("");
        binding.layoutPassword.inputPassword.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}