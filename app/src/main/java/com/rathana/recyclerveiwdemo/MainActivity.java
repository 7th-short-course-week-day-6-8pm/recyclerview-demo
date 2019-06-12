package com.rathana.recyclerveiwdemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rathana.recyclerveiwdemo.adapter.InboxAdapter;
import com.rathana.recyclerveiwdemo.model.Inbox;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements InboxAdapter.OnAdapterItemLongClickListener {

    private RecyclerView rvInbox;
    private List<Inbox> inboxList=new ArrayList<>();
    private InboxAdapter inboxAdapter;

    FloatingActionButton btnAddItem;

    private int itemPosition;
    public static  final int CODE_REQUEST=99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddItem=findViewById(R.id.btnAddItem);

        initUI();
        getInboxes();

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(MainActivity.this,AddItemActivity.class)
                        ,CODE_REQUEST
                );
            }
        });
    }

    private void initUI(){
        rvInbox=findViewById(R.id.rvInbox);
        rvInbox.setLayoutManager(new LinearLayoutManager(this));
        inboxAdapter=new InboxAdapter(inboxList,this);
        rvInbox.setAdapter(inboxAdapter);
    }

    private void getInboxes(){
        for(int i=0;i<100;i++){
            inboxList.add(new Inbox(
                    i+ " admin@gmail.com ",
                    "your subject",
                    "I send this email.",
                    "38mn",
                    R.drawable.ic_star_border_black_24dp
            ));
        }

        inboxAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contaxt_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                return  true;
            case R.id.remove:
                removeRecyclerViewItem();

                return true;
            default:  return false;
        }

    }

    @Override
    public void onAdapterItemLongClicked(int position) {
        this.itemPosition=position;
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
    }

    private void removeRecyclerViewItem(){
        this.inboxList.remove(this.itemPosition);
        inboxAdapter.notifyItemRemoved(this.itemPosition);
        Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(CODE_REQUEST==requestCode && resultCode==RESULT_OK){
            //get new Inbox from AddItemActivity
            Inbox inbox=data.getParcelableExtra("data");
            //add new Inbox to recycler View
            this.inboxList.add(0,inbox);
            inboxAdapter.notifyItemInserted(0);
        }
    }
}
