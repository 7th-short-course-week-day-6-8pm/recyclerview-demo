package com.rathana.recyclerveiwdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.rathana.recyclerveiwdemo.adapter.GridLayoutManagerAdapter;
import com.rathana.recyclerveiwdemo.adapter.StaggeredGridLayoutManagerAdapter;
import com.rathana.recyclerveiwdemo.model.SellItem;

import java.util.ArrayList;
import java.util.List;

public class SellActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SellItem> sellItems= new ArrayList<>();
    GridLayoutManagerAdapter adapter;

    StaggeredGridLayoutManagerAdapter staggeredGridLayoutManagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        recyclerView=findViewById(R.id.sellRecyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(
//                this,
//                getResources().getInteger(R.integer.colSpan),
//                GridLayoutManager.VERTICAL,
//                false
//        ));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        ));

        adapter=new GridLayoutManagerAdapter(sellItems);

        staggeredGridLayoutManagerAdapter =
                new StaggeredGridLayoutManagerAdapter(sellItems);
        recyclerView.setAdapter(staggeredGridLayoutManagerAdapter);

        getSellItems();
    }

    private void getSellItems(){
        for (int i=0;i<20;i++){
            this.sellItems.add(new SellItem(R.drawable.book_cover,
                    "cartoon "+i,
                    7.5));
            this.sellItems.add(new SellItem(R.drawable.panda,
                    "cartoon "+i,
                    7.5));
            this.sellItems.add(new SellItem(R.drawable.bg,
                    "cartoon "+i,
                    7.5));
        }

        adapter.notifyDataSetChanged();
    }
}
