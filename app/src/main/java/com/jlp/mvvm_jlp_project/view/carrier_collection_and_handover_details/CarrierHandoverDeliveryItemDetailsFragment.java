package com.jlp.mvvm_jlp_project.view.carrier_collection_and_handover_details;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.CommonAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCarrierHandoverDeliveryItemDetailsBinding;
import com.jlp.mvvm_jlp_project.databinding.FragmentCarrierHandoverDetailsBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.DeliveryGoodsDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestBodyCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_component_handover_details.RequestEnvelopeCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.CreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestBodyCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.request.create_or_update_handover_details.RequestEnvelopeCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ComponentDetails;
import com.jlp.mvvm_jlp_project.model.response.create_component_handover_details.ResponseDataCreateComponentHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.create_or_update_handover_details.ResponseDataCreateOrUpdateHandoverDetails;
import com.jlp.mvvm_jlp_project.model.response.find_deliveries_and_delivery_items.ResponseDataFindDeliveriesAndDeliveryItems;
import com.jlp.mvvm_jlp_project.model.response.find_handover_details.FoundHandoverDetails;
import com.jlp.mvvm_jlp_project.pref.AppPreferencesHelper;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CarrierCollectionAndHandoverDetailsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CarrierHandoverDeliveryItemDetailsFragment extends BaseFragment{
    private static final String TAG = CarrierHandoverDeliveryItemDetailsFragment.class.getSimpleName();
    private CarrierCollectionAndHandoverDetailsViewModel viewModel;
    private FragmentCarrierHandoverDeliveryItemDetailsBinding binding;
    private ProgressDialog progressDialog;

    private CommonAdapter adapter;
    private List<TitleValueDataModel> titleValueDataList =  new ArrayList<>();

    @Inject
    AppPreferencesHelper appPreferencesHelper;

    @Inject
    RequestEnvelopeCreateComponentHandoverDetails requestEnvelopeCreateComponentHandoverDetails;
    @Inject
    RequestBodyCreateComponentHandoverDetails requestBodyCreateComponentHandoverDetails;
    @Inject
    RequestDataCreateComponentHandoverDetails requestDataCreateComponentHandoverDetails;

    List<DeliveryGoodsDetails> deliveryGoodsDetails = new ArrayList<>();

    List<ComponentDetails> componentDetails = new ArrayList<>();

    public CarrierHandoverDeliveryItemDetailsFragment() {}

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarrierHandoverDeliveryItemDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CarrierCollectionAndHandoverDetailsViewModel.class);
        updateActionbar();
        setupAdapter();
        initListener();
        initObserver();
    }

    private void initObserver() {
        viewModel.itemDelivery.observe(getViewLifecycleOwner(), new Observer<List<TitleValueDataModel>>() {
            @Override
            public void onChanged(List<TitleValueDataModel> titleValueDataModels) {
                titleValueDataList.addAll(titleValueDataModels);
                Collections.reverse(titleValueDataList);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first){
                    if (Utils.isInternetAvailable(getContext())){
                        prepareRequestDataForCreateOrUpdateHandoverDetails();
                        viewModel.createComponentHandoverDetails(requestEnvelopeCreateComponentHandoverDetails);
                    }else{
                        Utils.showErrorMessage(getActivity(), getResources().getString(R.string.please_check_internet_connection));
                    }
                }else{
                    Utils.showErrorMessage(getActivity(), getResources().getString(validationResult.second));
                }
            }
        });

        viewModel.responseDataCreateComponentHandoverDetails.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataCreateComponentHandoverDetails>>() {
            @Override
            public void onChanged(Resource<ResponseDataCreateComponentHandoverDetails> response) {
                if (response.status != null) {
                    switch (response.status) {
                        case LOADING: {
                            progressDialog = Utils.showProgressBar(getContext());
                            break;
                        }
                        case ERROR: {
                            Utils.hideProgressDialog(progressDialog);
                            Utils.showErrorMessage(getActivity(), response.message);
                            break;
                        }
                        case SUCCESS: {
                            clearViews();
                            Utils.hideProgressDialog(progressDialog);
                            if(response.data.getCreatedComponentHandoverDetails()!=null){
                                replaceFragment(new CommonBarcodeScannerFragment(AppConstants.FRAGMENT_CARRIER_HANDOVER_DETAILS));
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    private void clearViews() {
    }

    private void initListener() {
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.hideKeyboard(getActivity(), view);
                // TODO: need to change later
                viewModel.addItemDetailsInRecycler(titleValueDataList.size()+1, binding.layoutItemEnquiryInputField.inputBarcode.getText().toString().trim());
            }
        });
    }

    private void prepareRequestDataForCreateOrUpdateHandoverDetails() {}

    /**
     * Set up the adapter and set the data for list items
     */
    private void setupAdapter(){
        adapter = new CommonAdapter(titleValueDataList, getContext());
        binding.recyclerViewDeliveryItemDetails.setAdapter(adapter);
        binding.recyclerViewDeliveryItemDetails.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateActionbar() {
        binding.homeTopHeader.txtToolbarTitle.setText(CommonBarcodeScannerFragment.actionBarTitle);
    }

    // TODO: NavController we have to use for this
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment); //main_fragment_container
        transaction.addToBackStack(TAG);
        transaction.commit();
    }
}