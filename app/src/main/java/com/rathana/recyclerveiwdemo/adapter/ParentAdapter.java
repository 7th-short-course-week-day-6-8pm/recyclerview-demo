package com.rathana.recyclerveiwdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rathana.recyclerveiwdemo.R;
import com.rathana.recyclerveiwdemo.model.Parent;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {


    List<Parent> parents;
    ChildAdapter adapter;
    Context context;
    public ParentAdapter(List<Parent> parents , Context context) {
        this.parents = parents;
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return this.parents.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Parent parent= this.parents.get(i);
        viewHolder.title.setText(parent.getTitle());
        adapter=new ChildAdapter(parent.getChildren());
        viewHolder.rvChild.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false));

        viewHolder.rvChild.setAdapter(adapter);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.parent_layout,viewGroup,false);
        return  new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView rvChild;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            rvChild=itemView.findViewById(R.id.rvChild);
        }
    }

}
