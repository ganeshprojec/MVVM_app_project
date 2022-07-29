package com.jlp.mvvm_jlp_project.view.common_printer_list;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;

public class CommonPrinterListViewHolder extends RecyclerView.ViewHolder {
    public TextView printerCount;
    public TextView printerName;
    View view;

    public CommonPrinterListViewHolder(View itemView) {
        super(itemView);
        printerCount = itemView.findViewById(R.id.printerCount);
        printerName = itemView.findViewById(R.id.printerName);
        view = itemView;
    }
}
