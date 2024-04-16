package com.example.spca.customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spca.Login;
import com.example.spca.R;
import com.example.spca.adapter.StockAdapterCustomer;
import com.example.spca.model.StockItem;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerViewStockActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StockAdapterCustomer adapter;
    private List<StockItem> stockList;
    private DatabaseReference stockReference;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_stock);



        // Initialize Firebase Database
        stockReference = FirebaseDatabase.getInstance().getReference("Stock");

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize EditText for search
        editTextSearch = findViewById(R.id.editTextSearch);

        // Initialize stockList
        stockList = new ArrayList<>();

        // Set up RecyclerView adapter
        adapter = new StockAdapterCustomer(stockList);
        recyclerView.setAdapter(adapter);

        // Add listener for search functionality
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchStock(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Retrieve stock items from Firebase
        retrieveStock();
    }

    private void retrieveStock() {
        stockReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stockList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    StockItem stockItem = snapshot.getValue(StockItem.class);
                    stockList.add(stockItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void searchStock(String query) {
        List<StockItem> searchResults = new ArrayList<>();
        for (StockItem item : stockList) {
            if (item.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    item.getManufacturer().toLowerCase().contains(query.toLowerCase()) ||
                    item.getCategory().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(item);
            }
        }
        adapter.setStockList(searchResults);
    }

    private void sortStockByTitle(boolean ascending) {
        Collections.sort(stockList, new Comparator<StockItem>() {
            @Override
            public int compare(StockItem item1, StockItem item2) {
                if (ascending) {
                    return item1.getTitle().compareToIgnoreCase(item2.getTitle());
                } else {
                    return item2.getTitle().compareToIgnoreCase(item1.getTitle());
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortStockByManufacturer(boolean ascending) {
        Collections.sort(stockList, new Comparator<StockItem>() {
            @Override
            public int compare(StockItem item1, StockItem item2) {
                if (ascending) {
                    return item1.getManufacturer().compareToIgnoreCase(item2.getManufacturer());
                } else {
                    return item2.getManufacturer().compareToIgnoreCase(item1.getManufacturer());
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortStockByPrice(boolean ascending) {
        Collections.sort(stockList, new Comparator<StockItem>() {
            @Override
            public int compare(StockItem item1, StockItem item2) {
                if (ascending) {
                    return item1.getPrice().compareToIgnoreCase(item2.getManufacturer());
                } else {
                    return item2.getPrice().compareToIgnoreCase(item1.getManufacturer());
                }
            }
        });
        adapter.notifyDataSetChanged();
    }


     private void sortStockByCategory(boolean ascending) {
         Collections.sort(stockList, new Comparator<StockItem>() {
             @Override
             public int compare(StockItem item1, StockItem item2) {
                 if (ascending) {
                     return item1.getCategory().compareToIgnoreCase(item2.getCategory());
                 } else {
                     return item2.getCategory().compareToIgnoreCase(item1.getCategory());
                 }
             }
         });
         adapter.notifyDataSetChanged();
     }



}
