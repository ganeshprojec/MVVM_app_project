package com.jlp.mvvm_jlp_project.view.carrier_handover_details;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.CommonAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCarrierHandoverDetailsBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CarrierHandoverDetailsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CarrierCollectionAndHandoverDetailsFragment extends BaseFragment{
    private static final String TAG = LoginFragment.class.getSimpleName();
    private CarrierHandoverDetailsViewModel viewModel;
    private FragmentCarrierHandoverDetailsBinding binding;

    // Used for adapter and list to setup
    private CommonAdapter adapter;
    private List<TitleValueDataModel> titleValueDataList =  new ArrayList<>();

    private String callFor;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public CarrierCollectionAndHandoverDetailsFragment(String callfor) {
        this.callFor = callfor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CarrierHandoverDetailsViewModel.class);
        updateActionbarAndButtonText();
        setupAdapter();
        initListener();
        initObserver();
    }

    private void initObserver() {
        viewModel.itemDelivery.observe(getViewLifecycleOwner(), new Observer<List<TitleValueDataModel>>() {
            @Override
            public void onChanged(List<TitleValueDataModel> titleValueDataModels) {
                titleValueDataList.addAll(titleValueDataModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initListener() {
        binding.dropDownOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etOn);
            }
        });
        
        binding.etOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etOn);
            }
        });

        binding.dropDownDeliveryTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etDeliveryTo);
            }
        });

        binding.etDeliveryTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDateAndSetToView(binding.etDeliveryTo);
            }
        });

        binding.etLatestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTimeAndSetToView(binding.etLatestDelivery);
            }
        });

        binding.dropDownLatestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pickTimeAndSetToView(binding.etLatestDelivery);
            }
        });

        binding.dropUpLatestDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pickTimeAndSetToView(binding.etLatestDelivery);
            }
        });
    }

    private void pickTimeAndSetToView(EditText etLatestDelivery) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String hrs = String.valueOf(hourOfDay);
                        String min = String.valueOf(minute);
                        if(hourOfDay<=9) hrs = "0"+hrs;
                        if(minute<=9) min = "0"+min;
                        etLatestDelivery.setText(hrs + ":" + min);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void pickDateAndSetToView(EditText etOn) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        etOn.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        // Select future date only
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    /**
     * Set up the adapter and set the data for list items
     */
    private void setupAdapter(){
        viewModel.getItemDetailsDeliveryBarcodeData();
        adapter = new CommonAdapter(titleValueDataList, getContext());
        binding.recyclerViewSubHeader.setAdapter(adapter);
        binding.recyclerViewSubHeader.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateActionbarAndButtonText() {
        binding.homeTopHeader.txtToolbarTitle.setText(CommonBarcodeScannerFragment.actionBarTitle);
        switch (callFor){
            case AppConstants.FRAGMENT_CARRIER_COLLECTION_DETAILS:{
                binding.btnConfirm.setText(getResources().getText(R.string.confirm));
                updateOnLayout(View.GONE);
                break;
            }
            case AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS:{
                binding.btnConfirm.setText(getResources().getText(R.string.next));
                updateOnLayout(View.VISIBLE);
                break;
            }
        }
    }

    private void updateOnLayout(int visibility) {
        binding.layoutOnWithTvAndEt.setVisibility(visibility);
        binding.divider1.divider.setVisibility(visibility);
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarrierHandoverDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}