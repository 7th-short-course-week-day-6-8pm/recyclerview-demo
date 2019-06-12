package com.rathana.recyclerveiwdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rathana.recyclerveiwdemo.DetailActivity;
import com.rathana.recyclerveiwdemo.R;
import com.rathana.recyclerveiwdemo.model.Inbox;

import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    private List<Inbox> inboxList;
    private Context context;

    public InboxAdapter(List<Inbox> inboxList, Context context) {
        this.inboxList = inboxList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return this.inboxList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.layout_inbox_item,viewGroup,false);
        return new InboxAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Inbox inbox=inboxList.get(position);

        viewHolder.contact.setText(inbox.getContact());
        viewHolder.subject.setText(inbox.getSubject());
        viewHolder.content.setText(inbox.getContent());
        viewHolder.date.setText(inbox.getDate());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("data",inbox);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        viewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.btnFavorite.setImageResource(
                        R.drawable.ic_star_yellow_24dp);
                notifyItemChanged(position);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contact,subject,content,date;
        private ImageView btnFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contact=itemView.findViewById(R.id.contact);
            subject=itemView.findViewById(R.id.subject);
            content=itemView.findViewById(R.id.content);
            date=itemView.findViewById(R.id.date);
            btnFavorite=itemView.findViewById(R.id.btnFavorite);
        }
    }
}
