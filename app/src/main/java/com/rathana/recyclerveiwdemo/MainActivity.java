package com.rathana.recyclerveiwdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;
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
    public static  final int EDIT_REQUEST_CODE=100;


    private boolean isLoading;
    private int currentPage=1;
    private  int totalPage =10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddItem=findViewById(R.id.btnAddItem);

        initUI();
        //getInboxes();

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(MainActivity.this,AddItemActivity.class)
                        ,CODE_REQUEST
                );
            }
        });

        oldInboxes.addAll(inboxList);
    }

    private void initUI(){
        rvInbox=findViewById(R.id.rvInbox);
        rvInbox.setLayoutManager(new LinearLayoutManager(this));
        inboxAdapter=new InboxAdapter(inboxList,this);
        rvInbox.setAdapter(inboxAdapter);

        isLoading=true;
        Paginate.with(rvInbox,callback)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemSpanSizeLookup(new LoadingListItemSpanLookup() {
                    @Override
                    public int getSpanSize() {
                        return 1;
                    }
                }).build();

        callback.onLoadMore();
    }


    private  Paginate.Callbacks callback=new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            //get more data
            isLoading=true;
            if(isLoading){
               new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                getInboxes();
                            }
                        }
                        , 1000);

                //getInboxes();
                currentPage++;
            }

            isLoading=false;
        }

        @Override
        public boolean isLoading() {
            return false;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return currentPage==10;
        }
    };


    private void getInboxes(){
       // int i = currentPage*10;
        for(int i=0;i<10;i++){
            inboxList.add(new Inbox(
                    i+ " admin@gmail.com ",
                    "your subject",
                    "I send this email.",
                    "38mn",
                    R.drawable.ic_star_border_black_24dp
            ));
        }
        //isLoading=true;
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
                Intent intent=new Intent(this,EditInboxActivity.class);
                Inbox inbox=inboxList.get(this.itemPosition);
                Bundle bundle=new Bundle();
                bundle.putParcelable("data",inbox);
                intent.putExtras(bundle);
                startActivityForResult(intent,EDIT_REQUEST_CODE);
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
            scroll(0);
        }

        if(EDIT_REQUEST_CODE==requestCode && resultCode==RESULT_OK){
            Inbox inbox=data.getParcelableExtra("data");
            this.inboxList.set(this.itemPosition,inbox);
            inboxAdapter.notifyItemChanged(this.itemPosition);
        }
    }

    private void scroll(int position){
        rvInbox.smoothScrollToPosition(position);
    }

    //create option menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                inboxList.clear();
                inboxList.addAll(oldInboxes);
                inboxAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }


    List<Inbox> oldInboxes=new ArrayList<>();

    private void doSearch(String s){
        if(s.isEmpty()){
            return;
        }

        inboxList.clear();
        for (Inbox inbox: oldInboxes){
            if(inbox.getContact().contains(s)){
                inboxList.add(inbox);
            }
        }
        if(inboxList.size()==0)
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        inboxAdapter.notifyDataSetChanged();
        Log.e("Activity",s);
    }

}
