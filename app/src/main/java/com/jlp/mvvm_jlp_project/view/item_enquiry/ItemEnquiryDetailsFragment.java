package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.adapters.ItemEnquiryAdapter;
import com.jlp.mvvm_jlp_project.databinding.FragmentItemEnquiryDetailsBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.DeliveryItemProductDetails;
import com.jlp.mvvm_jlp_project.model.response.find_delivery_details_for_component_barcode.ResponseDataFindDeliveryDetailsForComponentBarcode;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.view.auth.LoginFragment;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.viewmodel.ItemEnquiryViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

//TODO:Use viewModel for data manipulation, Manage condotions to handle the data
@AndroidEntryPoint
public class ItemEnquiryDetailsFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();
    ResponseDataFindDeliveryDetailsForComponentBarcode response;
    private FragmentItemEnquiryDetailsBinding binding;
    private ItemEnquiryViewModel itemEnquiryViewModel;

    public ItemEnquiryDetailsFragment() {}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            if (getArguments() != null) {
                Bundle bundle = this.getArguments();
                if (bundle != null) {
                    response = bundle.getParcelable(AppConstants.ITEM_ENQUIRY_DETAILS_DATA);
                }
            }
        }catch (Exception ex){
            Log.e(TAG, "Exception: "+ex.getMessage());
        }
        itemEnquiryViewModel = new ViewModelProvider(this).get(ItemEnquiryViewModel.class);

        List<ItemEnquiryModel> list = new ArrayList<>();
        list = getData(response.getDeliveryItemProductDetails());
        ItemEnquiryAdapter adapter;
        adapter
                = new ItemEnquiryAdapter(
                list, getContext());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemEnquiryDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onClick(View view){
        Fragment fragment=null;
        switch(view.getId()){
            case R.id.imgnextscan:
                fragment=new ItemEnquiryFragment();
                replaceFragment(fragment);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment){
        FragmentTransaction transaction= getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_main,someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public List<ItemEnquiryModel> getData(DeliveryItemProductDetails itemEnquiryDetailsData)
    {
        List<ItemEnquiryModel> list = new ArrayList<>();
        list.add(new ItemEnquiryModel("Delivery Number",
                itemEnquiryDetailsData.getDeliveryId()
        ));
        list.add(new ItemEnquiryModel("Route Number",
                itemEnquiryDetailsData.getRouteResourceKey()
        ));
        list.add(new ItemEnquiryModel("Delivery Date",
                itemEnquiryDetailsData.getDeliveryDate()));
        list.add(new ItemEnquiryModel("Last Recorded Location",
                itemEnquiryDetailsData.getDeliveryAddressPremise()));
        list.add(new ItemEnquiryModel("Time of last move",
                itemEnquiryDetailsData.getLastUpdatedTimeStamp()));
        list.add(new ItemEnquiryModel("Last UserId",
                itemEnquiryDetailsData.getLastUpdatedUserId()));
        list.add(new ItemEnquiryModel("Product Code",
                itemEnquiryDetailsData.getProductCode()));
        list.add(new ItemEnquiryModel("Product description",
                itemEnquiryDetailsData.getOrderDescriptionClean()));
        list.add(new ItemEnquiryModel("Lot Number",
                itemEnquiryDetailsData.getCurrentLotNumber()));
        list.add(new ItemEnquiryModel("Address",
                itemEnquiryDetailsData.getDeliveryAddressLocality()));

        return list;
    }


}