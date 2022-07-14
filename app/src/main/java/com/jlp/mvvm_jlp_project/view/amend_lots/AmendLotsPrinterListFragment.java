package com.jlp.mvvm_jlp_project.view.amend_lots;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.ItemEnquiryAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentAmendLotsPrinterListBinding;
import com.jlp.mvvm_jlp_project.model.PrinterListModel;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.item_movement.ItemMovementLocationBarcodeFragment;
import com.jlp.mvvm_jlp_project.viewmodel.AmendLotsPrinterViewModel;

public class AmendLotsPrinterListFragment extends BaseFragment {

    private FragmentAmendLotsPrinterListBinding binding;
    private AmendLotsPrinterViewModel amendPrinterViewModle;
    private ProgressDialog progressDialog;


    public AmendLotsPrinterListFragment(){}


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAmendLotsPrinterListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amendPrinterViewModle = new ViewModelProvider(this).get(AmendLotsPrinterViewModel.class);
        binding.amaendLotsTopHeader.txtToolbarTitle.setText(R.string.str_amend_lots);
        initObserver();
        initListener();
        initPrinterList();
    }

    private void initPrinterList()
    {
        AmendLotsPrinterAdapter printeradapter;
        PrinterListModel[] priinterListData = new PrinterListModel[] {
                new PrinterListModel("Printer 1","East Bank 1"),
                new PrinterListModel("Printer 2","East Bank2"),
                new PrinterListModel("Printer 3","East Bank3"),
                new PrinterListModel("Printer 4","East Bank4"),
                new PrinterListModel("Printer 5","East Bank5"),
                new PrinterListModel("Printer 6","East Bank6"),
                new PrinterListModel("Printer 7","East Bank7"),
                new PrinterListModel("Printer 8","East Bank8"),
                new PrinterListModel("Printer 9","East Bank9"),
             };
        printeradapter = new AmendLotsPrinterAdapter(priinterListData, getContext(),AmendLotsPrinterListFragment.this);
        binding.recyclerViewAmendLotPrinterList.setAdapter(printeradapter);
        binding.recyclerViewAmendLotPrinterList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initListener() {}

    private void initObserver() {}




}