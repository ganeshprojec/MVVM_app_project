package com.jlp.mvvm_jlp_project.view.item_movement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.model.ItemMovementModel;

import java.util.List;

public class ItemMovementAdapter extends RecyclerView.Adapter<ItemMovementViewHolder> {
    List<ItemMovementModel> list;
    Context context;
    public ItemMovementAdapter(List<ItemMovementModel> list,
                               Context context)
    {
        this.list = list;
        this.context = context;

    }

    @Override
    public ItemMovementViewHolder
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

        ItemMovementViewHolder viewHolder
                = new ItemMovementViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final ItemMovementViewHolder viewHolder,
                     final int position)
    {
        //final ItemEnquiryModel myListData = list(position);

      /*  viewHolder.deliverynumber
                .setText(list.get(position).getDeliveryNumber());
        viewHolder.deliverynumber1
                .setText(list.get(position).getDeliveryNumbers());*/


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

