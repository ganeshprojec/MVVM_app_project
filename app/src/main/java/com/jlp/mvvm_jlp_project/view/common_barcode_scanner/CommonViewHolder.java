package com.jlp.mvvm_jlp_project.view.common_barcode_scanner;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;

/**
 * ViewHolder for list items which shown data at details fragment
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView value;
    public View divider;
    View view;

    public CommonViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_list_item_title);
        value = itemView.findViewById(R.id.tv_list_item_value);
        divider = itemView.findViewById(R.id.divider);
        view  = itemView;
    }
}
