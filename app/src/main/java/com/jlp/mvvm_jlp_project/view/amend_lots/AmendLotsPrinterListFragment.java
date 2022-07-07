package com.jlp.mvvm_jlp_project.view.amend_lots;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentAmendLotsPrinterListBinding;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.AmendLotsPrinterViewModel;

public class AmendLotsPrinterListFragment extends BaseFragment {

    private FragmentAmendLotsPrinterListBinding binding;
    private AmendLotsPrinterViewModel amendPrinterViewModle;
    private ProgressDialog progressDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public AmendLotsPrinterListFragment(){}

    public static AmendLotsPrinterListFragment newInstance(String param1, String param2) {
        AmendLotsPrinterListFragment fragment = new AmendLotsPrinterListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amendPrinterViewModle = new ViewModelProvider(this).get(AmendLotsPrinterViewModel.class);
        initListener();
        initObserver();
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAmendLotsPrinterListBinding.inflate(inflater, container, false);
        replaceFragment(new AmendLotsPrinterListFragment() );
        return binding.getRoot();
    }



    private void initListener() {
        binding.itemMovementTopHeader.txtToolbarTitle.setText(R.string.str_amend_lots);  //appbar title set

    }

    private void initObserver() {

    }
    public void replaceFragment(Fragment fragment){

        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main,fragment);
        transaction.addToBackStack(getResources().getString(R.string.backstack_tag));
        transaction.commit();
    }



}