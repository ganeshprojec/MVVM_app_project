package com.jlp.mvvm_jlp_project.view.amend_lots;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.RowAmendLotsItemViewPrinterBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.model.PrinterListModel;
import com.jlp.mvvm_jlp_project.utils.AppConstants;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;

import java.util.List;

public class AmendLotsPrinterAdapter extends RecyclerView.Adapter<AmendPrinterListViewHolder>
{

    AmendLotsPrinterListFragment fragment;
    private PrinterListModel[] list;
    private RowAmendLotsItemViewPrinterBinding binding;
    Context ctx;
    public AmendLotsPrinterAdapter(PrinterListModel[] list, Context context,AmendLotsPrinterListFragment fragment)
    {
        this.list = list;
        this.fragment = fragment;
        ctx = context;

    }

    @NonNull
    @Override
    public AmendPrinterListViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding=RowAmendLotsItemViewPrinterBinding.inflate(inflater, parent, false);
        return new AmendPrinterListViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull AmendPrinterListViewHolder viewHolder, int position) {

        final PrinterListModel myListData = list[position];
        viewHolder.printerCount.setText(list[position].getPrinterCount());
        viewHolder.printerName.setText(list[position].getPrinterName());


        viewHolder.itemView.setOnClickListener(view -> {

            AmendLotsBarcodeScanFragment fragment2 = new AmendLotsBarcodeScanFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.PRINTER_NAME, list[position].getPrinterName());
            fragment2.setArguments(bundle);

            FragmentManager fragmentManager =((HomeActivity)ctx).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container_main, fragment2);
            fragmentTransaction.commit();

        });

    }

   /* private void setPrinterNameToFragment(AmendLotsBarcodeScanFragment amendLotsBarcodeScanFragment, String printerName)
    {

        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.PRINTER_NAME, printerName);
        amendLotsBarcodeScanFragment.setArguments(bundle);
        replaceFragment(amendLotsBarcodeScanFragment);
    }
    private void replaceFragment(AmendLotsBarcodeScanFragment fragment1){

        FragmentManager manager = ((AppCompatActivity)ctx).getSupportFragmentManager();
      //  FragmentTransaction transaction = manager.getSupportFragmentManager()//((FragmentActivity)ctx).getSupportFragmentManager().beginTransaction();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_container_main, fragment1); //main_fragment_container
       // transaction.addToBackStack("TAG");
        transaction.commit();

    }*/

    @Override
    public int getItemCount() {
        return list.length;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
