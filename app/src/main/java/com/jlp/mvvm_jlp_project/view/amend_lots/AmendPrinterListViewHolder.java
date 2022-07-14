package com.jlp.mvvm_jlp_project.view.amend_lots;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;

public class AmendPrinterListViewHolder extends RecyclerView.ViewHolder {
    public TextView printerCount;
    public TextView printerName;
    View view;

    public AmendPrinterListViewHolder(View itemView) {
        super(itemView);
        printerCount = itemView.findViewById(R.id.printerCount);
        printerName = itemView.findViewById(R.id.printerName);
        view  = itemView;
    }
}
