package com.rathana.recyclerveiwdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rathana.recyclerveiwdemo.adapter.ParentAdapter;
import com.rathana.recyclerveiwdemo.model.Child;
import com.rathana.recyclerveiwdemo.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class NestedRecyclerViewActivity extends AppCompatActivity {


    List<Parent> parents=new ArrayList<>();
    RecyclerView rvParent;
    ParentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);


        rvParent=findViewById(R.id.rvParent);
        rvParent.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ParentAdapter(parents,this);
        rvParent.setAdapter(adapter);

        getData();
    }

    public void getData(){
        for(int i=0;i<6;i++){
            List<Child> children=new ArrayList<>();
            for(int j=0;j<10;j++){
                children.add(new Child("app name",R.mipmap.ic_launcher));
            }

            Parent parent =new Parent();
            parent.setTitle("parent "+i);
            parent.setChildren(children);
            this.parents.add(parent);
        }

        adapter.notifyDataSetChanged();
    }
}
