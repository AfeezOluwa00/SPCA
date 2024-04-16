package com.example.spca;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchActivityCustomer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StockAdapterCustomer adapter;
    private List<StockItem> stockList;
    private DatabaseReference stockReference;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

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
        adapter = new StockAdapterCustomer(searchResults);
        recyclerView.setAdapter(adapter);
    }

    // Method to sort the stock items based on a selected attribute
    private void sortStock(String attribute, boolean ascending) {
        // Sort the stockList based on the selected attribute
        Collections.sort(stockList, new Comparator<StockItem>() {
            @Override
            public int compare(StockItem item1, StockItem item2) {
                switch (attribute) {
                    case "title":
                        return ascending ? item1.getTitle().compareTo(item2.getTitle()) :
                                item2.getTitle().compareTo(item1.getTitle());
                    case "manufacturer":
                        return ascending ? item1.getManufacturer().compareTo(item2.getManufacturer()) :
                                item2.getManufacturer().compareTo(item1.getManufacturer());

                    // Add more cases for other attributes as needed
                    default:
                        return 0;
                }
            }
        });
        adapter.notifyDataSetChanged();
    }
}
