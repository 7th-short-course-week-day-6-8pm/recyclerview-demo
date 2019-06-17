package com.rathana.recyclerveiwdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathana.recyclerveiwdemo.R;
import com.rathana.recyclerveiwdemo.model.SellItem;

import java.util.List;

public class StaggeredGridLayoutManagerAdapter extends RecyclerView.Adapter<StaggeredGridLayoutManagerAdapter.ViewHolder>  {

    List<SellItem> sellItems;

    public StaggeredGridLayoutManagerAdapter(List<SellItem> sellItems) {
        this.sellItems = sellItems;
    }

    @Override
    public int getItemCount() {
        return this.sellItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        SellItem sellItem= this.sellItems.get(position);

        //set data view holder
        viewHolder.thumb.setImageResource(sellItem.getThumb());
        viewHolder.title.setText(sellItem.getTitle());
        viewHolder.price.setText(""+sellItem.getPrice());


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sell_item_staggered_grid_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, price;
        ImageView thumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            price =itemView.findViewById(R.id.price);
            thumb=itemView.findViewById(R.id.thumb);
        }
    }
}
