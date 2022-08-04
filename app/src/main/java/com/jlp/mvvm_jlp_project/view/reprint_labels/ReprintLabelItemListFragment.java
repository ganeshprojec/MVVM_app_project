package com.jlp.mvvm_jlp_project.view.reprint_labels;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.CommonBarcodeScannerAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentCommonPrinterListBinding;
import com.jlp.mvvm_jlp_project.databinding.FragmentReprintLabelItemListBinding;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProductDetails;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.ReprintLabelDetails;
import com.jlp.mvvm_jlp_project.model.ReprintLableItemModel;
import com.jlp.mvvm_jlp_project.model.request.record_location_of_item.LocationItemDetails;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.ReprintLabelDetailsReq;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestBodyReprintLabel;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestDataReprintLabel;
import com.jlp.mvvm_jlp_project.model.request.reprint_label_detail.RequestEnvelopeReprintLabel;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_good_product.ResponseDataFindDeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_item_details_for_component_barcode.DeliveryItemDetails;
import com.jlp.mvvm_jlp_project.model.response.reprint_label_detail.ResponseDataReprintLabel;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Resource;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.view.common_printer_list.CommonPrinterListFragment;
import com.jlp.mvvm_jlp_project.viewmodel.CommonPrinterViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReprintLabelItemListFragment extends BaseFragment {

    ReprintLabelItemListViewModel reprintLabelItemListViewModel;
    FragmentReprintLabelItemListBinding binding;
   //private DeliveryGoodProduct deliveryGoodProduct = CommonPrinterListFragment.;

    String callFor;
    String deliveryNumber,printerID;
    private ProgressDialog progressDialog;
    private ReprintLabelAdapter adapter;
    private List<DeliveryGoodProduct> detailsgoodProductList =  new ArrayList<>();

    public static ReprintLabelDetails reprintLabelDetails;

    public ReprintLabelItemListFragment(String callFor,String deliveryNumber,String printerID) {
        this.callFor = callFor;
        this.deliveryNumber = deliveryNumber;
        this.printerID = printerID;
    }


    @Inject
    RequestBodyReprintLabel requestBodyReprintLabel;

    @Inject
    RequestDataReprintLabel requestDataReprintLabel;

    @Inject
    RequestEnvelopeReprintLabel requestEnvelopeReprintLabel;

    @Inject
    DeliveryGoodProduct deliveryGoodProduct;

    @Inject
    PrinterDetails printerDetails;

    @Inject
    ReprintLabelDetailsReq reprintLabelDetailsReq;


    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReprintLabelItemListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reprintLabelItemListViewModel = new ViewModelProvider(this).get(ReprintLabelItemListViewModel.class);


        Log.d("RERERERER",callFor +""+deliveryNumber);
        updateActionbarTitle();
        initObserver();
        initListener();

        setupAdapter();

    }



    private void updateActionbarTitle()
    {
        if(callFor.equals(AppConstants.FRAGMENT_REPRINT_LABELS)) {
            binding.itemEnquiryHeader.txtToolbarTitle.setText(R.string.str_reprint_labels);

            binding.itemEnquiryHeader.imgMultiSelect.setVisibility(View.VISIBLE);

        }
    }


    private void setupAdapter(){

        detailsgoodProductList.addAll(CommonBarcodeScannerFragment.deliveryGoodProductDetails.deliveryGoodProductDetails);
        if(detailsgoodProductList!=null){
            adapter = new ReprintLabelAdapter(detailsgoodProductList, getContext());
            binding.recyclerViewReprintLabel.setAdapter(adapter);
            binding.recyclerViewReprintLabel.setLayoutManager(new LinearLayoutManager(getContext()));
        }else{
            Utils.showErrorMessage(getActivity(), "Unable to get the data");
        }
    }


    private void initObserver() {
        reprintLabelItemListViewModel.reprintLableItemModeldata.observe(getViewLifecycleOwner(), new Observer<List<DeliveryGoodProduct>>() {
            @Override
            public void onChanged(List<DeliveryGoodProduct> reprintLableItemModel) {
                detailsgoodProductList.addAll(reprintLableItemModel);
                adapter.notifyDataSetChanged();
            }
        });

        //response after printer button click

        reprintLabelItemListViewModel.responseDataReprintLabel.observe(getViewLifecycleOwner(), new Observer<Resource<ResponseDataReprintLabel>>() {
            @Override
            public void onChanged(Resource<ResponseDataReprintLabel> response) {
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
                            Utils.hideProgressDialog(progressDialog);
                            reprintLabelDetails=response.data.getReprintLabelDetails();
                            Utils.showAmendAlertDialog(getActivity(),deliveryNumber,reprintLabelDetails.getLabelsPrinted(),reprintLabelDetails.getPrinterId(),callFor);
                            break;
                        }
                    }
                }
            }
        });
    }



    private void initListener()
    {
        //For MultiSelect Item from List


        binding.itemEnquiryHeader.imgMultiSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.itemEnquiryHeader.imgMultiSelect.isChecked())
                {
                    //  adapter.visibility(new ReprintLabelViewHolder(view));

                    for (DeliveryGoodProduct model : detailsgoodProductList) {
                        model.setSelected(true);
                    }

                }
                else {

                    for (DeliveryGoodProduct model : detailsgoodProductList) {
                        model.setSelected(false);
                    }
                }
              //  binding.recyclerViewReprintLabel.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


        //To Print Label
        binding.btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // List<DeliveryGoodProduct> temp=new ArrayList<>();
                for (int i = 0; i < ReprintLabelAdapter.list.size(); i++)
                {
                    if(ReprintLabelAdapter.list.get(i).isSelected())
                    {
                       // Toast.makeText(getActivity(),ReprintLabelAdapter.list.get(i).getDeliveryGoodId(), Toast.LENGTH_SHORT).show();
                    }
                    findReprintLabelDetail(printerDetails,ReprintLabelAdapter.list.get(i).getDeliveryGoodId(),deliveryGoodProduct);
                }

            }
        });
    }

    private void findReprintLabelDetail(PrinterDetails printerDetails, String dgoodID, DeliveryGoodProduct deliveryGoodProduct)
    {
        prepareRequestDataForFindReprintLabelDetail(printerDetails,dgoodID,deliveryGoodProduct);
        reprintLabelItemListViewModel.findReprintLabelDetail(requestEnvelopeReprintLabel);
    }

    private void prepareRequestDataForFindReprintLabelDetail(PrinterDetails printerDetails, String p, DeliveryGoodProduct deliveryGoodProduct)
    {
        if(deliveryGoodProduct!=null && printerDetails!=null )
        {
            reprintLabelDetailsReq.setPrinterId(printerDetails.getPrinterId());
            reprintLabelDetailsReq.setDeliveryGoodId(deliveryGoodProduct.getDeliveryGoodId());
            reprintLabelDetailsReq.setDeliveryId(deliveryGoodProduct.getDeliveryId());

        }
        requestDataReprintLabel.setReprintLabelDetails(reprintLabelDetailsReq);
        requestBodyReprintLabel.setRequestDataReprintLabel(requestDataReprintLabel);
        requestEnvelopeReprintLabel.setRequestBodyReprintLabel(requestBodyReprintLabel);
    }



    private ArrayList<DeliveryGoodProduct> getModel(boolean isSelect){
        ArrayList<DeliveryGoodProduct> list = new ArrayList<>();

        for(int i = 0; i < list.size(); i++)
        {

            DeliveryGoodProduct model = new DeliveryGoodProduct();
            model.setSelected(isSelect);
            model.setDeliveryGoodId(list.get(i).getDeliveryGoodId());
            model.setDeliveryId(list.get(i).getDeliveryId());
            model.setProductCode(list.get(i).getProductCode());
            model.setOrderDescriptionClean(list.get(i).getOrderDescriptionClean());
            list.add(model);
        }
        return list;
    }

}