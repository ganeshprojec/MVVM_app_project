package com.jlp.mvvm_jlp_project.view.amend_lots;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentAmendLotsBarcodeScanBinding;
import com.jlp.mvvm_jlp_project.databinding.FragmentAmendLotsPrinterListBinding;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.AmendBarcodeScanViewModel;
import com.jlp.mvvm_jlp_project.viewmodel.AmendLotsPrinterViewModel;

public class AmendLotsBarcodeScanFragment extends BaseFragment {

    private static final String TAG = AmendLotsBarcodeScanFragment.class.getSimpleName();
    FragmentAmendLotsBarcodeScanBinding binding;
    AmendBarcodeScanViewModel amendBarcodeScanViewModel;
    private ProgressDialog progressDialog;

    private String printerName;

    private static final String ARG_PARAM1 = "param1";

    public AmendLotsBarcodeScanFragment() {}


    public static AmendLotsBarcodeScanFragment newInstance(String param1, String param2)
    {
        AmendLotsBarcodeScanFragment fragment = new AmendLotsBarcodeScanFragment();
        Bundle args = new Bundle();
        args.putString(AppConstants.PRINTER_NAME, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            printerName = getArguments().getString(AppConstants.PRINTER_NAME);

        }
    }



    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentAmendLotsBarcodeScanBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        amendBarcodeScanViewModel = new ViewModelProvider(this).get(AmendBarcodeScanViewModel.class);

        binding.amaendLotsTopHeader.txtToolbarTitle.setText(R.string.str_amend_lots);
       // binding.txtSelectedPrinter.setText("Selected Printer : "+printerName);
        initObserver();
        initListener();

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
        String barcode=binding.itemenquiryinputfield.inputBarcode.getText().toString().trim();

        if(TextUtils.isEmpty(barcode)){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.enter_barcode));
        }else if(barcode.length()<6){
            Utils.showErrorMessage(getActivity(), getResources().getString(R.string.invalid_barcode));
        }else{
            findDeliveryDetailsForComponentBarcode(barcode);
        }
    }

    private void findDeliveryDetailsForComponentBarcode(String barcode)
    {
           prepareRequestForDeliveryDetailsForComponentBarcode(barcode);
         //  amendBarcodeScanViewModel
    }

    private void prepareRequestForDeliveryDetailsForComponentBarcode(String barcode) {
    }

    private void clearViews() {
        binding.itemenquiryinputfield.inputBarcode.setText("");
    }  //clear input filed
    private void initObserver() {

    }

}