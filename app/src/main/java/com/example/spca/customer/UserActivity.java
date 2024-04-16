package com.example.spca.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spca.Login;
import com.example.spca.R;
import com.example.spca.SearchActivityCustomer;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button EnterAdditionalDetails = findViewById(R.id.additionalDetails);
        EnterAdditionalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(UserActivity.this, AdditionalDetailsActivity.class);
                startActivity(intent);
            }
        });

        Button SearchItems = findViewById(R.id.searchItems);
        SearchItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(UserActivity.this, CustomerViewStockActivity.class);
                startActivity(intent);
            }
        });

        Button viewBasket = findViewById(R.id.viewItems);
        viewBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(UserActivity.this, ViewBasketActivity.class);
                startActivity(intent);
            }
        });

        Button logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateStockActivity
                Intent intent = new Intent(UserActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}