package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.databinding.CommonBarcodeScannerSingleItemBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.view.item_enquiry.CommonBarcodeLocationScannerViewHolder;

import java.util.List;

public class CommonBarcodeScannerAdapter extends RecyclerView.Adapter<CommonBarcodeLocationScannerViewHolder> {
    private List<ItemEnquiryModel> list;
    private CommonBarcodeScannerSingleItemBinding binding;
    public CommonBarcodeScannerAdapter(List<ItemEnquiryModel> list, Context context) {
        this.list = list;
    }

    @Override
    public CommonBarcodeLocationScannerViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = CommonBarcodeScannerSingleItemBinding.inflate(inflater, parent, false);
        return new CommonBarcodeLocationScannerViewHolder(binding.getRoot());
    }

    @Override
    public void
    onBindViewHolder(final CommonBarcodeLocationScannerViewHolder viewHolder, final int position) {
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.value.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

