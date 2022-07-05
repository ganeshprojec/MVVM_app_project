package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.ItemEnquiryModel;
import com.jlp.mvvm_jlp_project.view.item_enquiry.ItemEnquiryViewHolder;

import java.util.List;

public class ItemEnquiryAdapter extends RecyclerView.Adapter<ItemEnquiryViewHolder> {
    List<ItemEnquiryModel> list;
    Context context;
    public ItemEnquiryAdapter(List<ItemEnquiryModel> list,
                              Context context)
    {
        this.list = list;
        this.context = context;

    }

    @Override
    public ItemEnquiryViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.item_enquiry_display_cards,
                        parent, false);

        ItemEnquiryViewHolder viewHolder
                = new ItemEnquiryViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final ItemEnquiryViewHolder viewHolder,
                     final int position)
    {
        //final ItemEnquiryModel myListData = list(position);

        viewHolder.deliverynumber
                .setText(list.get(position).getDeliveryNumber());
        viewHolder.deliverynumber1
                .setText(list.get(position).getDeliveryNumbers());


    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

