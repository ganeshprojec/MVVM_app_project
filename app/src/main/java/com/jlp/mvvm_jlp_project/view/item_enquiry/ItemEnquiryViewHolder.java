package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;

public class ItemEnquiryViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView value;
    View view;

    public ItemEnquiryViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.deliverynumber);
        value = itemView.findViewById(R.id.deliverynumber1);
        view  = itemView;
    }
}
