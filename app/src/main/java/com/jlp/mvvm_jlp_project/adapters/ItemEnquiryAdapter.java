package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.databinding.ItemEnquirySingleItemBinding;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.view.item_enquiry.ItemEnquiryViewHolder;

import java.util.List;

public class ItemEnquiryAdapter extends RecyclerView.Adapter<ItemEnquiryViewHolder> {
    private List<ItemEnquiryModel> list;
    private ItemEnquirySingleItemBinding binding;
    public ItemEnquiryAdapter(List<ItemEnquiryModel> list, Context context) {
        this.list = list;
    }

    @Override
    public ItemEnquiryViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemEnquirySingleItemBinding.inflate(inflater, parent, false);
        return new ItemEnquiryViewHolder(binding.getRoot());
    }

    @Override
    public void
    onBindViewHolder(final ItemEnquiryViewHolder viewHolder, final int position) {
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

