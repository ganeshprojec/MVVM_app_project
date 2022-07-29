package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.databinding.ItemRowTitleAndValueBinding;
import com.jlp.mvvm_jlp_project.model.TitleValueDataModel;
import com.jlp.mvvm_jlp_project.view.carrier_collection_and_handover_details.CarrierHandoverDeliveryItemDetailsFragment;
import com.jlp.mvvm_jlp_project.view.common_barcode_scanner.CommonViewHolder;

import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private static final String TAG = CommonAdapter.class.getSimpleName();
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
        try {
            // As the title value is zero we can not get the string so we have to directly show th value from attribute
            // else title have some value from string.xml
            if(list.get(position).getTitle()==0){
                viewHolder.title.setText(String.valueOf(list.get(position).getNumber()));
            }else {
                viewHolder.title.setText(context.getResources().getString(list.get(position).getTitle()));
            }
            viewHolder.value.setText(list.get(position).getValue());
            // To hide the last line from recycler view
            if (position == list.size()-1) viewHolder.divider.setVisibility(View.GONE);
        }catch (Exception ex){
            Log.e(TAG, "Exception:"+ex);
        }
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

