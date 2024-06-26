package com.example.spca.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spca.Login;
import com.example.spca.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Button for creating stock
        Button createStockButton = findViewById(R.id.createStockButton);
        createStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(AdminActivity.this, CreateStockActivity.class);
                startActivity(intent);
            }
        });

        // Button for viewing stock
        Button viewStockButton = findViewById(R.id.viewStockButton);
        viewStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ViewStockActivity
                Intent intent = new Intent(AdminActivity.this, ViewStockActivity.class);
                startActivity(intent);
            }
        });

        // Button for searching
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open SearchActivity
                Intent intent = new Intent(AdminActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        // Button for updating stock
        Button updateStockButton = findViewById(R.id.updateStockButton);
        updateStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open UpdateStockActivity
                Intent intent = new Intent(AdminActivity.this, UpdateStockActivity.class);
                startActivity(intent);
            }
        });
        Button purchaseHistory = findViewById(R.id.purchaseHistory);
        purchaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open UpdateStockActivity
                Intent intent = new Intent(AdminActivity.this, PurchaseActivity.class);
                startActivity(intent);
            }
        });
        Button customerDetails = findViewById(R.id.customerDetails);

        customerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open UpdateStockActivity
                Intent intent = new Intent(AdminActivity.this, CustomerDetailsActivity.class);
                startActivity(intent);
            }
        });
        Button logOut = findViewById(R.id.logOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open UpdateStockActivity
                Intent intent = new Intent(AdminActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }



}
