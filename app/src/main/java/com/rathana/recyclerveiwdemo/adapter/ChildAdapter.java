package com.rathana.recyclerveiwdemo.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathana.recyclerveiwdemo.R;
import com.rathana.recyclerveiwdemo.model.Child;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {


    List<Child> children;

    public ChildAdapter(List<Child> children) {
        this.children = children;
    }

    @Override
    public int getItemCount() {
        return this.children.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Child child=children.get(i);
        viewHolder.thumb.setImageResource(child.getThumb());
        viewHolder.appName.setText(child.getAppName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_layout,viewGroup,false));
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thumb;
        TextView appName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb=itemView.findViewById(R.id.appThumb);
            appName=itemView.findViewById(R.id.appName);
        }
    }
}
