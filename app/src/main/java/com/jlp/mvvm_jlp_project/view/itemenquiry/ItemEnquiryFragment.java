package com.jlp.mvvm_jlp_project.view.itemenquiry;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentItemEnquiryBinding;
import com.jlp.mvvm_jlp_project.databinding.FragmentLoginBinding;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.AuthenticationDetails;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestBodyAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.RequestEnvelopeAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestBodyFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestBodyFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.home.MenuActivity;
import com.jlp.mvvm_jlp_project.view.home.TemplateFragment;
import com.jlp.mvvm_jlp_project.viewmodel.AuthViewModel;
import com.jlp.mvvm_jlp_project.viewmodel.ItemEnquiryViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ItemEnquiryFragment extends BaseFragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog progressDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentItemEnquiryBinding binding;
    private ItemEnquiryViewModel itemEnquiryViewModel;

    @Inject
    RequestEnvelopeFindDeliveryDetailsForComponentBarcode requestEnvelopeFindDeliveryDetailsForComponentBarcode;
    @Inject
    RequestBodyFindDeliveryDetailsForComponentBarcode requestBodyFindDeliveryDetailsForComponentBarcode;
    @Inject
    RequestDataFindDeliveryDetailsForComponentBarcode requestDataFindDeliveryDetailsForComponentBarcode;

    public ItemEnquiryFragment() {}

    public static TemplateFragment newInstance(String param1, String param2) {
        TemplateFragment fragment = new TemplateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemEnquiryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemEnquiryViewModel = new ViewModelProvider(this).get(ItemEnquiryViewModel.class);
        initObserver();
        initListener();
    }

    private void initObserver() {
        itemEnquiryViewModel.responseFindDeliveryDetailsForComponentBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindDeliveryDetailsForComponentBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindDeliveryDetailsForComponentBarcode> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING:{
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }

                        case ERROR:{
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }

                        case SUCCESS:{
                            clearViews();
                            Toast.makeText(getContext(), response.data.deliveryItemProductDetails.getCurrentLotNumber()+
                                    "-"+response.data.deliveryItemProductDetails.currentLotNumber, Toast.LENGTH_LONG).show();
                            Utils.hideProgressDialog(progressDialog);
                            Helper.redirectToActivity(getActivity(), MenuActivity.class, true);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void clearViews() {
        binding.itemenquiryinputfield.inputBarcode.setText("");
    }

    private void initListener() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                validate();
            }
        });
    }

    private void validate() {
        Fragment fragment=null;
        String barcode = binding.itemenquiryinputfield.inputBarcode.getText().toString().trim();
        if(TextUtils.isEmpty(barcode)){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.enter_barcode));
        }else if(barcode.length()<6){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.invalid_barcode));
        }else{
            findLocationDetailsForBarcode(barcode);
//            fragment=new com.jlp.mvvm_jlp_project.view.itemenquiry.ItemEnquiryDisplayFragment();
//            replaceFragment(fragment);
        }
    }

    private void findLocationDetailsForBarcode(String barcode) {
        prepareRequestData(barcode);
        itemEnquiryViewModel.findDeliveryDetailsForComponentBarcode(requestEnvelopeFindDeliveryDetailsForComponentBarcode);
    }

    private void prepareRequestData(String barcode) {
        requestDataFindDeliveryDetailsForComponentBarcode.setBarcode(barcode);
        requestBodyFindDeliveryDetailsForComponentBarcode.setRequestDataFindDeliveryDetailsForComponentBarcode(requestDataFindDeliveryDetailsForComponentBarcode);
        requestEnvelopeFindDeliveryDetailsForComponentBarcode.setRequestBodyFindDeliveryDetailsForComponentBarcode(requestBodyFindDeliveryDetailsForComponentBarcode);
    }
}