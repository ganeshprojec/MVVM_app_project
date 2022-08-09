package com.jlp.mvvm_jlp_project.view.reprint_labels;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;

public class ReprintLabelViewHolder extends RecyclerView.ViewHolder{

    public TextView product_item_name;
    public TextView productCode;
    public TextView description;
    public CheckBox check_tick;
    public ConstraintLayout cl_main;
    View view;

    public ReprintLabelViewHolder(@NonNull View itemView) {
        super(itemView);
        product_item_name = itemView.findViewById(R.id.product_item_name);
        productCode = itemView.findViewById(R.id.productCode);
        description = itemView.findViewById(R.id.productDescription);
        check_tick = itemView.findViewById(R.id.check_tick);
        cl_main = itemView.findViewById(R.id.cl_main);
        view = itemView;
    }
}
