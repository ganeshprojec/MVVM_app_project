package com.jlp.mvvm_jlp_project.view.route_management;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.RouteDeliveryDetailsAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentRouteSummaryNewBinding;
import com.jlp.mvvm_jlp_project.interfaces.AppBarStateChangeListener;
import com.jlp.mvvm_jlp_project.interfaces.ClickListener;
import com.jlp.mvvm_jlp_project.model.DeliveryDetails;
import com.jlp.mvvm_jlp_project.utils.SpacesItemDecoration;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.RouteSummaryViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RouteSummaryFragment extends BaseFragment implements ClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private @NonNull
    FragmentRouteSummaryNewBinding binding;

    private RouteSummaryViewModel summaryViewModel;
    private RouteDeliveryDetailsAdapter adapter;
    private ArrayList<DeliveryDetails> listDeliveryDetails = new ArrayList<>();


    public RouteSummaryFragment() {

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


    private void initObserver(View view) {


    }


    private void initListener() {
        // Dummy list funciton
        listDeliveryDetails = summaryViewModel.getDummyList(getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        llm.setAutoMeasureEnabled(false);

        binding.recyList.setHasFixedSize(true);
        adapter = new RouteDeliveryDetailsAdapter(listDeliveryDetails, getActivity(), this, childListener);
        binding.recyList.setLayoutManager(llm);
        binding.recyList.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_padding_tiny);
        binding.recyList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        binding.appbarLayoutRoute.addOnOffsetChangedListener(appBarStateChangeListener);


    }


    public static RouteSummaryFragment newInstance(String param1, String param2) {
        RouteSummaryFragment fragment = new RouteSummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    private void initPageParam() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRouteSummaryNewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onClickItem(int index, Object model) {


    }

    ClickListener childListener = new ClickListener() {
        @Override
        public void onClickItem(int index, Object model) {
            // Lots click listener
        }
    };

    AppBarStateChangeListener appBarStateChangeListener = new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state) {
            if (state.name().equalsIgnoreCase(State.COLLAPSED.name())) {
                binding.secondHeader.setVisibility(View.VISIBLE);
            } else if (state.name().equalsIgnoreCase(State.EXPANDED.name())) {
                binding.secondHeader.setVisibility(View.GONE);
            }
        }
    };


}