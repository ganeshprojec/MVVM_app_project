package com.jlp.mvvm_jlp_project.view.reprint_labels;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.RowReprintLabelDetailItemViewPrinterBinding;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProduct;
import com.jlp.mvvm_jlp_project.model.DeliveryGoodProductModel;
import com.jlp.mvvm_jlp_project.model.ReprintLableItemModel;

import java.util.List;


public class ReprintLabelAdapter extends RecyclerView.Adapter<ReprintLabelViewHolder> {

    public static List<DeliveryGoodProduct> list;
    private RowReprintLabelDetailItemViewPrinterBinding  binding;
    private Context ctx;




    public ReprintLabelAdapter(List<DeliveryGoodProduct> list, Context context)
    {
        this.list = list;
        ctx = context;

    }

    @NonNull
    @Override
    public ReprintLabelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowReprintLabelDetailItemViewPrinterBinding.inflate(inflater, parent, false);
        return new ReprintLabelViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ReprintLabelViewHolder holder, int position) {
        final int pos = position;
          int itemposition= holder.getAdapterPosition()+1;


        holder.product_item_name.setText(ctx.getResources().getString(R.string.dummy_item)+" "+ itemposition  );
        holder.productCode.setText(holder.view.getContext().getString(R.string.productCode)+" : "+list.get(position).getProductCode());
        holder.description.setText(list.get(position).getOrderDescriptionClean());
        holder.check_tick.setChecked(list.get(position).isSelected());
        holder.check_tick.setTag(position);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                holder.check_tick.setVisibility(View.VISIBLE);
                Integer pos = (Integer) holder.check_tick.getTag();

             //  Toast.makeText(ctx, list.get(pos).getDeliveryGoodId()+ " clicked!", Toast.LENGTH_SHORT).show();

                if (list.get(pos).isSelected()) {

                    list.get(pos).setSelected(false);
                    holder.check_tick.setChecked(false);
                    holder.check_tick.setButtonDrawable(null);
                    holder.cl_main.setBackgroundResource(R.color.white);

                } else {

                    list.get(pos).setSelected(true);
                    holder.check_tick.setChecked(true);
                    holder.check_tick.setButtonDrawable(R.drawable.check_tick);
                     holder.cl_main.setBackgroundResource(R.color.multi_item_select);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
