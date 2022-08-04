package com.jlp.mvvm_jlp_project.view.common_printer_list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.RowCommonPrinterListItemViewBinding;
import com.jlp.mvvm_jlp_project.model.PrinterDetails;
import com.jlp.mvvm_jlp_project.model.PrinterListModel;
import com.jlp.mvvm_jlp_project.utils.AppConstants;

import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonBarcodeScannerFragment;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;

import java.util.ArrayList;


public class CommonPrinterListAdapter extends RecyclerView.Adapter<CommonPrinterListViewHolder>{

    CommonPrinterListFragment fragment;

    ArrayList<PrinterDetails> printerList=new ArrayList<PrinterDetails>();
    Context ctx;
    RowCommonPrinterListItemViewBinding binding;
    String screenCall;

    public CommonPrinterListAdapter(ArrayList<PrinterDetails>printerList, Context context, CommonPrinterListFragment fragment,String screenCall1)
    {
        this.printerList = printerList;
        this.fragment = fragment;
        ctx = context;
        screenCall = screenCall1;

    }

    @NonNull
    @Override
    public CommonPrinterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding=RowCommonPrinterListItemViewBinding.inflate(inflater, parent, false);
        return new CommonPrinterListViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CommonPrinterListViewHolder viewHolder, int position) {


        int itemposition= viewHolder.getAdapterPosition()+1;
        viewHolder.printerCount.setText(ctx.getResources().getString(R.string.printer)+" "+itemposition);
        viewHolder.printerName.setText(printerList.get(position).printerName);

         Log.d("ScreenCAll",screenCall+""+printerList.get(position).printerName);

        viewHolder.itemView.setOnClickListener(view -> {

            CommonBarcodeScannerFragment fragment2 = new CommonBarcodeScannerFragment(screenCall);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.PRINTER_NAME, printerList.get(position).printerName);
            bundle.putString(AppConstants.PRINTER_ID, printerList.get(position).printerId);
            fragment2.setArguments(bundle);
            FragmentManager fragmentManager =((fragment.getActivity())).getSupportFragmentManager(); //((AppCompatActivity)ctx).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container_main, fragment2);
            fragmentTransaction.addToBackStack("TAG");
            fragmentTransaction.commit();

        });

    }

    @Override
    public int getItemCount() {
        return printerList.size();
    }
}
