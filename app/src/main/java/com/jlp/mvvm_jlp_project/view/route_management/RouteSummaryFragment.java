package com.jlp.mvvm_jlp_project.view.route_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.jlp.mvvm_jlp_project.databinding.FragmentRouteSummaryBinding;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.RouteSummaryViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RouteSummaryFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View rootView;
    ImageView imgClose, imgCloseSecond;
    private String mParam1;
    private String mParam2;
    private @NonNull
    FragmentRouteSummaryBinding binding;
    private RouteSummaryViewModel summaryViewModel;

    public RouteSummaryFragment() {

    }

    public static RouteSummaryFragment newInstance(String param1, String param2) {
        RouteSummaryFragment fragment = new RouteSummaryFragment();
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
        summaryViewModel = new ViewModelProvider(this).get(RouteSummaryViewModel.class);

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

    }


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRouteSummaryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_route_summary, container, false);
        imgClose = (ImageView) rootView.findViewById(R.id.imgClose);
        imgCloseSecond = (ImageView) rootView.findViewById(R.id.imgCloseSecond);

        return rootView;
    }*/

}