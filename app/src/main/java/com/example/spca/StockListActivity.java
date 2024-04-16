package com.example.spca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Toast;

import com.example.spca.adapter.StockAdapter;
import com.example.spca.admin.EditStockActivity;
import com.example.spca.model.StockItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StockListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StockAdapter adapter;
    private List<StockItem> stockList;
    private DatabaseReference stockReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stockList = new ArrayList<>();
        adapter = new StockAdapter(stockList);
        recyclerView.setAdapter(adapter);

        stockReference = FirebaseDatabase.getInstance().getReference("Stock");

        stockReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stockList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    StockItem stockItem = snapshot.getValue(StockItem.class);
                    stockList.add(stockItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StockListActivity.this, "Failed to fetch stock items", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemClickListener(new StockAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click, open edit/update activity
                Intent intent = new Intent(StockListActivity.this, EditStockActivity.class);
                intent.putExtra("stockItem", (CharSequence) stockList.get(position));
                startActivity(intent);
            }
        });
    }
}
