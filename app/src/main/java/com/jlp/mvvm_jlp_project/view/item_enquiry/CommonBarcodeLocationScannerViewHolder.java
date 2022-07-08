package com.jlp.mvvm_jlp_project.view.item_enquiry;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;

public class CommonBarcodeLocationScannerViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView value;
    View view;

    public CommonBarcodeLocationScannerViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_list_item_title);
        value = itemView.findViewById(R.id.tv_list_item_value);
        view  = itemView;
    }
}
