package com.jlp.mvvm_jlp_project.view.route_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.jlp.mvvm_jlp_project.databinding.FragmentSummaryBinding;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.SummaryViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SummaryFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private @NonNull
    FragmentSummaryBinding binding;
    private SummaryViewModel summaryViewModel;

    public SummaryFragment() {

    }

    public static SummaryFragment newInstance(String param1, String param2) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPageParam();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        summaryViewModel = new ViewModelProvider(this).get(SummaryViewModel.class);

        initObserver(view);
        initListener();
    }


    private void initPageParam() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initObserver(View view) {

    }


    private void initListener() {
        //binding.homeTopheader.imgClose.setOnClickListener(this);

    }


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSummaryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }
}
