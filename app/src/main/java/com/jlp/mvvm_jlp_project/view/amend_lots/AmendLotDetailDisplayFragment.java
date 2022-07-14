package com.jlp.mvvm_jlp_project.view.amend_lots;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentAmendLotDetailDisplayBinding;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.AmendLotDetailDisplayViewModel;
import com.jlp.mvvm_jlp_project.viewmodel.AmendLotsPrinterViewModel;

public class AmendLotDetailDisplayFragment extends BaseFragment {


      FragmentAmendLotDetailDisplayBinding binding;
     AmendLotDetailDisplayViewModel AmendLotDetailDisplayViewModel;

    public AmendLotDetailDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAmendLotDetailDisplayBinding.inflate(inflater, container, false);

        replaceFragment(new AmendLotDetailDisplayFragment());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AmendLotDetailDisplayViewModel = new ViewModelProvider(this).get(AmendLotDetailDisplayViewModel.class);
      //  initObserver();
       // initListener();
    }

    public void replaceFragment(Fragment fragment){

        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main,fragment);
        transaction.addToBackStack(getResources().getString(R.string.backstack_tag));
        transaction.commit();
    }
}