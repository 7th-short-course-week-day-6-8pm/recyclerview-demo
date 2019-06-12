package com.rathana.recyclerveiwdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rathana.recyclerveiwdemo.adapter.InboxAdapter;
import com.rathana.recyclerveiwdemo.model.Inbox;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvInbox;
    private List<Inbox> inboxList=new ArrayList<>();
    private InboxAdapter inboxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        getInboxes();
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
                    "38mn"
            ));
        }

        inboxAdapter.notifyDataSetChanged();
    }

}
