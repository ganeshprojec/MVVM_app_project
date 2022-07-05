package com.jlp.mvvm_jlp_project.view.itemmovement;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentItemMovementBinding;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestBodyFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestBodyFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_location_details_for_barcode.RequestEnvelopeFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_location_details_for_barcode.ResponseDataFindLocationDetailsForBarcode;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.item_enquiry.ItemEnquiryDetailsFragment;
import com.jlp.mvvm_jlp_project.viewmodel.ItemMovementViewModel;

import javax.inject.Inject;

public class ItemMovementFragment extends BaseFragment {
    private FragmentItemMovementBinding binding;
    private ItemMovementViewModel itemMovementViewModel;
    private ProgressDialog progressDialog;

    @Inject
    RequestEnvelopeFindLocationDetailsForBarcode requestEnvelopeFindLocationDetailsForBarcode;
    @Inject
    RequestBodyFindLocationDetailsForBarcode requestBodyFindLocationDetailsForBarcode;
    @Inject
    RequestDataFindLocationDetailsForBarcode requestDataFindLocationDetailsForBarcode;

    public ItemMovementFragment() {}

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemMovementBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemMovementViewModel = new ViewModelProvider(this).get(ItemMovementViewModel.class);
        initListener();
        initObserver();
    }

    private void initObserver() {
        itemMovementViewModel.responseFindLocationDetailsForBarcode.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataFindLocationDetailsForBarcode>>() {
            @Override
            public void onChanged(Resource<ResponseDataFindLocationDetailsForBarcode> response) {
                if(response.status != null){
                    switch (response.status){
                        case LOADING: {
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
                            Utils.hideProgressDialog(progressDialog);
                            replaceFragment(new ItemEnquiryDetailsFragment(), response.data);
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
        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                redirect();
            }
        });
    }

    private void findLocationDetailsForBarcode(String barcode) {
        prepareRequestData(barcode);
        itemMovementViewModel.findLocationDetailsForBarcode(requestEnvelopeFindLocationDetailsForBarcode);
    }

    private void prepareRequestData(String barcode) {
        requestDataFindLocationDetailsForBarcode.setBarcode(barcode);
        requestBodyFindLocationDetailsForBarcode.setRequestDataFindLocationDetailsForBarcode(requestDataFindLocationDetailsForBarcode);
        requestEnvelopeFindLocationDetailsForBarcode.setRequestBodyFindLocationDetailsForBarcode(requestBodyFindLocationDetailsForBarcode);
    }

    private void redirect() {
        if(TextUtils.isEmpty(binding.itemenquiryinputfield.inputBarcode.getText().toString().trim())){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.enter_barcode));
        }else if(binding.itemenquiryinputfield.inputBarcode.getText().toString().trim().length()<6){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.invalid_barcode));
        }else{
            findLocationDetailsForBarcode(binding.itemenquiryinputfield.inputBarcode.getText().toString().trim());
        }
    }

    public void replaceFragment(Fragment someFragment, ResponseDataFindLocationDetailsForBarcode data){
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main,someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}