package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.databinding.ItemEnquirySingleItemBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.view.item_enquiry.CommonBarCodeLocationScannerViewHolder;

import java.util.List;

public class ItemEnquiryAdapter extends RecyclerView.Adapter<CommonBarCodeLocationScannerViewHolder> {
    private List<ItemEnquiryModel> list;
    private ItemEnquirySingleItemBinding binding;
    public ItemEnquiryAdapter(List<ItemEnquiryModel> list, Context context) {
        this.list = list;
    }

    @Override
    public CommonBarCodeLocationScannerViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemEnquirySingleItemBinding.inflate(inflater, parent, false);
        return new CommonBarCodeLocationScannerViewHolder(binding.getRoot());
    }

    @Override
    public void
    onBindViewHolder(final CommonBarCodeLocationScannerViewHolder viewHolder, final int position) {
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

