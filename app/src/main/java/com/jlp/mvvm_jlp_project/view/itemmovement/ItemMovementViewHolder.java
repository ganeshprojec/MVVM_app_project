package com.jlp.mvvm_jlp_project.view.itemmovement;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;


// ViewHolder code for ItemEnquiryRecyclerView

public class ItemMovementViewHolder extends RecyclerView.ViewHolder
{
    public TextView deliverynumber;
    public TextView deliverynumber1;
    View view;

    public ItemMovementViewHolder(View itemView)
    {
        super(itemView);
        deliverynumber
                = itemView
                .findViewById(R.id.deliverynumber);
        deliverynumber1
                = itemView
                .findViewById(R.id.deliverynumber1);

        view  = itemView;
    }
}
