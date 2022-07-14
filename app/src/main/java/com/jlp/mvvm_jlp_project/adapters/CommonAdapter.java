package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.databinding.ItemRowTitleAndValueBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonViewHolder;

import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private List<TitleValueDataModel> list;
    private ItemRowTitleAndValueBinding binding;
    private Context context;
    public CommonAdapter(List<TitleValueDataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CommonViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ItemRowTitleAndValueBinding.inflate(inflater, parent, false);
        return new CommonViewHolder(binding.getRoot());
    }

    @Override
    public void
    onBindViewHolder(final CommonViewHolder viewHolder, final int position) {
        viewHolder.title.setText(context.getResources().getString(list.get(position).getTitle()));
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

