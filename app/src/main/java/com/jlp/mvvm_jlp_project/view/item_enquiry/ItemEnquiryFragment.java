package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentItemEnquiryBinding;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestBodyFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.request.find_delivery_details_for_component_barcode.RequestEnvelopeFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.home.TemplateFragment;
import com.jlp.mvvm_jlp_project.viewmodel.ItemEnquiryViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ItemEnquiryFragment extends BaseFragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
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
        binding.itemenquiryinputfield.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                validate();
            }
        });


    }

    private void validate() {
        String barcode = binding.itemenquiryinputfield.inputBarcode.getText().toString().trim();
        if(TextUtils.isEmpty(barcode)){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.enter_barcode));
        }else if(barcode.length()<6){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.invalid_barcode));
        }else{
            findDeliveryDetailsForComponentBarcode(barcode);
        }
    }

    private void findDeliveryDetailsForComponentBarcode(String barcode) {
        prepareRequestData(barcode);
        itemEnquiryViewModel.findDeliveryDetailsForComponentBarcode(requestEnvelopeFindDeliveryDetailsForComponentBarcode);
    }

    private void prepareRequestData(String barcode) {
        requestDataFindDeliveryDetailsForComponentBarcode.setBarcode(barcode);
        requestBodyFindDeliveryDetailsForComponentBarcode.setRequestDataFindDeliveryDetailsForComponentBarcode(requestDataFindDeliveryDetailsForComponentBarcode);
        requestEnvelopeFindDeliveryDetailsForComponentBarcode.setRequestBodyFindDeliveryDetailsForComponentBarcode(requestBodyFindDeliveryDetailsForComponentBarcode);
    }

    public void replaceFragment(Fragment fragment,
                                ResponseDataFindDeliveryDetailsForComponentBarcode responseData){
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.ITEM_ENQUIRY_DETAILS_DATA, responseData);
        fragment.setArguments(bundle);
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main,fragment);
        transaction.addToBackStack(getResources().getString(R.string.backstack_tag));
        transaction.commit();
    }
}