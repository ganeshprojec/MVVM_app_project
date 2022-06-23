package com.jlp.mvvm_jlp_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.interfaces.ClickListener;
import com.jlp.mvvm_jlp_project.model.DrawerMenuItem;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.RecyclerViewHolder> {

    private ArrayList<DrawerMenuItem> menuArrayList;
    private Context mcontext;
    private ClickListener listener;

    public MenuAdapter(ArrayList<DrawerMenuItem> menuList, Context mcontext, ClickListener listener) {
        this.menuArrayList = menuList;
        this.mcontext = mcontext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        DrawerMenuItem data = menuArrayList.get(position);
        holder.title.setText(data.getTitle());

        final int index = holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                listener.onClickItem(index, menuArrayList.get(index));
            }
        });

        if (data.getResource() != 0) {
            holder.icon.setImageResource(data.getResource());
        }

    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView icon;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemTitle);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
